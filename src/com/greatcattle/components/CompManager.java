package com.greatcattle.components;

import java.awt.Panel;

import com.greatcattle.components.drivers.camera.impl.stand.StandCameraConfig;
import com.greatcattle.components.drivers.camera.impl.stand.StandardCameraImpl;
import com.greatcattle.components.drivers.plc.impl.taida.TaiDaPlcApiImpl;
import com.greatcattle.components.drivers.weighbridge.impl.stand.StandardWeighBridgeImpl;
import com.greatcattle.framework.utils.SpringUtils;

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
	
	private java.awt.Panel panelPicRealplay;
	
	/**
	 * 初始化所有设备
	 */
	public void initComps() {
		
		StandCameraConfig surveillanceCameras =  SpringUtils.getBean("surveillanceCameras"); 
		StandCameraConfig licensePlateCameras =  SpringUtils.getBean("licensePlateCameras"); 
		 
		//设置监控摄像头设备
		CompRegCenter.getCompRegCenter().regCameraApi(new StandardCameraImpl(panelRealplay,surveillanceCameras));
		
		//设置车牌抓拍摄像头设备
		CompRegCenter.getCompRegCenter().regPicCameraApi(new StandardCameraImpl(panelPicRealplay,licensePlateCameras));
		
		// 注册地磅设备
		CompRegCenter.getCompRegCenter().regWeighBridgeApi( new StandardWeighBridgeImpl());
		
		//注册plc设备
		CompRegCenter.getCompRegCenter().regPLCApi( new TaiDaPlcApiImpl()); 
		
		//注册设备后初始化所有设备
		CompRegCenter.getCompRegCenter().initAllComps();
		
		
	} 

    public Panel getPanelRealplay() {
        return panelRealplay;
    }

    public void setPanelRealplay(Panel panelRealplay) {
        this.panelRealplay = panelRealplay;
    }
	
}
