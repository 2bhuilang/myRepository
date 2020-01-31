package com.greatcattle.framework.utils;

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
	 * ����������
	 * @param applicationContext
	 * @throws BeansException
	 */ 
	public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
		SpringUtils.applicationContext = applicationContext;
	}
	
	/**
	 * ����������
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends ApplicationContext> T getApplicationContext(){
		return (T)applicationContext;
	}
	
	/**
	 * ����bean��name����beanʵ��
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
	 * ����bean��name����beanʵ��
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
	 * ����bean��name��class���ͷ���beanʵ��
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
	 * ����class���ͷ���beanʵ��
	 * @param <T>
	 * @param requiredType
	 * @return
	 * @throws BeansException
	 */
	public static <T> T getBean(Class<T> requiredType) throws BeansException{
		return applicationContext.getBean(requiredType);
	}
	
	/**
	 * BeanFactory�Ƿ������name��ʵ��
	 * @param name
	 * @return
	 */
	public static boolean containsBean(String name){
		return applicationContext.containsBean(name);
	}
	
	/**
	 * ����bean��name�жϸ�bean�Ƿ���
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException{
		return applicationContext.isSingleton(name);
	}
	
	/**
	 * ����bean��name�жϸ�bean�Ƿ����
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static boolean isPrototype(String name) throws NoSuchBeanDefinitionException{
		return applicationContext.isPrototype(name);
	}
	
	/**
	 * ����bean��name�жϸ�bean�Ƿ�ʵ����class���ͽӿ�
	 * @param name
	 * @param targetType
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static boolean isTypeMatch(String name, Class<?> targetType) throws NoSuchBeanDefinitionException{
		return applicationContext.isTypeMatch(name, targetType);
	}
	
	/**
	 * ����bean��name���ظ�bean��class����
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static Class<?> getType(String name) throws NoSuchBeanDefinitionException{
		return applicationContext.getType(name);
	}
	
	/**
	 * ����bean��name���ظ�bean�ı���
	 * @param name
	 * @return
	 */
	public static String[] getAliases(String name){
		return applicationContext.getAliases(name);
	}
	
	/**
	 * ��spring��ȡbean��û�о�ע��һ��
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
