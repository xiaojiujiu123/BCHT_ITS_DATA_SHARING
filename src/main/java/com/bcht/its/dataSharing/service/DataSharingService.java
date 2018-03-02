package com.bcht.its.dataSharing.service;

/**
 * Created by JXX on 2017/8/9.
 */
public interface DataSharingService {
    public String queryDeviceInfo(String key);
    public String querySiteInfo(String key);
    public String queryVeh(String key,String str);
    public String writeViosurveil(String key,String str);
}
