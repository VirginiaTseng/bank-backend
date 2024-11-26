package com.virginia.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

//import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Yi 读取runConfig文件
 *
 */
public class RunConfigPropTools {
	private static String config = "runConfig.properties";
	private static Properties prop = new Properties();

	public static void setStrategy(String stratey) {
		if(stratey==null) {
		   config="runConfig.properties";
		}else {
			config="runConfig."+stratey+".properties";
		}
	}
	
	private static void loadPropFromProperty(String cfgFile) {
		try{
//				FileInputStream fis = new FileInputStream(new File(cfgFile));
			/*
			 * 两种读取方式 InputStream in = Thread.currentThread().getContextClassLoader()
			 * .getResourceAsStream(config);
			 */
			InputStream fis = Thread.currentThread().getContextClassLoader().getResourceAsStream(config);
			prop.load(fis);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	public static void load() {
		if(prop.size()==0) {
			loadPropFromProperty(config);
		}
	}

	public static Properties getProp() {
		return prop;
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

	public static Set<String> stringPropertyNames() {
		return prop.stringPropertyNames();
	}

//	public static HashMap<String, Object> getPropertiesByPrefix(String prefix) {
//		HashMap<String, Object> ht = new HashMap<String, Object>();
//		Set<String> set = prop.stringPropertyNames();
//
//		for (String key : set) {
//			if (key.startsWith(prefix)) {
//				ht.put(StringUtils.substringAfter(key, prefix), prop.getProperty(key));
//			}
//		}
//
//		return ht;
//	}
}
