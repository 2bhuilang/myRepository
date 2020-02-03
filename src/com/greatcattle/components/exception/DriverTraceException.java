package com.greatcattle.components.exception;

/**
 * 设备交互异常
 * @author zhang.xian
 *
 */
public class DriverTraceException extends RuntimeException{
	
	private  String code = "";
	public DriverTraceException(String code,String msg) {
		super(msg);
		this.code = code;
	}
	
}
