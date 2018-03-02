package com.bcht.its.dataSharing.Scheduling;

import com.bcht.its.dataSharing.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JXX on 2017/8/10.
 * 用于删除每天缓存在redis里面的访问次数信息
 */
@Component
public class DeleteRedisKey {
    @Autowired
    private IRedisService iRedisServiceImpl;
    /**
     * 每天早上零点删除所有缓存访问次数的key
     */
    @Scheduled(cron = " 0 0 0 * * ?")
    public void deleteAllKey(){
        List<String> keys = new ArrayList<String >();
        keys.add("devCount");
        keys.add("siteCount");
        for(String str : keys){
            iRedisServiceImpl.delete(str);
        }
    }
}
