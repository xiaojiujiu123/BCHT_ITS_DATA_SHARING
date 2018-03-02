package com.bcht.its.dataSharing.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *  配置文件工具
 * @author 陶诗德
 *
 */
public class PropertiesUtils {
	private  static Properties properties = null;

	private static Properties getInstance(){

		String filePath = PropertiesUtils.class.getResource("/").getPath()+"config.properties";
		try {
			properties = new Properties();
			properties.load(new FileInputStream(new File(filePath)));
			return properties;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void setProperties(Properties properties){
		this.properties = properties;
	}
	
	public  static String getValue(String key){
		if(properties == null){
			properties = getInstance();
		}
		return properties.getProperty(key);
	}
}
