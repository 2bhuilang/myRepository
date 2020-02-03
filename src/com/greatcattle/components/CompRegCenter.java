package com.greatcattle.components;

import com.greatcattle.components.drivers.camera.CameraApi;
import com.greatcattle.components.drivers.weighbridge.WeighBridgeApi;

/**
 * 设备注册中心
 * @author zhang.xian
 *
 */
public class CompRegCenter {
	
	private CompRegCenter() {
		
	}
	
	public static CompRegCenter compRegCenter = new CompRegCenter();
	
	public static CompRegCenter getCompRegCenter() {
		return compRegCenter;
	}
	
	
	private CameraApi cameraApi = null;
	private WeighBridgeApi weighBridgeApi = null;
	
	 
	/**
	 * 注册摄像头设备
	 */
	public void regCameraApi(CameraApi cameraApi ) {
		this.cameraApi = cameraApi;
	}
	
	/**
	 * 注册摄像头设备
	 */
	public void regWeighBridgeApi(WeighBridgeApi weighBridgeApi ) {
		this.weighBridgeApi = weighBridgeApi;
	}
	
	


	public CameraApi getCameraApi() {
		return cameraApi;
	}
	
	
	 
}
