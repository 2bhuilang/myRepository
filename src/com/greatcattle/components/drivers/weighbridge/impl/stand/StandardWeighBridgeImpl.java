package com.greatcattle.components.drivers.weighbridge.impl.stand;

import com.greatcattle.components.drivers.weighbridge.WeighBridgeApi;
import com.greatcattle.components.drivers.weighbridge.WeighBridgeStatus;

/**
 * 标准摄像头设备
 * @author zhang.xian
 *
 */
public class StandardWeighBridgeImpl implements WeighBridgeApi {

	private StandWeighBridgeConfig standWeighBridgeConfig = null;
	
	private WeighBridgeStatus weighBridgeStatus = new WeighBridgeStatus();
	
	private StandWeighBridgeProtocol standWeighBridgeProtocol = new StandWeighBridgeProtocol();

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connect() {
		
		
	}

	@Override
	public WeighBridgeStatus getStatus() {
		// TODO Auto-generated method stub
		return null;
	}
	 

}
