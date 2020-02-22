package com.greatcattle.business.flow;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import com.greatcattle.business.flow.vo.BusFlowStateData;
import com.greatcattle.dataservice.common.transaction.TxProxyCreator;

/**
 * 动态数据监听器
 * 将业务动态数据通知到面板采集
 * @author zhang.xian
 *
 */
public class BusFlowStateDataListener {
	
	
	public static BusFlowStateData createBusFlowSateProxy(BusFlowStateData  targetObj) {
		return TxProxyCreator.newInstance(new MethodInterceptor() { 
			@Override
			public Object intercept(final Object obj, Method method, final Object[] args,
					final MethodProxy proxy) throws Throwable {
				
				Object result = proxy.invokeSuper(targetObj, args);
				if(method.getName().startsWith("set")) {
					notifyMainView( targetObj);
				}
				return result;
			}
			
		}, targetObj.getClass());
	}
	
	
	/**
	 * 面板通知
	 * @param targetObj
	 */
	public static void notifyMainView(BusFlowStateData  targetObj) {
		
	}
	
	
	
	
}
