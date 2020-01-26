package com.greatcattle.dataservice.common.dao;

import com.greatcattle.dataservice.common.util.AppConstants;
import com.greatcattle.dataservice.common.util.AppParamsConfig;
import com.greatcattle.utils.StrTools;



/**
 * @author Administrator
 *
 */
public class DatabaseFunction {
	public static final String DEFAULT_EXPIRE_DATE = "2050-12-01";
	private static String databaseType =AppParamsConfig.getInstance().getParamValue("DATABASE_TYPE");
	public static String getDefaultExpDate() {
		return "to_date('" + DEFAULT_EXPIRE_DATE + "','"+DatabaseFunction.date_pattern("yyyy-MM-dd")+"')";
	}
	/**
	 * 返回当前时间
	 * @return
	 */
	public static String current() {
		String returnValue = "current" ;
		if(StrTools.isEqual(databaseType,AppConstants.DB_TYPE_INFORMIX )){
			returnValue = "current" ;
		}else if(StrTools.isEqual(databaseType,AppConstants.DB_TYPE_ORACLE)){
			returnValue = "sysdate";
		}
		
		return returnValue ;
	}
	
	/**
	 * 返回to_date(),只返回日期，不包括时间
	 * @return
	 */
	public static String to_date(String value) {
		if( value == null || "".equals(value)){
			return "";
		}
		String returnValue = "" ;
		//value : '2007-01-01 01:01:01'   OR value : '2007-01-01'
		String[] datetime = value.split(" ") ;
		
		if( datetime.length == 1 ){
			if( databaseType.equals(AppConstants.DB_TYPE_INFORMIX)){
				returnValue = "to_date('" + value + "', '%Y-%m-%d')";
			}else if( databaseType.equals(AppConstants.DB_TYPE_ORACLE)){
				returnValue = "to_date('" + value + "','yyyy-mm-dd')";
			}
		}else if( datetime.length == 2 ){
			if( databaseType.equals(AppConstants.DB_TYPE_INFORMIX)){
				returnValue = "to_date('" + value + "', '%Y-%m-%d %H:%M:%S')";
			}else if( databaseType.equals(AppConstants.DB_TYPE_ORACLE)){
				returnValue = "to_date('" + value + "','yyyy-mm-dd hh24:mi:ss')";
			}			
		}
		
		return returnValue ;
	}
	
	private static int getDatePatternType(String pattern){
		return pattern.split(" ").length ;
	}
	
	public static String date_pattern(String pattern) {
		if (databaseType.equals(AppConstants.DB_TYPE_INFORMIX)) {
			if (pattern.startsWith("%"))
				return pattern;

			if (1 == getDatePatternType(pattern))
				return "%Y-%m-%d";

			return "%Y-%m-%d %H:%M:%S";
		} else {
			if (!pattern.startsWith("%"))
				return pattern;
			
			if (1 == getDatePatternType(pattern))
				return "yyyy-mm-dd";

			return "yyyy-mm-dd hh24:mi:ss";
		}
	}
	public static void main(String[] args ){
		System.out.println(date_pattern("yyyy-mm-dd hh24:mi:ss")); 
	}
	/**
	 * 返回相应月份
	 * @param exp
	 * @return
	 */
	/*public static String month( String exp ) {
		String returnValue = "month(" + exp + ")" ;
		if( databaseType.equals(AppConstants.DB_TYPE_INFORMIX)){
			returnValue = "month(" + exp + ")" ;
		}else if( databaseType.equals(AppConstants.DB_TYPE_ORACLE)){
			returnValue = "to_number(to_char(" + exp + ",'MM'))";
		}
		
		return returnValue ;
	}*/
	
	/**
	 * 返回当前时间
	 * @return
	 */
	/*public static String time() {
		String returnValue = "time" ;
		if( databaseType.equals(AppConstants.DB_TYPE_INFORMIX)){
			returnValue = "time" ;
		}else if( databaseType.equals(AppConstants.DB_TYPE_ORACLE)){
			returnValue = "to_char( sysdate, 'hh24:mi:ss')";
		}
		
		return returnValue ;
	}*/
	
