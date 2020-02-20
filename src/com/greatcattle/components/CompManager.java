package com.greatcattle.components;

import com.greatcattle.components.drivers.camera.impl.stand.StandardCameraImpl;
import com.greatcattle.components.drivers.weighbridge.impl.stand.StandardWeighBridgeImpl;
import java.awt.Panel;

/**
 * 设备管理
 * 关系当前系统的需要加载的设备
 * 
 * @author zhang.xian
 *
 */
public class CompManager {
	
        private CompManager(){
            
        }
        private static CompManager compManager = null;
        public static  CompManager initCompManager(java.awt.Panel panelRealplay){
            if(compManager!=null){
                return compManager ;
             }
            compManager =  new CompManager();
            compManager.panelRealplay = panelRealplay;
            compManager.initComps();
            return compManager;
        }
        
        public CompManager getCompManager(){
            if(compManager!=null){
                return compManager ;
             }
            return  null ;
        }
        
        
         
	private java.awt.Panel panelRealplay;
	/**
	 * 初始化所有设备
	 */
	public void initComps() {
		
		//设置摄像头设备
		CompRegCenter.getCompRegCenter().regCameraApi(new StandardCameraImpl(panelRealplay));
		// 注册地磅设备
		CompRegCenter.getCompRegCenter().regWeighBridgeApi( new StandardWeighBridgeImpl());
		 
	} 

        public Panel getPanelRealplay() {
            return panelRealplay;
        }

        public void setPanelRealplay(Panel panelRealplay) {
            this.panelRealplay = panelRealplay;
        }
	
}
