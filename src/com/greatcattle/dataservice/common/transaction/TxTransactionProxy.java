package com.greatcattle.dataservice.common.transaction;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.transaction.TransactionDefinition;

import com.greatcattle.dataservice.service.DBService;
import com.greatcattle.dataservice.service.DbExecuteCallBack;
import com.greatcattle.dataservice.service.impl.DbServiceImpl;
import com.greatcattle.framework.utils.SpringUtils;

/**
 * cglib 代理管理 
 *  实现基于SvcExecuteService的事务代理 ，统一于spring事务管理
 * @author zhang.xian 
 */
public class TxTransactionProxy implements MethodInterceptor {

	private int progration = TransactionDefinition.PROPAGATION_REQUIRED;
 

	public static synchronized TxTransactionProxy getInstance(
			final int propagation) {

		return new TxTransactionProxy(propagation);
		 
	}

	public TxTransactionProxy() {
	}

	public TxTransactionProxy(int propagation) {
		this.progration = propagation;
	}

	public Object intercept(final Object obj, Method method, final Object[] args,
			final MethodProxy proxy) throws Throwable {
		if (method.getModifiers() != Modifier.PUBLIC) {
			return proxy.invokeSuper(obj, args);
		}
		
		if(method.getDeclaringClass()==Object.class){
			return proxy.invokeSuper(obj, args);
		}
		DBService dbService =SpringUtils.getBean(DbServiceImpl.class); 
		
		Object result = dbService.excuteUpdateCallBack( new DbExecuteCallBack (){
			public Object callback(){
				Object result;
				try {
					result = proxy.invokeSuper(obj, args);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}
				return result;
			}
		}); 
		
		return result; 
	}
}