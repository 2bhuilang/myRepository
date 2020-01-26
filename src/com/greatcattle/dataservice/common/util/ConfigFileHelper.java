package com.greatcattle.dataservice.common.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * 配置文件助手类
 * @author easonwu 2010-01-10
 *
 */

public class ConfigFileHelper {
	private ConfigFileHelper(){
		
	}
	
	private static final String app_params_properties="app_params.properties" ;
	
	private static final String log4j_properties="log4j.properties" ;
	
	private static final String Resources_Informix_properties="Resources_Informix_properties" ;
	
	private static final String Resources_ORA_properties="Resources_ORA.properties" ;
	
	
	private static InputStream getFileInputStream(String fileName) throws Exception {
		return ConfigFileHelper.class.getClassLoader().getResourceAsStream(fileName) ;
	}
	
	public  static Properties getConfigFileProperties(String fileName) throws Exception {
		InputStream is = getFileInputStream( fileName) ;
		Properties configFile = new Properties() ;
		configFile.load(is) ;
		is.close() ;
		
		return configFile ;
	}
	
	public  static Properties getCrmParamConfigFile() throws Exception {
		return getConfigFileProperties(app_params_properties) ;
	}
	public  static Properties getLog4JConfigFile() throws Exception {
		return getConfigFileProperties(log4j_properties) ;
	}
	
	public  static Properties getInformixDBConfigFile() throws Exception {
		return getConfigFileProperties(Resources_Informix_properties) ;
	}
	
	public  static Properties getOracleDBConfigFile() throws Exception {
		return getConfigFileProperties(Resources_ORA_properties) ;
	}
}