	/**
	 * 返回当前系统时间
	 * @return
	 */
	/*public static String today(){
		String returnValue = "today" ;
		if( databaseType.equals(AppConstants.DB_TYPE_INFORMIX)){
			returnValue = "today" ;
		}else if( databaseType.equals(AppConstants.DB_TYPE_ORACLE)){
			returnValue = "sysdate";
		}
		
		return returnValue ;
	}*/
	
	/**
	 * 返回指定日期是星期几，0表示星期日
	 * @param exp
	 * @return
	 */
	/*public static String weekday( String exp ) {
		String returnValue = "weekday(" + exp + ")" ;
		if( databaseType.equals(AppConstants.DB_TYPE_INFORMIX)){
			returnValue = "weekday(" + exp + ")" ;
		}else if( databaseType.equals(AppConstants.DB_TYPE_ORACLE)){
			returnValue = "to_number(to_char(" + exp + ",'D') -1 )";
		}
		
		return returnValue ;
	}*/
	
	/**
	 * 返回指定日期对应年份
	 * @param exp
	 * @return
	 */
	/*public static String year( String exp ) {
		String returnValue = "year(" + exp + ")" ;
		if( databaseType.equals(AppConstants.DB_TYPE_INFORMIX)){
			returnValue = "year(" + exp + ")" ;
		}else if( databaseType.equals(AppConstants.DB_TYPE_ORACLE)){
			returnValue = "to_number(to_char(" + exp + ",'YYYY'))";
		}
		
		return returnValue ;
	}*/
	
	/**
	 * 返回指定格式日期
	 * @param format
	 * @return
	 */
	/*public static String date( String format ) {
		String returnValue = "date(" + format + ")" ;
		if( databaseType.equals(AppConstants.DB_TYPE_INFORMIX)){
			returnValue = "date(" + format + ")" ;
		}else if( databaseType.equals(AppConstants.DB_TYPE_ORACLE)){
			returnValue = "to_date(to_char('" + format + "','mm/dd/yyyy'))";
		}
		
		return returnValue ;
	}*/
	
	/**
	 * 返回指定日期是某月的第几天
	 * @param dtimeExp
	 * @return
	 */
	/*public static String day(String dtimeExp ) {
		String returnValue = "day(" + dtimeExp + ")" ;
		if( databaseType.equals(AppConstants.DB_TYPE_INFORMIX)){
			returnValue = "day(" + dtimeExp + ")" ;
		}else if( databaseType.equals(AppConstants.DB_TYPE_ORACLE)){
			returnValue = "to_number(to_char('" + dtimeExp + "','dd'))";
		}
		
		return returnValue ;
	}*/
	public static String getDataFormat(int type) {
		String returnValue = "sysdate";
		if (1 == type) {
			if (databaseType.equals(AppConstants.DB_TYPE_INFORMIX)) {
				returnValue = "'%Y-%m-%d'";
			} else if (databaseType.equals(AppConstants.DB_TYPE_ORACLE)) {
				returnValue = "'yyyy-mm-dd'";
			}
		}
		if (2 == type) {
			if (databaseType.equals(AppConstants.DB_TYPE_INFORMIX)) {
				returnValue = "'%Y-%m-%d %H:%M:%S'";
			} else if (databaseType.equals(AppConstants.DB_TYPE_ORACLE)) {
				returnValue = "'yyyy-mm-dd hh24:mi:ss'";
			}
		}
		if (3 == type) {
			if (databaseType.equals(AppConstants.DB_TYPE_INFORMIX)) {
				returnValue = "'%Y%m%d%H%M%S'";
			} else if (databaseType.equals(AppConstants.DB_TYPE_ORACLE)) {
				returnValue = "'yyyymmddhh24miss'";
			}
		}
		return returnValue;
	}
	
	/**
	 * 返回当前日期
	 * @return
	 * @author li.yuanyang
	 */
	public static String currentDay() {
		String returnValue = "sysdate";
		if (databaseType.equals(AppConstants.DB_TYPE_INFORMIX)) {
			returnValue = "today";
		} else if (databaseType.equals(AppConstants.DB_TYPE_ORACLE)) {
			returnValue = "trunc(sysdate)";
		}

		return returnValue;
	}
}
