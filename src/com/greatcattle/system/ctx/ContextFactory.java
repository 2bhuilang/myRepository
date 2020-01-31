package com.greatcattle.system.ctx;

import java.util.Hashtable;
import java.util.Map;

import com.greatcattle.dto.vo.StaffInfoVO;
import com.greatcattle.utils.StrTools;

/**
 * 用户上下文信息
 * @author zhang.xian
 *
 */
public class ContextFactory {
	
	
	private static Map<String,UserContext> userContexts = new Hashtable();
	
	public static UserContext createUserContext(StaffInfoVO staffInfoVo) {
		if(staffInfoVo==null || StrTools.isEmpty(staffInfoVo.getStaffCode())) {
			return null;
		}
		synchronized (userContexts) {
			
			return userContexts.get(staffInfoVo.getStaffCode());
		} 
	}
	
	public UserContext getUserContext(String staffCode) {
		return userContexts.get(staffCode);
	}
	
	
	
}
