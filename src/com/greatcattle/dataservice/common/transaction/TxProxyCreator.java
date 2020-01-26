package com.greatcattle.dataservice.common.transaction;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * 
 * @author zhang.xian
 */
public class TxProxyCreator {


	public static <T> T newInstance(MethodInterceptor proxy, Class<T> targetClz) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(targetClz);
		enhancer.setCallback(proxy);
		return (T) enhancer.create();
	}
	public static <T> T newInstance(MethodInterceptor proxy, Class<T> targetClz,
		Class[] argumentTypes, Object[] arguments) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(targetClz);
		enhancer.setCallback(proxy);
		return (T) enhancer.create(argumentTypes, arguments);
	}

}
