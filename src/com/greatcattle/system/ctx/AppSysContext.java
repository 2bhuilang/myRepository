package com.greatcattle.system.ctx;

import com.greatcattle.system.ctx.vo.HostInfo;

/**
 * 系统上下文，用户存储当前系统的设备信息，流程信息等系统状态数据
 * @author zhang.xian
 *
 */
public class AppSysContext {
	
	
	private static AppSysContext appSysCtx =new AppSysContext();
	private AppSysContext() {
		
	} 
	public static  AppSysContext getSysCtx() {
		return appSysCtx;
	}
	
	
	
	private HostInfo hostInfo = null;
	
	//当前登录用户的上下文
	private UserContext currentUserContext = null ;
	
	public UserContext getCurrentUserContext() {
		return currentUserContext;
	}
	
	public void setCurrentUserContext(UserContext currentUserContext) {
		this.currentUserContext = currentUserContext;
	}
	public HostInfo getHostInfo() {
		return hostInfo; 
	}
	public void setHostInfo(HostInfo hostInfo) {
		this.hostInfo = hostInfo;
	}
	
	 
	
	
	
	
}
