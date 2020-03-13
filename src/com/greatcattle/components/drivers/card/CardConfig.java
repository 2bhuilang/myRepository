package com.greatcattle.components.drivers.card;

/**
 * 内部卡配置
 * @author zhang.xian
 *
 */
public class CardConfig {
	private String comPort = "";
	private String comAddr = "";
	private String baudRate = "";
	
	private String text_addr = "";
	private String text_num = "";
	private String mem = "";
	private String text_psd = ""; 
	 
	 
	public String getComPort() {
		return comPort;
	}
	public void setComPort(String comPort) {
		this.comPort = comPort;
	}
	public String getComAddr() {
		return comAddr;
	}
	public void setComAddr(String comAddr) {
		this.comAddr = comAddr;
	}
	public String getBaudRate() {
		return baudRate;
	}
	public void setBaudRate(String baudRate) {
		this.baudRate = baudRate;
	}
	public String getText_addr() {
		return text_addr;
	}
	public void setText_addr(String text_addr) {
		this.text_addr = text_addr;
	}
	public String getText_num() {
		return text_num;
	}
	public void setText_num(String text_num) {
		this.text_num = text_num;
	}
	public String getMem() {
		return mem;
	}
	public void setMem(String mem) {
		this.mem = mem;
	}
	public String getText_psd() {
		return text_psd;
	}
	public void setText_psd(String text_psd) {
		this.text_psd = text_psd;
	}
	
	
	
	
}
