package com.greatcattle.service.staff;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.greatcattle.business.bo.StaffBO;
import com.greatcattle.dto.view.LogInVO;
import com.greatcattle.dto.vo.StaffInfoVO;
import com.greatcattle.freamwork.utils.SpringUtils;


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
		return null;
	}
	
	
	
	
}
