package com.greatcattle.components;

import com.greatcattle.components.drivers.camera.CameraApi;
import com.greatcattle.components.drivers.plc.PLCApi;
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
	 
	/**
	 * 初始化所有设备
	 */
	public void initAllComps() {
		
	}
	
	
	private CameraApi cameraApi = null;
	
	//车牌识别摄像头
	private CameraApi cameraPicApi = null;
	
	private WeighBridgeApi weighBridgeApi = null;
	
	
	private PLCApi pLCApi = null;
	
	 
	/**
	 * 注册摄像头设备
	 */
	public void regCameraApi(CameraApi cameraApi ) {
		this.cameraApi = cameraApi;
	}
	
	/**
	 * 注册拍照摄像头
	 */
	public void regPicCameraApi(CameraApi cameraApi ) {
		this.cameraApi = cameraApi;
	}
	
	/**
	 * 注册摄地磅设备
	 */
	public void regWeighBridgeApi(WeighBridgeApi weighBridgeApi ) {
		this.weighBridgeApi = weighBridgeApi;
	}
	
	/**
	 * 注册摄地磅设备
	 */
	public void regPLCApi(PLCApi pLCApi ) {
		this.pLCApi = pLCApi;
	}
	
	
	public PLCApi getPlcApi() {
		return this.pLCApi;
	}
	

	public CameraApi getCameraApi() {
		return cameraApi;
	}
	
	
	 
}
