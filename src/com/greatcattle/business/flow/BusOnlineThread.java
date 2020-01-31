package com.greatcattle.business.flow;

/**
 * 业务在线线程
 * @author zhang.xian
 *
 */
public class BusOnlineThread extends Thread{
	
	 private boolean shutdown = false;
	
	 public void run() {
		 while(!shutdown) {
			//1 红外设备监控车辆是否进入，
			//2.检测进入随即创建业务服务引擎读取各设备数据完善业务过程
			try {
				 ServiceFlowEngine.createServiceFlowEngine().dobus();
			} catch (Exception e) {
				
			} 
			 //3.完成则重新初始化设备状态等待下一辆车辆进入 
		 }
	 }

	public boolean isShutdown() {
		return shutdown;
	}

	public void setShutdown(boolean shutdown) {
		this.shutdown = shutdown;
	}
	 
	 
}
