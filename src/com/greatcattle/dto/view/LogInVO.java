package com.greatcattle.dto.view;

/**
 * 登录传输对象
 * @author zhang.xian
 *
 */
public class LogInVO {
	
	//登录工号
	private String staffCode ="";
	//登录密码
	private String password = "";
	//验证码
	private String validationCode = "";

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getValidationCode() {
		return validationCode;
	}

	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}
	
	
}
