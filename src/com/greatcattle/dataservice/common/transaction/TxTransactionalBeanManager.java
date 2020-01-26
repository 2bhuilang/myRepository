package com.greatcattle.dataservice.common.transaction;

import java.util.Hashtable;
import java.util.Map;

import org.springframework.transaction.TransactionDefinition;

/**
 * 事务管理
 * @author zhang.xian
 */
public class TxTransactionalBeanManager {
 
	private static Map<Class, Object> transactionalBoCache = new Hashtable();

	public static synchronized <T> T getTransactional(Class<T> clz) {
		 
		return TxTransactionalBeanManager.getTransactional(clz, true);
	}
	
	public static synchronized <T> T getTransactional(Class<T> clz, boolean useCache) {
		T bo = null ;
		if(useCache){
			bo = (T) transactionalBoCache.get(clz);
			if (bo == null) {
				bo = createTransactional(clz);
				transactionalBoCache.put(clz, bo);
			}
		}else{
			bo = createTransactional(clz);
		}
		
		return bo;
	}
	
	
	public static <T> T createTransactional(Class<T> clz) {
		return TxProxyCreator.newInstance(TxTransactionProxy.getInstance(TransactionDefinition.PROPAGATION_REQUIRED), clz);
	}
	
	public static <T> T createTransactional(Class<T> clz, int propagation) {
		return TxProxyCreator.newInstance(TxTransactionProxy.getInstance(propagation), clz);
	}
	 

}
