package com.greatcattle.dataservice.util;

import com.greatcattle.dataservice.service.DBService;
import com.greatcattle.framework.utils.SpringUtils;

/**
 * 
 * @author zhang.xian
 *
 */
public class DBUtils {
	/**
	 * 逻辑指定数据源
	 * @param dbCode
	 * @return
	 */
	public static DBService getDBService(){
		 
		return SpringUtils.getBean(DBService.class); 
		
		
	}
	
}
