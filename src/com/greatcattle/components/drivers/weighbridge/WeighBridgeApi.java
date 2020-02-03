package com.greatcattle.components.drivers.weighbridge;

/**
 * 地磅设备api
 * @author zhang.xian
 *
 */
public interface WeighBridgeApi{
	
	
	/**
	 * 初始化
	 */
	public void load();
	
	
	/**
	 * 重新加载
	 */
	public void reload();
	
	
	/**
	 * 复位
	 */
	public void reset();
	
	
	/**
	 * 复位
	 */
	public void connect();
	
	  
	
	
	/**
	 * 状态
	 * @return
	 */
	public WeighBridgeStatus getStatus();
	
	 
}
