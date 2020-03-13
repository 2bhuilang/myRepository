package com.greatcattle.components.common.com;

import gnu.io.SerialPort;

/**
 * 串口交互基本配置
 * @author zhang.xian
 *
 */
public class ComConfig {
	private String portName = "";
	private int bitRate=0;
	
	private int databits = SerialPort.DATABITS_8;
	private int stopbits = SerialPort.STOPBITS_1;
	private int parity   =SerialPort.PARITY_NONE;
	
	private String extParam1 = "";
	private String extParam2= "";
	private String extParam3 = "";
	private String extParam4 = "";
	private String extParam5 = "";

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public int getBitRate() {
		return bitRate;
	}

	public void setBitRate(int bitRate) {
		this.bitRate = bitRate;
	}

	public int getDatabits() {
		return databits;
	}

	public void setDatabits(int databits) {
		this.databits = databits;
	}

	public int getStopbits() {
		return stopbits;
	}

	public void setStopbits(int stopbits) {
		this.stopbits = stopbits;
	}

	public int getParity() {
		return parity;
	}

	public void setParity(int parity) {
		this.parity = parity;
	}

	public String getExtParam1() {
		return extParam1;
	}

	public void setExtParam1(String extParam1) {
		this.extParam1 = extParam1;
	}

	public String getExtParam2() {
		return extParam2;
	}

	public void setExtParam2(String extParam2) {
		this.extParam2 = extParam2;
	}

	public String getExtParam3() {
		return extParam3;
	}

	public void setExtParam3(String extParam3) {
		this.extParam3 = extParam3;
	}

	public String getExtParam4() {
		return extParam4;
	}

	public void setExtParam4(String extParam4) {
		this.extParam4 = extParam4;
	}

	public String getExtParam5() {
		return extParam5;
	}

	public void setExtParam5(String extParam5) {
		this.extParam5 = extParam5;
	}
	
	
	
}
