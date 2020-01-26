package com.greatcattle.freamwork;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.greatcattle.freamwork.utils.SpringUtils;

/**
 * springӦ�ò�ѯ������
 * @author zhang.xian
 *
 */
public class ContextApplication {
	
	private static ApplicationContext appContext = null;
	public static synchronized void  initCtx() {
		if(true) {
			return;
		}
		if(appContext!=null) {
			return;
		}
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml"); 
		appContext = context;
		SpringUtils.setApplicationContext(appContext);
		
	}
	 
}
