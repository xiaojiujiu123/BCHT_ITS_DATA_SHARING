package com.bcht.its.dataSharing.service.impl;

import com.bcht.its.dataSharing.beans.DsTJksqRefJk;
import com.bcht.its.dataSharing.beans.Dstjksj;
import com.bcht.its.dataSharing.service.KeyValidationService;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 姜鑫鑫 2017/08/08
 * 接口信息初始化
 */
@Service
public class KeyValidationServiceImpl implements KeyValidationService {
    @Autowired
    private Dao dao;
    @Override
    public List<String> findAllJk() {
        List<String>  array  = new ArrayList<String>();
        List<Dstjksj> list =   dao.query(Dstjksj.class, Cnd.where("1","=","1"));
        if(list.size()<1){
        }else{
            for(Dstjksj ds : list){
                array.add(ds.getKey());
            }
        }
        return array;
    }

    @Override
    public List<Dstjksj> findAll() {
        return dao.query(Dstjksj.class, Cnd.where("1","=","1"));
    }

    @Override
    public Map<String, List<String>> findAllMethod() {
            dao.query(DsTJksqRefJk.class,Cnd.where("1","=","1"));
        return null;
    }
}
