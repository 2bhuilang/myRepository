package com.greatcattle.utils;

import java.util.HashMap;

import java.util.Iterator;
import java.util.Map;

public class Const {
	public static final String ACTION_METHOD = "execMethod" ;
	
	public static final String ACTION_RESULT = "RESULT" ;

	public static final String ACTION_PARAMETER = "PARAMETER" ;
	
	public static final String ACTION_ERROR = "ERROR" ;
	 
	
	public static final String PAGE_PAGESIZE = "pageSize" ;
	public static final String PAGE_PAGEINDEX = "pageIndex" ;
	public static final int UN_JUDGE_ERROR = -999999999 ;  //这种异常就跳过，不需要处理。
	
	// 查找变量的正则表达式，如${var1}这样的格式
	public static final String FIND_QUERY_VARIABLE_REXP = "\\u0024\\u007B\\S+?}";
	
	private static Map getEmptyMap(){
		return new HashMap() ;
	}
	
	public static int getPageSize(Map m ){
		return ((Integer)m.get("pageSize")).intValue() ;
	}
	
	public static int getPageIndex(Map m ){
		return ((Integer)m.get("pageIndex")).intValue() ;
	}
	
	/**
	 * 根据tStr字段 构建新map
	 * @param sm  原map
	 * @param tStr 从原map，根据tStr字段作为key，构建需要的map
	 * @return
	 */
	public static Map getMapForTargetStr(Map sm , String tStr){
		if(sm == null || sm.isEmpty() ||
				tStr == null || "".equals(tStr.trim()))
			return getEmptyMap() ; 
		Map tm = new HashMap() ;
		Iterator it = sm.keySet().iterator() ;
		String[] tStrArray = tStr.split(",") ;
		for(int i = 0 , j= tStrArray.length ; i< j ; i++){
			String n = tStrArray[i] ;
			if(sm.get(n) != null ){
				tm.put(n, (String)sm.get(n));
			}
		}
		return tm ;
	}
	
	public static boolean containValue(Map m , String name ){
		return m.get(name) != null ;
	}
	
	public static  boolean containStrValue(Map m , String name) {
		if( !containValue( m , name ) )
			return false ;
		String t = (String)m.get(name) ;
		return t != null && !"".equals(t.trim()) ;
	}
	
	public static  String getStrValue(Map m , String name) {
		Object t = m.get(name) ;
		if(t == null )
			return "" ;
		return ((String)m.get(name)).trim() ;
	}
}
