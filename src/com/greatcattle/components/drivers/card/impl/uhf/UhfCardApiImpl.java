package com.greatcattle.components.drivers.card.impl.uhf;

import com.greatcattle.components.drivers.card.CardApi;
import com.greatcattle.components.drivers.card.CardConfig;
import com.greatcattle.components.drivers.card.impl.uhf.UHF18.UHFReader;
import com.greatcattle.utils.StrTools;

/**
 * uhf卡 实现
 * @author zhang.xian
 *
 */
public class UhfCardApiImpl implements CardApi{
	
	private int fCmdRet=0x30;  
	private UHFReader uhf = new UHFReader();
	private  boolean checkFlag = false;
	
	private CardReadCallBack callBack =  null ;
	

	private  CardConfig cardConfig = null ;
	public UhfCardApiImpl(CardConfig cardConfig) {
		this.cardConfig =  cardConfig;
	}
	
	
	@Override
	public void connect() {
		
		fCmdRet=uhf.OpenByCom(Integer.parseInt(cardConfig.getComPort()), 
				Byte.parseByte( cardConfig.getComAddr()), 
				Byte.parseByte(cardConfig.getBaudRate())) ;
		
		if(fCmdRet==0){
			checkThread.start();
		}
	}
	public void disConnect() { 
		fCmdRet=uhf.CloseByCom(uhf.FrmHandle);
	}
	
	public void startCheck() {
		 this.checkFlag = true;
	}
	
	public void stopCheck() {
		this.checkFlag = false;
	}
	
	/**
	 * 执行业务过程
	 * @param cardNum
	 */
	public void doBus(String cardNum) {
		if(callBack==null) {
			return;
		}
		callBack.setCardNum(cardNum);
		callBack.callBack();
		
	} 
	
	
	private String readCheckCard() {
		String[] EPC;
		String sepc  = null ;
		EPC=uhf.Inventory();
		if(EPC != null ){
			
			for(int m=0;m<EPC.length;m++) {
				sepc=EPC[m]; 
				if(StrTools.isNotEmpty(sepc)) {
					break;
				} 
			} 
		}  
		return sepc;
	}
	
	private String readCard(String epc) {
		 
		byte WordPtr = (byte)(0xff & Integer.parseInt(this.cardConfig.getText_addr(),16));
		byte Num =(byte)(0xff & Integer.parseInt(this.cardConfig.getText_num(),16));
		byte Mem=0;
		byte[] psd = uhf.stringToByte(this.cardConfig.getText_psd());
		
		String result=uhf.ReadData(epc,WordPtr,Num,Mem,psd);
		
		return result;
	}
	
	private Thread checkThread = new Thread(new Runnable() { 
		@Override
		public void run() {  
			while(true) {
				if(!checkFlag) {
					try {
						Thread.sleep(1000);
						continue;
					} catch (InterruptedException e) { 
						e.printStackTrace();
					}
				}
				String sepc = readCheckCard(); 
				String result = readCard(sepc);
				doBus(result);
			}  
		}  
	}) ;
	
	
}
