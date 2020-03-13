package com.greatcattle.components.common.com;

import java.util.Hashtable;
import java.util.Map;

/**
 * 串口实例管理创建工厂
 * @author zhang.xian
 *
 */
public class ComPortFactory {
	
	public static Map<String,ComPortInst> sysComPortInstData = new Hashtable();
	
	private static ComPortFactory fac = new ComPortFactory();
	private ComPortFactory() {
		
	}
	
	public static ComPortFactory getComPortFactory() {
		return fac;
	}
	
	
	/**
	 * 初始化串口
	 * @param comConfig
	 * @return
	 */
	public synchronized ComPortInst reloadComPortInst(ComConfig comConfig) { 
		if(sysComPortInstData.get(comConfig.getPortName())!=null) {
			try {
				sysComPortInstData.get(comConfig.getPortName()).closeComPort();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} 
		ComPortInst comPortInst = new ComPortInst();
		
		try {
			comPortInst.init(comConfig);
			comPortInst.openComPort(); 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e); 
		} 
		sysComPortInstData.put(comConfig.getPortName(), comPortInst );
		
		return  comPortInst;
	}
	
	
	/**
	 * 加载串口
	 * @param comConfig
	 * @return
	 */
	public synchronized ComPortInst loadComPortInst(ComConfig comConfig) { 
		if(sysComPortInstData.get(comConfig.getPortName())!=null) {
			return sysComPortInstData.get(comConfig.getPortName());
		} 
		ComPortInst comPortInst = new ComPortInst(); 
		try {
			comPortInst.init(comConfig);
			comPortInst.openComPort(); 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e); 
		} 
		sysComPortInstData.put(comConfig.getPortName(), comPortInst ); 
		return  comPortInst; 
	}
	 
}
