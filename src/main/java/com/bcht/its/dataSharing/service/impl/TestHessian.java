package com.bcht.its.dataSharing.service.impl;


import com.bcht.its.dataSharing.beans.QueryCondition;
import com.bcht.its.dataSharing.service.DataSharingService;
import com.caucho.hessian.client.HessianProxyFactory;
import net.sf.json.JSONObject;
import org.json.XML;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by JXX on 2017/6/2.
 */
public class TestHessian {
    public static void main(String[] args) {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        String url = "http://localhost:9999/BchtDataSharing";
        String key = "63bea323035781fe37716fec1099";


     try {
           DataSharingService bchtTrffService = (DataSharingService) hessianProxyFactory.create(DataSharingService.class, url);
         QueryCondition queryCondition = new QueryCondition();
         try {
             String sh = "贵C6T909";
             sh = sh.substring(1);
             queryCondition.setHphm("C6T909");
             queryCondition.setHpzl("02");
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
            /*PoliceCarLocation pcl = new PoliceCarLocation();
            pcl.setLsh(getUUID());
            pcl.setFx("1");
            pcl.setHphm("皖AHH007");
            pcl.setHpzl("02");
            pcl.setJd("123.3123213");
            pcl.setWd("34.2343242");
            pcl.setSbbh("12321321321");
            pcl.setSd("23");
            pcl.setSbsj("2017-12-12 12:12:12");*/
            String result = bchtTrffService.queryVeh(key, JSONObject.fromObject(queryCondition).toString());
            //JSONObject jsonObject= (JSONObject)XML.toJSONObject(result);
            System.out.print(result);
       /*  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = new Date();
             String startTime =  sdf.format(start);
           for(int i=0;i<2;i++){
               PassingVehicle passingVehicle  = new PassingVehicle();
               passingVehicle.setHphm("皖AHH007");
               passingVehicle.setHpzl("02");
               passingVehicle.setCdbh("1");
               passingVehicle.setCllx("K33");
               passingVehicle.setClsd("80");
               passingVehicle.setCsys("F");
               passingVehicle.setFx("1");
               passingVehicle.setFxlx("合肥");
               passingVehicle.setSbbh("38492342424");
               Date date = new Date();
               passingVehicle.setGcsj(sdf.format(date));
               passingVehicle.setWfdm("1232");
               passingVehicle.setLsh(getUUID());
               passingVehicle.setTpurl("http://pic130.nipic.com/file/20170524/4516668_143048292032_2.jpg");
               String result = bchtTrffService.writeViolationVehicle(key, JSONObject.fromObject(passingVehicle).toString());
               System.out.println("result"+i+":"+result);
           }
         Date end = new Date();

         String endTime =    sdf.format(end);
         System.out.println("开始时间:"+startTime);
         System.out.println("结束时间"+endTime);*/
        } catch (MalformedURLException e) {
            e.printStackTrace();


        }


    }
    public static String getUUID(){
        String str = UUID.randomUUID().toString();
        return str.substring(0,8)+ str.substring(9,13)+ str.substring(19,23)+ str.substring(24);
    }


}
