package com.greatcattle.freamwork;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.greatcattle.freamwork.utils.SpringUtils;

/**
 * spring上下文初始化
 * @author zhang.xian
 *
 */
public class ContextApplication {
	
	private static ApplicationContext appContext = null;
	public static synchronized void  initCtx() {
		 
		if(appContext!=null) {
			return;
		}
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml"); 
		appContext = context;
		SpringUtils.setApplicationContext(appContext);
		
	}
	 
}
