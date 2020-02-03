package com.greatcattle.components;

import com.greatcattle.components.drivers.camera.impl.stand.StandardCameraImpl;

/**
 * 设备管理
 * 关系当前系统的需要加载的设备
 * 
 * @author zhang.xian
 *
 */
public class CompManager {
	
	
	/**
	 * 初始化所有设备
	 */
	public void initComps() {
		//设置摄像头设备
		CompRegCenter.getCompRegCenter().regCameraApi(new StandardCameraImpl());
		 
	}
	
	
}
