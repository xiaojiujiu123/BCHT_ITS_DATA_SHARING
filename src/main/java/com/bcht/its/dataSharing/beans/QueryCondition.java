package com.bcht.its.dataSharing.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * 客运车辆信息核查接口入参
 * @author 陶诗德
 *
 */
@XStreamAlias("QueryCondition")
public class QueryCondition {
	private String hpzl;
	private String hphm;
	public String getHpzl() {
		return hpzl;
	}
	public void setHpzl(String hpzl) throws UnsupportedEncodingException {
		this.hpzl = URLEncoder.encode(hpzl,"UTF-8");
	}
	public String getHphm() {
		return hphm;
	}
	public void setHphm(String hphm) throws UnsupportedEncodingException {
		this.hphm = URLEncoder.encode(hphm,"UTF-8");
	}
	
}
