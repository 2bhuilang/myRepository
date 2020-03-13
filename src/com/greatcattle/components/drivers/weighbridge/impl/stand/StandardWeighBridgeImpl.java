package com.greatcattle.components.drivers.weighbridge.impl.stand;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.greatcattle.components.common.com.ComConfig;
import com.greatcattle.components.common.com.ComPortFactory;
import com.greatcattle.components.common.com.ComPortInst;
import com.greatcattle.components.common.com.listener.ListenerCallBack;
import com.greatcattle.components.drivers.weighbridge.WeighBridgeApi;
import com.greatcattle.components.drivers.weighbridge.WeighBridgeStatus;
import com.greatcattle.framework.utils.SpringUtils;

/**
 * dibang 
 * @author zhang.xian
 *
 */
public class StandardWeighBridgeImpl implements WeighBridgeApi {
 
	private StandWeighBridgeConfig standWeighBridgeConfig = null;
	
	private WeighBridgeStatus weighBridgeStatus = new WeighBridgeStatus();
	
	private StandWeighBridgeProtocol standWeighBridgeProtocol = new StandWeighBridgeProtocol();
	private ListenerCallBack  callBack = null ;
	private ComConfig comConfig  = null ;
	// StandWeighBridgeProtocol.StandWeighBridgeCallBack
	
	private ComPortInst comPortInst = null ;

	@Override
	public void load() {
		comConfig  = SpringUtils.getBean("weighBridge");
		comPortInst = ComPortFactory.getComPortFactory().loadComPortInst(comConfig); 
	}

	@Override
	public void reload() {
		comConfig  = SpringUtils.getBean("weighBridge");
		comPortInst = ComPortFactory.getComPortFactory().reloadComPortInst(comConfig);
		
	}
 

	@Override
	public void connect() {
		comConfig  = SpringUtils.getBean("weighBridge");
		comPortInst = ComPortFactory.getComPortFactory().loadComPortInst(comConfig); 
	}
	
	@Override
	public void disConnect() { 
		comPortInst.closeComPort();
	} 
	
	public void stopListener( JLabel acceptpJPanel) {
		
		this.callBack = new StandWeighBridgeProtocol.StandWeighBridgeStopCallBack();
		 
		comPortInst.setListenerCallBack(callBack);  
	}
	
	
	public void reSetListener( JLabel acceptpJPanel) throws Exception {

		this.callBack = new StandWeighBridgeProtocol.StandWeighBridgeCallBack();
		callBack.setAcceptPannel( acceptpJPanel);
		comPortInst.setListenerCallBack(callBack);  
	}
	
	
	public float getMaxWeight() {
		if(this.callBack!=null) {
			return  this.callBack.getMaxInList();
		}
		return 0f;
	}
	
	

	@Override
	public WeighBridgeStatus getStatus() {
		
		return null;
	}
	 

}
