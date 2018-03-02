package com.bcht.its.dataSharing;

import com.bcht.its.dataSharing.beans.Dstjksj;
import com.bcht.its.dataSharing.service.DataSharingService;
import com.bcht.its.dataSharing.utils.PropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * Created by JXX on 2017/8/9.
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class Application {
    /**
     * 缓存密钥
     */
    public  static List<String> INTERFACECACHE = null;

    public static Connection con = null;// 创建一个数据库连接
    private static String url = PropertiesUtils.getValue("url");
    private static String user = PropertiesUtils.getValue("user");
    private static String pass = PropertiesUtils.getValue("pass");
    /**
     * 缓存接口对象
     */
    public static Map<String,Dstjksj> INTERFACEMAP =null;
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
    @Autowired
    private DataSharingService DataSharingServiceImpl;

    @Bean(name = "/BchtDataSharing")
    public HessianServiceExporter exporter(){
       /* if(con==null){
            testOracle();
        }*/
        HessianServiceExporter ex = new HessianServiceExporter();
        ex.setService(DataSharingServiceImpl);
        ex.setServiceInterface(DataSharingService.class);
        return ex;
    }

    private void testOracle() {
        PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
        ResultSet result = null;// 创建一个结果集对象
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
            System.out.println("开始尝试连接数据库！");
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("连接成功！");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
