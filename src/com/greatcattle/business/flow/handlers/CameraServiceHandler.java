package com.greatcattle.business.flow.handlers;

import com.greatcattle.business.flow.vo.BusFlowStateData;

/**
 * 摄像头拍照环节
 * @author zhang.xian
 *
 */
public class CameraServiceHandler extends IHandler {
	
	public CameraServiceHandler(BusFlowStateData busFlowStateData) { 
		super(busFlowStateData);
	}
 
	@Override
	public void handle() {
		 //使用摄像头设备进行拍照
	}

}
