package com.greatcattle.business.flow.handlers;

import com.greatcattle.business.flow.vo.BusFlowStateData;

public abstract class IHandler {
	
	private BusFlowStateData busFlowStateData= null;
	
	public IHandler(BusFlowStateData busFlowStateData) {
		this.busFlowStateData = busFlowStateData ;
	}
	
	
	abstract public void handle();


	public BusFlowStateData getBusFlowStateData() {
		return busFlowStateData;
	}


	public void setBusFlowStateData(BusFlowStateData busFlowStateData) {
		this.busFlowStateData = busFlowStateData;
	}
	
	
}
