package com.greatcattle.business.flow;

import java.util.ArrayList;
import java.util.List;

import com.greatcattle.business.flow.handlers.CameraServiceHandler;
import com.greatcattle.business.flow.handlers.IHandler;
import com.greatcattle.business.flow.vo.BusFlowStateData;

/**
 * 服务流程引擎
 * 管理各个设备交互流程，保证设备作用有序进行
 * @author zhang.xian
 *
 */
public class ServiceFlowEngine {
	
	private BusFlowStateData busFlowStateData =  BusFlowStateDataListener.createBusFlowSateProxy(new BusFlowStateData());
			
			 
	
	private List<IHandler> flowHanlders = new ArrayList();
	
	public void dobus() {
		for(IHandler ihandler : flowHanlders) {
			ihandler.handle();
		}
	}
	
	
	/**
	 * 创建服务引擎
	 * @return
	 */
	public static ServiceFlowEngine createServiceFlowEngine() {
		ServiceFlowEngine serviceFlowEngine = new ServiceFlowEngine();
		serviceFlowEngine.flowHanlders = ServiceFlowEngine.createFlowHandler(serviceFlowEngine.busFlowStateData);
		
		return serviceFlowEngine;
	}
	
	
	/**
	 *环节处理对象与设备检测相关，后续修改位动态配置方式
	 * @return
	 */
	private static List createFlowHandler(BusFlowStateData busFlowStateData) {
		List flows = new ArrayList();
		flows.add( new CameraServiceHandler(busFlowStateData));
		
		
		return flows;
	}
	
	
}
