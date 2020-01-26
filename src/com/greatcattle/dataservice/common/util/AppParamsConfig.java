package com.greatcattle.dataservice.common.util;
  
import java.util.Enumeration;
import java.util.Properties;
  

public class AppParamsConfig {
	/**
	 * Logger for this class
	 */ 
	private Properties params = new Properties(); 
	private static AppParamsConfig paramsConfig = null;
	
	public AppParamsConfig(){		
	}
	
	/**
	 * 获取实例对象
	 * @return
	 */
	public static AppParamsConfig getInstance(){
		if(paramsConfig == null){
			paramsConfig = new AppParamsConfig();
		}
		return paramsConfig;
	}
	
	/**
	 * 初始化参数，在系统初始化时载入。或者定时任务刷新。
	 * @param path
	 */
	public void initParams(String path){
		
//		try {
			try {
				params = ConfigFileHelper.getCrmParamConfigFile();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			//trim()
			Enumeration enu = params.keys();
			String key = "";
			String val = "";
			while(enu.hasMoreElements()){
				key = (String)enu.nextElement();
				val = params.getProperty(key);
				if(val!=null && !"".equals(val)){
					params.put(key, val.trim());
				}
			}
			
			//主动刷新特定的常量。
			if (null != params.getProperty("MAX_UPLOAD_SIZE")) {
				AppConstants.setMaxUploadSize(params.getProperty("MAX_UPLOAD_SIZE"));
			}
			if (null != params.getProperty("NOT_FILTER_PAFGES")) {
				AppConstants.setNotFilterPages(params.getProperty("NOT_FILTER_PAFGES"));
			}
			if (null != params.getProperty("SHOW_SQL")) {
				AppConstants.SHOW_SQL = (String)params.getProperty("SHOW_SQL");
			}
			if (null != params.getProperty("SHOW_METHOD_TIME")) {
				AppConstants.SHOW_METHOD_TIME = (String)params.getProperty("SHOW_METHOD_TIME");
			}
			if (null != params.getProperty("MAX_QUERY_SIZE")) {
				AppConstants.setMaxQuerySize(params.getProperty("MAX_QUERY_SIZE"));
			}
			
			//通过鉴权服务解密数据库密码
//			if (null != params.getProperty("DirectDBPwd")) {
//				
//				String directDBPwd2 = params.getProperty("DirectDBPwd");
//				StructPackage reqPack = new StructPackage("EncryptRequest");
//		        reqPack.setRepStructName("EncryptRespond");
//		        
//		        reqPack.addString("flag", "F");
//		        reqPack.addString("proclaimedBuff", "");
//		        reqPack.addString("secretBuff", params.getProperty("DirectDBPwd"));
//		        
//		        StructPackage resPack = new StructPackage("EncryptRespond");
//				try {
//					resPack = AuthService.getInstance().sendRequest(reqPack);
//					directDBPwd2 = resPack.getString("strResultBuff");
//				} catch (AuthException e) {
//					e.printStackTrace();
//				}
//				
//				params.setProperty("DirectDBPwd",directDBPwd2);
//			}
			
//		} catch (FileNotFoundException e) {
//			logger.error("file not found: "+path+ "  " + e);
//			return;
//		} catch (IOException e) {
//			logger.error("can't init config file: "+path+ "  " + e);
//			return;
//		}		
		
	}
	
	/**
	 * 获取参数值
	 * @param name 参数名称
	 * @return
	 */
	public String getParamValue(String name){
		return params.getProperty(name);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AppParamsConfig.getInstance().initParams("D:/workspaces/crmworkspace/CrmWeb/CrmWeb/WEB-INF/");
 
		
	}
	
	/*
	 * 更新内存中的系统参数
	 */
	public void updateProperty(String paramCode) throws Exception{
			Properties tempProperty = ConfigFileHelper.getCrmParamConfigFile();
			Enumeration enu = tempProperty.keys();
			String key = "";
			String val = "";
			while(enu.hasMoreElements()){
				key = (String)enu.nextElement();
				val = tempProperty.getProperty(key);
				if(val!=null && !"".equals(val)){
					tempProperty.put(key, val.trim());
				}
			}
			String paramValue= tempProperty.getProperty(paramCode);
			//如果存在这个参数，才去刷新参数的信息
			if(paramValue!=null){
				params.setProperty(paramCode,paramValue);
			}
	}
}
