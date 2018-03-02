package com.bcht.its.dataSharing.service.impl;
import com.alibaba.druid.util.StringUtils;
import com.bcht.its.dataSharing.Application;
import com.bcht.its.dataSharing.aop.validAOP;
import com.bcht.its.dataSharing.beans.*;
import com.bcht.its.dataSharing.service.DataSharingService;
import com.bcht.its.dataSharing.service.IRedisService;
import com.bcht.its.dataSharing.utils.XstreamUtils;
import com.bcht.its.dataSharing.webservice.WebServiceClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import net.sf.json.JSONObject;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by JXX on 2017/8/9.
 */
@Service("DataSharingServiceImpl")
public class DataSharingServiceImpl implements DataSharingService {
    @Autowired
    private Dao dao;
    @Resource
    private IRedisService iRedisService;
    private static Logger logger  = LoggerFactory.getLogger(validAOP.class);
    /**
     * @param key 密钥
     * @return 设备集合Json
     */
    @Override
    public String queryDeviceInfo(String key) {
        QueryLog log =new QueryLog();
        int total=0;
       String devCount = iRedisService.get("devCount");
        if(StringUtils.isEmpty(devCount)){
            iRedisService.set("devCount",1+"");
            total=1;
        }else{
           int nowCount = Integer.parseInt(devCount);
             nowCount+=1;
            total= nowCount;
            iRedisService.set("devCount",nowCount+"");
        }

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String userIP =  request.getRemoteAddr();
        String user = Application.INTERFACEMAP.get(key).getSqr();
        Date now = new Date();
        log.setId(UUID.randomUUID().toString().replace("-",""));
        log.setFwip(userIP);
        log.setFwyh(user);
        log.setFwsj(now);
        String sql = "select ba.sbbh,\n" +
                "'' zdid,\n" +
                "'' chanNum,\n" +
                "ba.dwbh siteCode,\n" +
                "ba.sbmc,\n" +
                "ba.sblx,\n" +
                "ba.ip,\n" +
                "ba.sbdk port,\n" +
                "ba.sbyhm loginName,\n" +
                "ba.sbmm loginPass,\n" +
                "ba.jrfs accessMode,\n" +
                "'' jd,\n" +
                "'' wd,\n" +
                "toll.cdh cdh,\n" +
                "ba.sbcs,\n" +
                "toll.fxlx,\n" +
                "toll.fxmc,\n" +
                "ba.zxzt zt\n" +
                " from dev_t_base ba left join dev_t_tollgate toll on ba.sbbh = toll.sbbh where ba.sblx = '01'\n" +
                " union all \n" +
                " select ba.sbbh,\n" +
                "'' zdid,\n" +
                "'' chanNum,\n" +
                "ba.dwbh siteCode,\n" +
                "ba.sbmc,\n" +
                "ba.sblx,\n" +
                "ba.ip,\n" +
                "ba.sbdk port,\n" +
                "ba.sbyhm loginName,\n" +
                "ba.sbmm loginPass,\n" +
                "ba.jrfs accessMode,\n" +
                "'' jd,\n" +
                "'' wd,\n" +
                "vio.cdh cdh,\n" +
                "ba.sbcs,\n" +
                "vio.fxlx,\n" +
                "vio.fxmc,\n" +
                "ba.zxzt zt\n" +
                "from dev_t_base ba left join dev_t_vio vio on ba.sbbh = vio.sbbh where ba.sblx = '02' \n" +
                " ";
        Sql sqls = Sqls.create(sql);
        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<DeviceInfo> devs = new ArrayList<DeviceInfo>();
                while (rs.next()) {
                    DeviceInfo dev = new DeviceInfo();
                    dev.setSbbh(rs.getString("sbbh"));
                    dev.setSbmc(rs.getString("sbmc"));
                    dev.setZdid(rs.getString("zdid"));
                    dev.setChanNum(rs.getString("chanNum"));
                    dev.setSiteCode(rs.getString("siteCode"));
                    dev.setSblx(rs.getString("sblx"));
                    dev.setIp(rs.getString("ip"));
                    dev.setPort(rs.getString("port"));
                    dev.setLoginName(rs.getString("loginName"));
                    dev.setLoginPass(rs.getString("loginPass"));
                    dev.setAccessMode(rs.getString("accessMode"));
                    dev.setCdh(rs.getString("cdh"));
                    dev.setJd(rs.getString("jd"));
                    dev.setWd(rs.getString("wd"));
                    dev.setSbcs(rs.getString("sbcs"));
                    dev.setFxlx(rs.getString("fxlx"));
                    dev.setFxmc(rs.getString("fxmc"));
                    dev.setZt(rs.getString("zt"));
                    devs.add(dev);
                }
                return devs;
            }
        });
        dao.execute(sqls);
        List<DeviceInfo> lists = sqls.getList(DeviceInfo.class);
        String msg = "用户：‘"+user+"’今天第"+total+"次访问该接口,IP为:‘"+userIP+"’，共获取了"+lists.size()+"条设备数据";
        log.setMs(msg);
        dao.insert(log);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(String.class, STRING);
        Gson gson = gsonBuilder.create();
        String str = gson.toJson(lists);
        return str;
    }

    @Override
    public String querySiteInfo(String key) {
        String siteCount = iRedisService.get("siteCount");
        if(StringUtils.isEmpty(siteCount)){
            iRedisService.set("siteCount",1+"");
        }else{
            int nowCount = Integer.parseInt(siteCount);
            nowCount+=1;
            iRedisService.set("siteCount",nowCount+"");
        }
        String sql = "select dwbh,dwmc from dev_t_site t";
        Sql sqls = Sqls.create(sql);
        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<SiteInfo> sites = new ArrayList<SiteInfo>();
                while (rs.next()) {
                    SiteInfo siteInfo = new SiteInfo();
                    siteInfo.setDwbh(rs.getString("dwbh"));
                    siteInfo.setDwmc(rs.getString("dwmc"));
                    sites.add(siteInfo);
                }
                return sites;
            }
        });
        dao.execute(sqls);
        List<SiteInfo> lists = sqls.getList(SiteInfo.class);
        Gson gson =  new GsonBuilder().serializeNulls().create();
        String str = gson.toJson(lists);
        return str;
    }

    //自定义Strig适配器
    private static final TypeAdapter<String> STRING = new TypeAdapter<String>()
    {
        public String read(JsonReader reader) throws IOException
        {
            if (reader.peek() == JsonToken.NULL)
            {
                reader.nextNull();
                return "";
            }
            return reader.nextString();
        }

        public void write(JsonWriter writer, String value) throws IOException
        {
            if (value == null)
            {
                // 在这里处理null改为空字符串
                writer.value("");
                return;
            }
            writer.value(value);
        }
    };

    @Override
    public String queryVeh(String key,String str) {
        if(!Application.INTERFACECACHE.contains(key)){
            return "接口秘钥错误，数据写入失败！";
        }
        JSONObject json = JSONObject.fromObject(str);
        String sql ="";
        PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
        ResultSet result = null;// 创建一个结果集对象
        try {
            pre = Application.con.prepareStatement(sql);
            pre.setString(1, json.getString("hpzl"));
            pre.setString(2, json.getString("hphm"));

            result = pre.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        QueryCondition queryCondition = new QueryCondition();
        try {
            queryCondition.setHpzl(json.getString("hpzl"));
            queryCondition.setHphm(json.getString("hphm"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String xml="";
        try{
            xml = WebServiceClient.queryVehBus(queryCondition);
        }catch (Exception e){
            logger.error("六合一接口地址中断！");
            Head1 head1 = new Head1();
            head1.setCode(0);
            head1.setRownum("0");
            head1.setMessage("接口地址连接失败！");

            return JSONObject.fromObject(head1).toString();
        }

        Object obj = XstreamUtils.xmlToObject(xml,Root1.class);
        String jsonStr = JSONObject.fromObject(obj).toString();
        return jsonStr;
    }

    @Override
    public String writeViosurveil(String key,String str){
        return "";
    }
}
