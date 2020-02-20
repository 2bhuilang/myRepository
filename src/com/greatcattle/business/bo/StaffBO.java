package com.greatcattle.business.bo;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.greatcattle.dataservice.common.SqlMapExe;
import com.greatcattle.dto.vo.StaffInfoVO;
import com.greatcattle.utils.AppBeanUtil;

/**
 * 
 * @author zhang.xian
 *
 */
@Service
public class StaffBO {
	
	
	public StaffInfoVO getStaffInfo(String staffCode) {
		
		String sql = "";//a
		
		Map data = SqlMapExe.getInstance().queryForMapBySql(sql, new String[] {staffCode});
		 
		
		return AppBeanUtil.queryResultMapToBean(data, StaffInfoVO.class);
	}
	
	
	
}
