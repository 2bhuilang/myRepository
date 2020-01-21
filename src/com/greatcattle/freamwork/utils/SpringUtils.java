package com.greatcattle.freamwork.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * spring fac
 * @author zhang.xian
 *
 */
public class SpringUtils {

	private static ApplicationContext applicationContext;
	
	/**
	 * 设置上下文
	 * @param applicationContext
	 * @throws BeansException
	 */ 
	public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
		SpringUtils.applicationContext = applicationContext;
	}
	
	/**
	 * 返回上下文
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends ApplicationContext> T getApplicationContext(){
		return (T)applicationContext;
	}
	
	/**
	 * 根据bean的name返回bean实例
	 * @param <T>
	 * @param name
	 * @return
	 * @throws BeansException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException{
		return (T)applicationContext.getBean(name);
	}
	
	/**
	 * 根据bean的name返回bean实例
	 * @param <T>
	 * @param name
	 * @return
	 * @throws BeansException
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBeanByClazzPath(String clazzPath) throws BeansException, ClassNotFoundException{
		return (T)applicationContext.getBean(Class.forName(clazzPath));
	}
	
	/**
	 * 根据bean的name和class类型返回bean实例
	 * @param <T>
	 * @param name
	 * @param requiredType
	 * @return
	 * @throws BeansException
	 */
	public static <T> T getBean(String name, Class<T> requiredType) throws BeansException{
		return applicationContext.getBean(name, requiredType);
	}
	
	/**
	 * 根据class类型返回bean实例
	 * @param <T>
	 * @param requiredType
	 * @return
	 * @throws BeansException
	 */
	public static <T> T getBean(Class<T> requiredType) throws BeansException{
		return applicationContext.getBean(requiredType);
	}
	
	/**
	 * BeanFactory是否包含此name的实例
	 * @param name
	 * @return
	 */
	public static boolean containsBean(String name){
		return applicationContext.containsBean(name);
	}
	
	/**
	 * 根据bean的name判断该bean是否单例
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException{
		return applicationContext.isSingleton(name);
	}
	
	/**
	 * 根据bean的name判断该bean是否多例
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static boolean isPrototype(String name) throws NoSuchBeanDefinitionException{
		return applicationContext.isPrototype(name);
	}
	
	/**
	 * 根据bean的name判断该bean是否实现了class类型接口
	 * @param name
	 * @param targetType
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static boolean isTypeMatch(String name, Class<?> targetType) throws NoSuchBeanDefinitionException{
		return applicationContext.isTypeMatch(name, targetType);
	}
	
	/**
	 * 根据bean的name返回该bean的class类型
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static Class<?> getType(String name) throws NoSuchBeanDefinitionException{
		return applicationContext.getType(name);
	}
	
	/**
	 * 根据bean的name返回该bean的别名
	 * @param name
	 * @return
	 */
	public static String[] getAliases(String name){
		return applicationContext.getAliases(name);
	}
	
	/**
	 * 从spring中取bean，没有就注册一个
	 * @param <T>
	 * @param name
	 * @param requiredType
	 * @return
	 */
	public static <T> T getOrRegisterBean(String name, Class<T> requiredType){
		if(requiredType == null){
			return null;
		}
		
		ConfigurableApplicationContext appContext = getApplicationContext();
		T bean = null;
		try{
			bean = appContext.getBean(name, requiredType);
		}
		catch(NoSuchBeanDefinitionException e){
			
		}
		
		if(bean == null){
			DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)appContext.getBeanFactory();
			BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(requiredType);
			beanFactory.registerBeanDefinition(name, beanDefinitionBuilder.getRawBeanDefinition());
			
			bean = appContext.getBean(name, requiredType);
		}
		
		return bean;
	}
	
}
