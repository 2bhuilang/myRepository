package com.greatcattle.system;

import com.greatcattle.framework.ContextApplication;
import com.greatcattle.system.ctx.AppSysContext;
import com.greatcattle.system.ctx.vo.HostInfo;

/**
 * 系统初始化管理
 * @author zhang.xian
 *
 */
public class SystemInitManager {
	
	
	public static void init() { 
		
		AppSysContext sysContext = AppSysContext.getSysCtx(); 
		sysContext.setHostInfo( initHostInfo()); 
		//spring上下文初始化
		ContextApplication.initCtx(); 
		
	}
	
	
	private static HostInfo  initHostInfo() {
		
		return null ;
	}
	
	/**
	 * 系统检查，设备状态检查等
	 */
	private static void sysCheck() {
		
	}
	
	
	
}
