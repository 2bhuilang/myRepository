package com.greatcattle.components.common.com.listener;

import javax.swing.JLabel;

import com.greatcattle.components.common.com.ComConfig;

/**
 * 串口回调接收器
 * @author zhang.xian
 *
 */
public interface ListenerCallBack {
	
	public void callBack(String portData) ;
	
	public void setAcceptPannel(JLabel acceptPannel);
	
	public float getMaxInList() ;
	
	
	public void setComConfig(ComConfig comConfig);
	
	
}
