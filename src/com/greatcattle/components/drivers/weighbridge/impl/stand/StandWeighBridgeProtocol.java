package com.greatcattle.components.drivers.weighbridge.impl.stand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.greatcattle.components.common.com.ComConfig;
import com.greatcattle.components.common.com.listener.ListenerCallBack;

/**
 * 地磅设备通讯协议
 * 存放标准的指令集
 * @author zhang.xian
 *
 */
public class StandWeighBridgeProtocol {
	
	static class StandWeighBridgeCallBack  implements ListenerCallBack { 
		
		private ComConfig comConfig = null ;
		
		private JLabel acceptPannel =  null ; 
		
		private List<Integer>  realWeights =  new ArrayList();
		
		private List<Float> maxList = new ArrayList();
		
		public JLabel getAcceptPannel() {
			return acceptPannel;
		}
 
		public void setAcceptPannel(JLabel acceptPannel) {
			this.acceptPannel = acceptPannel;
		}
		
		public void addMaxList(float avgWeight) {
			int maxSize = Integer.parseInt( this.comConfig.getExtParam2());
			if(maxList.size()<maxSize) {
				maxList.add( avgWeight);
			}else {
				//取出最小值，并将最小值替换为最新值；
				Float minItem = Collections.min(maxList);
				if(avgWeight>minItem) {
					maxList.remove(minItem );
					maxList.add(avgWeight );
				} 
			}
		}
		
		public float getMaxInList() {
			return Collections.max(this.maxList);
		}
 
		@Override
		public void callBack(String portData) {
			
			int realWeight = analysisData(portData);
			realWeights.add(realWeight);
			int realLength = Integer.parseInt( this.comConfig.getExtParam1());
			if(realWeights.size()>=realLength) {
				List<Integer> subList = realWeights.subList(0, realLength);
				realWeights.clear();
				int allWeight = 0;
				float avgWeight = 0;
				for(int i=0;i<subList.size();i++ ) {
					allWeight = allWeight+subList.get(i);
				}
				avgWeight = allWeight/subList.size();
				acceptPannel.setText(avgWeight+"");
			} 
		}

		@Override
		public void setComConfig(ComConfig comConfig) {
			this.comConfig =  comConfig ;
			
		}
	}
	
	static class StandWeighBridgeStopCallBack  implements ListenerCallBack {

		@Override
		public void callBack(String portData) {
			 
			
		}

		@Override
		public void setAcceptPannel(JLabel acceptPannel) {
		 
			
		}

		@Override
		public float getMaxInList() { 
			
			return 0;
		}

		@Override
		public void setComConfig(ComConfig comConfig) { 
			
		}
	
	}
	
	
	/**
	 * 解析反馈数据
	 * @param portData
	 * @return
	 */
	private static  int analysisData(String portData) {
		
		return 100 ;
	}
	
	
	
}
