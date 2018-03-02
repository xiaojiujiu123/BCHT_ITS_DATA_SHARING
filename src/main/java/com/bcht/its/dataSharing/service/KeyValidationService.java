package com.bcht.its.dataSharing.service;

import com.bcht.its.dataSharing.beans.Dstjksj;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/26.
 */
public interface KeyValidationService extends Serializable {
    public List<String> findAllJk();
    public List<Dstjksj> findAll();
    public Map<String,List<String>> findAllMethod();
}
