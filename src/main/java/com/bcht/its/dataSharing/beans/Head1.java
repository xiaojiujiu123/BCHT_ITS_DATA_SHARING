package com.bcht.its.dataSharing.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 查询类接口返回xml参数
 * Created by zyq on 2017/4/14.
 */
@XStreamAlias("head")
public class Head1 {
    private int code;//写入结果标记(1-成功,0-失败)
    private String message;//写入结果描述信息，对于错误的将显示错误信息
    private String rownum;//查询结果返回的记录数

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRownum() {
        return rownum;
    }

    public void setRownum(String rownum) {
        this.rownum = rownum;
    }
}
