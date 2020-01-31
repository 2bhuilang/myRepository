package com.greatcattle.system.ctx;

import com.greatcattle.dto.vo.StaffInfoVO;

/**
 * 用户上下文，用户保存用户状态，当前登录用户装的工号，权限，工号对应的流程权限等 信息
 * @author zhang.xian
 *
 */
public class UserContext {
	 	
	private  StaffInfoVO staffInfoVo  = null;
	
	private String loginTime = "";

	public StaffInfoVO getStaffInfoVo() {
		return staffInfoVo;
	}

	public void setStaffInfoVo(StaffInfoVO staffInfoVo) {
		this.staffInfoVo = staffInfoVo;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	
	
		
}
