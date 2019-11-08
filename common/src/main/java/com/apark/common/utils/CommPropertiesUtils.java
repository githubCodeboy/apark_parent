/*
package com.apark.common.utils;

import java.io.IOException;
import java.util.Properties;

public class CommPropertiesUtils {
	
	private static Properties properties;
	
	static {
		properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
			          .getResourceAsStream("common.properties"));
		} catch (IOException e) {
		}
		
	}
	
	public static String getProperty(String key){
		return properties.getProperty(key);
	}
	
	public static String getProperty(String key, String defaultValue){
		return properties.getProperty(key, defaultValue);
	}
	
//	public static String getEnviroment(){
//		return properties.getProperty("current_enviroment", Enviroment.Product);
//	}
	
	public static String getQiuNiuPath(){
		return properties.getProperty("QINIU_PATH");
	}
}
*/
