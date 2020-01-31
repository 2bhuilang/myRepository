package com.greatcattle.business;

import com.greatcattle.business.flow.BusOnlineThread;

/**
 * 业务主线程管理器
 * @author zhang.xian
 *
 */
public class MainThreadManager {
	
	private static  BusOnlineThread busOnlineThread =  new BusOnlineThread();
	
	
	public static void startMainThread() {
		if(busOnlineThread.isAlive()) {
			return;
		}
		busOnlineThread.setShutdown(false);
		busOnlineThread.start();
	}
	
	public static void stopThread() {
		busOnlineThread.setShutdown(true);
	}
	
	
}
