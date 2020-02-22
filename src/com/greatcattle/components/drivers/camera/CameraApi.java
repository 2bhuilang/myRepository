package com.greatcattle.components.drivers.camera;

/**
 * 摄像设备 api
 * @author zhang.xian
 *
 */
public interface CameraApi
{	
	
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
	 * 抓拍照片
	 * @return
	 */
	public byte[] takePicture();
	
	
	/**
	 * 状态
	 * @return
	 */
	public CameraStatus getCameraStatus();
	
	 
}
