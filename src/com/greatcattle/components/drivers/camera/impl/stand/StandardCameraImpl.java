package com.greatcattle.components.drivers.camera.impl.stand;

import com.greatcattle.components.drivers.camera.CameraApi;
import com.greatcattle.components.drivers.camera.CameraStatus;

/**
 * 标准摄像头设备
 * @author zhang.xian
 *
 */
public class StandardCameraImpl implements CameraApi {

	private StandCameraConfig standCameraConfig = null;
	
	private CameraStatus cameraStatus = new CameraStatus();
	
	private StandCameraProtocol standCameraProtocol =  null ; 
    private long userId;
	
        
        public StandardCameraImpl(java.awt.Panel panelRealplay){
            standCameraProtocol = new StandCameraProtocol(panelRealplay);
        }
	
	@Override
	public byte[] takePicture() {
		
		return null;
	}
 

	@Override
	public void load() {
		//读取配置
		standCameraConfig = new StandCameraConfig();
		//建立连接
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connect() {
		long userId= standCameraProtocol.reg();
                if(userId>-1){
                     standCameraProtocol.playView(); 
                } 
	}


	@Override
	public CameraStatus getCameraStatus() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void reload() {
		// TODO Auto-generated method stub
		
	}

}
