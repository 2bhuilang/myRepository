package com.greatcattle.components.common.com;

/**
 * 串口命令 返回接口
 * @author zhang.xian
 *
 */
public class ComCommandResult {
	
	 private  boolean sucess = false;
	 
	 private  String resultCode = "";

	public boolean isSucess() {
		return sucess;
	}

	public void setSucess(boolean sucess) {
		this.sucess = sucess;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
 
	 
}
