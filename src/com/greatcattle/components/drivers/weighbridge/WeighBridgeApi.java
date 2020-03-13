package com.greatcattle.components.drivers.weighbridge;

import javax.swing.JLabel;

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
	public void connect();
	
	public void disConnect() ;
	
	  
	public void reSetListener( JLabel acceptpJPanel) throws Exception;
	
	
	public void stopListener( JLabel acceptpJPanel);	
	  
	public float getMaxWeight(); 
	
	/**
	 * 状态
	 * @return
	 */
	public WeighBridgeStatus getStatus();
	
	 
}
