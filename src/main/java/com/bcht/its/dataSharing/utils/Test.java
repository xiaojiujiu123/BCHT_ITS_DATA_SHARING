package com.bcht.its.dataSharing.utils;

import com.bcht.its.dataSharing.service.DataSharingService;
import com.caucho.hessian.client.HessianProxyFactory;

import java.net.MalformedURLException;

/**
 * Created by JXX on 2017/8/9.
 */
public class Test {
    public static void main(String[] args) {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        String url = "http://localhost:9999/BchtDataSharing";
        String key  = "a6261187bde5491182f06df276da537b";
        try {
            for(int i=0;i<1000;i++){
                DataSharingService dataSharingService =(DataSharingService) hessianProxyFactory.create(DataSharingService.class,url);
                String str=   dataSharingService.queryDeviceInfo(key);
                //System.out.println(str);
            }
            System.out.println("finsh");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
