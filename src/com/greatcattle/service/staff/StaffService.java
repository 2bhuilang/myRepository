package com.greatcattle.service.staff;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.greatcattle.business.bo.StaffBO;
import com.greatcattle.dto.view.LogInVO;
import com.greatcattle.dto.vo.StaffInfoVO;
import com.greatcattle.framework.utils.SpringUtils;
import com.greatcattle.system.ctx.AppSysContext;
import com.greatcattle.system.ctx.ContextFactory;
import com.greatcattle.system.ctx.UserContext;
import com.greatcattle.utils.StrTools;


@Service
public class StaffService {
	
	 
	/**
	 * 验证登录
	 * @param loginVo
	 * @return
	 */
	public Map login(LogInVO loginVo) {
		StaffBO staffBo = SpringUtils.getBean(StaffBO.class);
		StaffInfoVO staffInfoVo = staffBo.getStaffInfo(loginVo.getStaffCode());
		
		if(StrTools.isEqual(staffInfoVo.getPassWord(), loginVo.getPassword())) {
			throw new RuntimeException("密码验证失败");
		}
		
		UserContext userContext = ContextFactory.createUserContext(staffInfoVo);
		AppSysContext.getSysCtx().setCurrentUserContext( userContext);
		
		return null;
	}
	
	
	
	
}
