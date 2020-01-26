/**
 *
 */
package com.greatcattle.dataservice.common;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.greatcattle.dataservice.common.dao.ConnectionContext;
import com.greatcattle.dataservice.common.dao.DAOSQLUtils;
import com.greatcattle.dataservice.common.dao.DAOSystemException;
import com.greatcattle.dataservice.common.dao.DAOUtils;
import com.greatcattle.dataservice.common.util.JNDINames;
import com.greatcattle.utils.Base64Encoder;

@SuppressWarnings({"rawtypes","unchecked","deprecation", "serial"})
public class SqlMapExeUtil implements java.io.Serializable{

	private static final Log LOG = LogFactory.getLog(SqlMapExeUtil.class);

	private static SqlMapExeUtil instance;

	private static String databaseType = DAOUtils.getDatabaseType();

	//从数据获取最大的记录条数
	public static final int MAX_SIZE = 2000;

	private SqlMapExeUtil() {

	}

	public static SqlMapExeUtil getInstance() {

		if (instance == null)
			instance = new SqlMapExeUtil();
		return instance;
	}

    public List queryListForList(String sql, String[] sqlParams, int num) {
        List list = new ArrayList();
        Connection dbConnection = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            dbConnection = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);
            String sqlStr = DAOSQLUtils.getFilterSQL(sql);
            stmt = dbConnection.prepareStatement(sqlStr);
            for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
                //////Debug.print("sqlParams[" + i + "]=" + sqlParams[i]);
            	DAOUtils.dealParam(i, sqlParams[i]);
                stmt.setString(i + 1, sqlParams[i]);
            }          
            
            result = stmt.executeQuery();
            while (result.next()) {
                List list2 = new ArrayList();
                for (int i = 1; i < num + 1; i++) {
                    list2.add(DAOUtils.trimStr(result.getString(i)));
                }
                list.add(list2);
            }
        } catch (Exception se) {
            throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
        } finally {
            DAOUtils.closeResultSet(result, this);
            DAOUtils.closeStatement(stmt, this);

        }
        return list;
    }	
	
	public List queryForStringListEx(String sql, String[] sqlParams, int num) {
		List list = new ArrayList();
		Connection dbConnection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			dbConnection = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);
			String sqlStr = DAOSQLUtils.getFilterSQL(sql);
			stmt = dbConnection.prepareStatement(sqlStr);
			for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
				//////Debug.print("sqlParams[" + i + "]=" + sqlParams[i]);
				DAOUtils.dealParam(i, sqlParams[i]);
				stmt.setString(i + 1, sqlParams[i]);
			}
			result = stmt.executeQuery();
			if (result.next()) {
				for (int i = 1; sqlParams != null && i < num + 1; i++) {
					list.add(DAOUtils.trimStr(result.getString(i)));
				}

			}
		} catch (Exception se) {
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(result, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return list;
	}

	public List queryForStringListEx(String sql, String[] sqlParams) {
		List list = new ArrayList();
		Connection dbConnection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			dbConnection = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);
			String sqlStr = DAOSQLUtils.getFilterSQL(sql);
			stmt = dbConnection.prepareStatement(sqlStr);
			for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
				//////Debug.print("sqlParams[" + i + "]=" + sqlParams[i]);
				DAOUtils.dealParam(i, sqlParams[i]);
				stmt.setString(i + 1, sqlParams[i]);
			}
			result = stmt.executeQuery();
			int k = 0;
			while (result.next()&& k <= MAX_SIZE) {
				list.add(DAOUtils.trimStr(result.getString(1)));
				k++;
			}
		} catch (Exception se) {
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(result, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return list;
	}
	
	
	public List queryForStringListByStringList(String sql, List<String> sqlParams) {
		List list = new ArrayList();
		Connection dbConnection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String sqlStrParams="";
		for(int j=0;j<sqlParams.size();j++){
			if(j!=sqlParams.size()-1){
				sqlStrParams=sqlStrParams+sqlParams.get(j)+",";
			}
			else
			{
				sqlStrParams=sqlStrParams+sqlParams.get(j);
			}
		}
		try {
			dbConnection = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);
			String sqlStr = DAOSQLUtils.getFilterSQL(sql.replaceAll("\\?", sqlStrParams));
			stmt = dbConnection.prepareStatement(sqlStr);
			result = stmt.executeQuery();
			int k = 0;
			while (result.next()&& k <= MAX_SIZE) {
				list.add(DAOUtils.trimStr(result.getString(1)));
				k++;
			}
		} catch (Exception se) {
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(result, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return list;
	}

	
	public String querySingleValueByStringList(String sql, List<String> params) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String returnValue = "";
		String sqlStrParams="";
		for(int j=0;j<params.size();j++){
			if(j!=params.size()-1){
				sqlStrParams=sqlStrParams+params.get(j)+",";
			}
			else
			{
				sqlStrParams=sqlStrParams+params.get(j);
			}
		}
		String sqlFinal = DAOSQLUtils.getFilterSQL(sql.replaceAll("\\?", sqlStrParams));

		try {

			//对误调用处理

			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sqlFinal, DAOSQLUtils.CRM_DB));
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = DAOUtils.trimStr(result.getString(1));
			}
		} catch (Exception se) {
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(result, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return returnValue;
	}
	
	public String queryValueBySqlAndCond(String sql, String param) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String returnValue = "";

		try {
			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));

			if (param != null) {
				DAOUtils.dealParam(0, param);
				stmt.setString(1, param);
			}

			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = DAOUtils.trimStr(result.getString(1));
			}
		} catch (Exception se) {
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(result, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return returnValue;
	}

	public String queryValueBySqlAndCond(String sql, String params[]) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String returnValue = "";

		try {
			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));

			for (int i = 0; params != null && i < params.length; i++) {
				DAOUtils.dealParam(i, params[i]);
				stmt.setString(i + 1, params[i]);
			}
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = DAOUtils.trimStr(result.getString(1));
			}
		} catch (Exception se) {
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(result, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return returnValue;
	}

	public String queryValueBySqlAndCond(String sql, List params) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String returnValue = "";

		try {
			if (params == null) {
				params = new ArrayList();
			}
			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);
			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			for (int i = 0, argsCnt = params.size(); i < argsCnt; i++) {
				DAOUtils.dealParam(i, (String) params.get(i));
				stmt.setString(i + 1, (String) params.get(i));
			}
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = DAOUtils.trimStr(result.getString(1));
			}
		} catch (Exception se) {
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(result, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return returnValue;
	}

	/*public String querySingleValue(String sqlname, String param) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String returnValue = "";

		String sql = Sqls.getSql(sqlname);

		try {
			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));

			if (param != null) {
				DAOUtils.dealParam(0, param);
				stmt.setString(1, param);
			}

			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = DAOUtils.trimStr(result.getString(1));
			}
		} catch (Exception se) {
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(result, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return returnValue;
	}

	public String querySingleValue(String sqlname, List params) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String returnValue = "";

		String sql = Sqls.getSql(sqlname);

		try {

			//对误调用处理
			if(StrTools.isEmpty(sql)){
				sql=sqlname;
			}
			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			if (params == null) {
				params = new ArrayList();
			}
			for (int i = 0, argsCnt = params.size(); i < argsCnt; i++) {
				DAOUtils.dealParam(i, (String) params.get(i));
				stmt.setString(i + 1, (String) params.get(i));
			}
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = DAOUtils.trimStr(result.getString(1));
			}
		} catch (Exception se) {
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(result, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return returnValue;
	}
	
	public String querySingleValue(String sqlname, List params , String jndiName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String returnValue = "";

		String sql = Sqls.getSql(sqlname);

		try {

			//对误调用处理
			if(StrTools.isEmpty(sql)){
				sql=sqlname;
			}
			conn = ConnectionContext.getContext().getConnection(jndiName);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			if (params == null) {
				params = new ArrayList();
			}
			for (int i = 0, argsCnt = params.size(); i < argsCnt; i++) {
				DAOUtils.dealParam(i, (String) params.get(i));
				stmt.setString(i + 1, (String) params.get(i));
			}
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = DAOUtils.trimStr(result.getString(1));
			}
		} catch (Exception se) {
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(result, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return returnValue;
	}*/

	public String querySingleValueBySql(String sql, List params) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String returnValue = "";

		try {
			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			if (params == null) {
				params = new ArrayList();
			}
			for (int i = 0, argsCnt = params.size(); i < argsCnt; i++) {
				DAOUtils.dealParam(i, (String) params.get(i));
				stmt.setString(i + 1, (String) params.get(i));
			}
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = DAOUtils.trimStr(result.getString(1));
			}
		} catch (Exception se) {
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(result, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return returnValue;
	}

	public String querySingleValue(String sql, String[] params) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String returnValue = "";
		try {
			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);
			if (sql.indexOf("dual") > -1 || sql.indexOf("DUAL") > -1) {
				stmt = conn.prepareStatement(sql);
			} else {
				stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			}

			for (int i = 0; i < params.length; i++) {
				DAOUtils.dealParam(i, params[i]);
				stmt.setString(i + 1, params[i]);
			}
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = DAOUtils.trimStr(result.getString(1));
			}
		} catch (Exception se) {
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(result, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return returnValue;
	}
	//add by AyaKoizumi 101217
	public String querySingleValue(String sql, String[] params,String jndiName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String returnValue = "";
		try {
			conn = ConnectionContext.getContext().getConnection(jndiName);
			if (sql.indexOf("dual") > -1 || sql.indexOf("DUAL") > -1) {
				stmt = conn.prepareStatement(sql);
			} else {
				stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			}

			for (int i = 0; i < params.length; i++) {
				DAOUtils.dealParam(i, params[i]);
				stmt.setString(i + 1, params[i]);
			}
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = DAOUtils.trimStr(result.getString(1));
			}
		} catch (Exception se) {
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(result, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return returnValue;
	}
	public String querySingleValue(String sql, ArrayList params) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String returnValue = "";
		try {
			if (params == null) {
				params = new ArrayList();
			}
			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);
			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			for (int i = 0, argsCnt = params.size(); i < argsCnt; i++) {
				DAOUtils.dealParam(i,(String) params.get(i));
				stmt.setString(i + 1, (String) params.get(i));
			}
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = DAOUtils.trimStr(result.getString(1));
			}
		} catch (Exception se) {
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(result, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return returnValue;
	}

	/*public Map queryForMap(String sqlname, String param) throws DAOSystemException {

		Map map = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = Sqls.getSql(sqlname);
		try {

			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

//			System.out.println(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			
			DAOUtils.dealParam(0,param);
			stmt.setString(1, param);

			rs = stmt.executeQuery();

			if (rs.next()) {

				map = this.rowToMap(rs);
			}

			return map;
		} catch (SQLException se) {
			////Debug.print(se.toString(), this);
			////Debug.print(sql, this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);

		}

	}*/


	public Map queryForMapBySql(String sql, String[] param) throws DAOSystemException {

		Map map = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

//			System.out.println(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));

			if(param!=null&&param.length>0) {
				for (int i = 0; i < param.length; i++) {
					DAOUtils.dealParam(i,param[i]);
					stmt.setString(i+1, param[i]);
				}
			}

			rs = stmt.executeQuery();

			if (rs.next()) {

				map = this.rowToMap(rs);
			}

			return map;
		} catch (SQLException se) {
			//////Debug.print(se.toString(), this);
			//////Debug.print(sql, this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);

		}

	}
	
	public Map queryForMapBySql(String sql, String[] param, String jndiNames) throws DAOSystemException {

		Map map = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			conn = ConnectionContext.getContext().getConnection(jndiNames);

//			System.out.println(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));

			if(param!=null&&param.length>0) {
				for (int i = 0; i < param.length; i++) {
					DAOUtils.dealParam(i,param[i]);
					stmt.setString(i+1, param[i]);
				}
			}

			rs = stmt.executeQuery();

			if (rs.next()) {

				map = this.rowToMap(rs);
			}

			return map;
		} catch (SQLException se) {
			//////Debug.print(se.toString(), this);
			//////Debug.print(sql, this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);

		}

	}
	public Map queryMap(String sql, String[] param) throws DAOSystemException {

		Map map = new HashMap();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));

			if(param!=null&&param.length>0) {
				for (int i = 0; i < param.length; i++) {
					DAOUtils.dealParam(i,param[i]);
					stmt.setString(i+1, param[i]);
				}
			}

			rs = stmt.executeQuery();

			int k = 0;
			while (rs.next()&& k <= MAX_SIZE) {
				map.put(rs.getString(1).toLowerCase(), rs.getString(2));
				k++;
			}

			return map;
		} catch (SQLException se) {
			//////Debug.print(se.toString(), this);
			//////Debug.print(sql, this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);

		}

	}

	// 通过SQL名称 获取结果集
	/*public List queryForMapList(String sqlname, String[] sqlParams) throws DAOSystemException {

		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = Sqls.getSql(sqlname);
		if(StringUtils.isEmpty(sql))
			sql = sqlname;
		try {
			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
				////////Debug.print("sqlParams[" + i + "]=" + sqlParams[i]);
				DAOUtils.dealParam(i,sqlParams[i]);
				stmt.setString(i + 1, sqlParams[i]);
			}
			rs = stmt.executeQuery();

			list = handle(rs);
		} catch (SQLException se) {
			//////Debug.print(se.toString(), this);
			//////Debug.print(sql, this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);
			
			 * try{ ConnectionContext.getContext().closeConnection(JNDINames.CRM_DATASOURCE); }catch(Exception e){
			 *  }
			 
		}
		return list;
	}

	// 通过SQL名称 获取结果集
	*//**
	 * @param sqlname
	 * @param sqlParams
	 * @return
	 * @throws DAOSystemException
	 *//*
	public List queryForMapList(String sqlname, List sqlParams) throws DAOSystemException {

		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = Sqls.getSql(sqlname);
		if(StringUtils.isEmpty(sql))
			sql = sqlname;
		try {

			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			for (int i = 0; sqlParams != null && i < sqlParams.size(); i++) {
				////////Debug.print("sqlParams[" + i + "]=" + sqlParams.get(i));
				DAOUtils.dealParam(i,(String) sqlParams.get(i));
				stmt.setString(i + 1, (String) sqlParams.get(i));
			}
			rs = stmt.executeQuery();

			list = handle(rs);
		} catch (SQLException se) {
			//////Debug.print(se.toString(), this);
			//////Debug.print(sql, this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);
			
			 * try{ ConnectionContext.getContext().closeConnection(JNDINames.CRM_DATASOURCE); }catch(Exception e){
			 *  }
			 

		}
		return list;
	}*/

	public List queryForMapListBySql2(String sql, String[] sqlParams) throws DAOSystemException {
		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
				////////Debug.print("sqlParams[" + i + "]=" + sqlParams[i]);
				DAOUtils.dealParam(i,sqlParams[i]);
				stmt.setString(i + 1, sqlParams[i]);
			}
			rs = stmt.executeQuery();
			list = handle2(rs);
		} catch (SQLException se) {
			//////Debug.print(se.toString(), this);
			//////Debug.print(sql, this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return list;
	}

	// 直接通过SQL 获取结果集
	public List queryForMapListBySql(String sql, String[] sqlParams) throws DAOSystemException {

		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
				////////Debug.print("sqlParams[" + i + "]=" + sqlParams[i]);
				DAOUtils.dealParam(i,sqlParams[i]);
				stmt.setString(i + 1, sqlParams[i]);
			}
			rs = stmt.executeQuery();

			list = handle(rs);
		} catch (SQLException se) {
			//////Debug.print(se.toString(), this);
			//////Debug.print(sql, this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return list;
	}

	// 直接通过SQL 获取结果集
	public List queryForMapListBySql(String sql, List sqlParams) throws DAOSystemException {

		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			for (int i = 0; sqlParams != null && i < sqlParams.size(); i++) {
				////////Debug.print("sqlParams[" + i + "]=" + sqlParams.get(i));
				DAOUtils.dealParam(i,(String) sqlParams.get(i));
				stmt.setString(i + 1, (String) sqlParams.get(i));
			}
			rs = stmt.executeQuery();

			list = handle(rs);
		} catch (SQLException se) {
			//////Debug.print(se.toString(), this);
			//////Debug.print(sql, this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return list;
	}
	
	
	// 直接通过SQL 获取结果集
	public List queryForMapListBySql(String sql, List sqlParams,int maxCount) throws DAOSystemException {

		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			for (int i = 0; sqlParams != null && i < sqlParams.size(); i++) {
				////////Debug.print("sqlParams[" + i + "]=" + sqlParams.get(i));
				DAOUtils.dealParam(i,(String) sqlParams.get(i));
				stmt.setString(i + 1, (String) sqlParams.get(i));
			}
			rs = stmt.executeQuery();

			list = handle(rs,maxCount);
		} catch (SQLException se) {
			//////Debug.print(se.toString(), this);
			//////Debug.print(sql, this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return list;
	}

	/**
	 * 直接通过SQL 获取结果集
	 * 指定数据源
	 * @param sql
	 * @param sqlParams
	 * @param dataSource
	 * @return
	 * @throws DAOSystemException
	 */
	public List queryForMapListBySql(String sql, List sqlParams,String dataSource) throws DAOSystemException {

		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			if (dataSource == null || "".equals(dataSource))
				dataSource = JNDINames.CRM_DATASOURCE;

			conn = ConnectionContext.getContext().getConnection(dataSource);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			for (int i = 0; sqlParams != null && i < sqlParams.size(); i++) {
				////////Debug.print("sqlParams[" + i + "]=" + sqlParams.get(i));
				DAOUtils.dealParam(i,(String) sqlParams.get(i));
				stmt.setString(i + 1, (String) sqlParams.get(i));
			}
			rs = stmt.executeQuery();

			list = handle(rs);
		} catch (SQLException se) {
			//////Debug.print(se.toString(), this);
			//////Debug.print(sql, this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return list;
	}

	public List queryForStringList(String sql, String[] sqlParams) {
		List list = new ArrayList();
		Connection dbConnection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			dbConnection = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);
			String sqlStr = DAOSQLUtils.getFilterSQL(sql);
			stmt = dbConnection.prepareStatement(sqlStr);
			for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
				////////Debug.print("sqlParams[" + i + "]=" + sqlParams[i]);
				DAOUtils.dealParam(i,sqlParams[i]);
				stmt.setString(i + 1, sqlParams[i]);
			}
			result = stmt.executeQuery();
			int k=0;
			while (result.next()&& k <= MAX_SIZE) {
				list.add(DAOUtils.trimStr(result.getString(1)));
				k++;
			}
		} catch (Exception se) {
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(result, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return list;
	}

	// 以返回结果集的第一个字段作为HashMap KEY
	/*public Map queryKeyValueMap(String sqlname, String[] sqlParams) throws DAOSystemException {

		Map map = new HashMap();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = Sqls.getSql(sqlname);
		try {
			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));

			for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
				DAOUtils.dealParam(i,sqlParams[i]);
				stmt.setString(i + 1, sqlParams[i]);
			}
			rs = stmt.executeQuery();

			int k=0;
			while (rs.next()&& k <= MAX_SIZE) {
				map.put(DAOUtils.trimStr(rs.getString(1)), DAOUtils.trimStr(rs.getString(2)));
				k++;
			}

			return map;
		} catch (SQLException se) {
			//////Debug.print(se.toString(), this);
			//////Debug.print(sql, this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);

		}

	}*/

	@Deprecated
	// 以返回结果集的第一个字段作为HashMap KEY
	public Map queryKeyValueMap(String sql) throws DAOSystemException {

		Map map = new HashMap();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);
			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			rs = stmt.executeQuery();

			while (rs.next()) {

				map.put(DAOUtils.trimStr(rs.getString(1)), DAOUtils.trimStr(rs.getString(2)));
			}

			return map;
		} catch (SQLException se) {
			//////Debug.print(se.toString(), this);
			//////Debug.print(sql, this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);

		}

	}

	public Map queryRowsForMap(String sql, String[] sqlParams) throws DAOSystemException {

		Map map = new HashMap();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));

			for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
				DAOUtils.dealParam(i,sqlParams[i]);
				stmt.setString(i + 1, sqlParams[i]);
			}
			rs = stmt.executeQuery();

			int k = 0;
			while (rs.next() && k <= MAX_SIZE) {

				map.put(DAOUtils.trimStr(rs.getString(1)), DAOUtils.trimStr(rs.getString(2)));
				k++;
			}

			return map;
		} catch (SQLException se) {
			//////Debug.print(se.toString(), this);
			//////Debug.print(sql, this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);

		}

	}

	@Deprecated
	public int executeUpdate(String sql) throws DAOSystemException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {

			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql.toString()));

			return stmt.executeUpdate();

		} catch (SQLException se) {
			//////Debug.print(sql.toString(), this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeStatement(stmt, this);

		}
	}

	/**
	 * 根据条件更新数据。
	 *
	 * @param sql
	 * @param sqlParams
	 * @return
	 * @throws DAOSystemException
	 */
	public int excuteUpdate(String sql, List sqlParams) throws DAOSystemException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {

			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql.toString()));
			for (int i = 0; i < sqlParams.size(); i++) {
				//////Debug.print("sqlParams[" + i + "]=" + (String)sqlParams.get(i));
				//DAOUtils.dealParam(i,(String)sqlParams.get(i));
				stmt.setString(i + 1, (String) sqlParams.get(i));
			}
			return stmt.executeUpdate();
		} catch (SQLException se) {
			//////Debug.print(sql.toString(), this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeStatement(stmt, this);
		}
	}

	/**
	 * 根据条件更新数据。
	 * @author AyaKoizumi
	 * @date 101217
	 * @param sql
	 * @param sqlParams
	 * @return
	 * @throws DAOSystemException
	 */
	public int excuteUpdate(String sql, String[] sqlParams,String jndiName) throws DAOSystemException {
		List params=new ArrayList();
		for (int i=0;i<sqlParams.length;i++){
			params.add(sqlParams[i]);
		}
		return excuteUpdate(sql,params,jndiName);
	}
	/**
	 * 根据条件更新数据。
	 * @author AyaKoizumi
	 * @date 101217
	 * @param sql
	 * @param sqlParams
	 * @return
	 * @throws DAOSystemException
	 */
	public int excuteUpdate(String sql, List sqlParams,String jndiName) throws DAOSystemException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {

			conn = ConnectionContext.getContext().getConnection(jndiName);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql.toString()));
			for (int i = 0; i < sqlParams.size(); i++) {
				//////Debug.print("sqlParams[" + i + "]=" + (String)sqlParams.get(i));
				//DAOUtils.dealParam(i,(String)sqlParams.get(i));
				stmt.setString(i + 1, (String) sqlParams.get(i));
			}
			return stmt.executeUpdate();
		} catch (SQLException se) {
			//////Debug.print(sql.toString(), this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeStatement(stmt, this);
		}
	}
	public void excuteBatchUpdate(String sql, List sqlParams) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {

			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql.toString()));
			for (int i = 0; i < sqlParams.size(); i++) {
				List params = (List)sqlParams.get(i);
				for (int j = 0; j < params.size(); j++) {
					//////Debug.print("sqlParams[" + i +"--" + j + "]=" + (String)params.get(j));
					//DAOUtils.dealParam(i +"--" + j ,(String)sqlParams.get(i));
					stmt.setString(j + 1, (String) params.get(j));
				}
				stmt.addBatch();
			}
			stmt.executeBatch();
		} catch (SQLException se) {
			//////Debug.print(sql.toString(), this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeStatement(stmt, this);
		}
	}

	/**
	 * 支持单个参数值的批量更新
	 * @param sql
	 * @param singleParaLst<String>
	 * @param commParam  公共
	 */
	public void excuteBatchUpdateSinglePara(String sql, List singleParaLst) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);
			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql.toString()));

			for (Object obj : singleParaLst) {
				//DAOUtils.dealParam(0 ,(String)(String)obj);
				stmt.setString(1, (String)(String)obj);
				stmt.addBatch();
			}

			stmt.executeBatch();
		} catch (SQLException se) {
			//////Debug.print(sql.toString(), this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeStatement(stmt, this);
		}
	}

	/***
	 * 批量更新，支持日期类型
	 * @param sql
	 * @param sqlParams
	 */
	public void excuteBatchUpdateSupportDate(String sql, List sqlParams) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql.toString()));
			for (int i = 0; i < sqlParams.size(); i++) {
				List params = (List)sqlParams.get(i);
				for (int j = 0; j < params.size(); j++) {
					//////Debug.print("sqlParams[" + i +"--" + j + "]=" + params.get(j));

					if (isDateType(params.get(j))) {
						stmt.setDate(j+1, (java.sql.Date) params.get(j));
					} else if (isTimestampType(params.get(j))) {
						stmt.setTimestamp(j+1, (java.sql.Timestamp) params.get(j));
					} else {
						if (params.get(j) == null)
							stmt.setString(j+1, "");

						else
							stmt.setObject(j+1, params.get(j));
					}
				}
				stmt.addBatch();
			}
			stmt.executeBatch();
		} catch (SQLException se) {
			////Debug.print(sql.toString(), this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeStatement(stmt, this);
		}
	}


	public static boolean isDateType(Object o) {
		return o != null
				&& ("java.util.Date".equals(o.getClass().getName()) || "java.sql.Date"
						.equals(o.getClass().getName()));
	}

	public static boolean isTimestampType(Object o) {
		return o != null && "java.sql.Timestamp".equals(o.getClass().getName());
	}

	/**
	 * 根据条件更新数据。
	 *
	 * @param sql
	 * @param sqlParams
	 * @return
	 * @throws DAOSystemException
	 */
	public int excuteUpdate(String sql, String[] sqlParams) throws DAOSystemException {
		List params=new ArrayList();
		for (int i=0;i<sqlParams.length;i++){
			params.add(sqlParams[i]);
		}
		return excuteUpdate(sql,params);
	}
	
	@Deprecated
	public String querySingleValue(String sql) {
		Connection dbConnection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String returnValue = "";
		try {
			dbConnection = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);
			String sqlStr = "";
			if (sql.indexOf("dual") > -1 || sql.indexOf("DUAL") > -1) {
				sqlStr = sql;
			} else {
				sqlStr = DAOSQLUtils.getFilterSQL(sql);
			}
			stmt = dbConnection.prepareStatement(sqlStr);
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = DAOUtils.trimStr(result.getString(1));
			}
		} catch (Exception se) {
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(result, this);
			DAOUtils.closeStatement(stmt, this);

			// DAOUtils.closeConnection(dbConnection, this);
		}
		return returnValue;
	}

	@Deprecated
	public List queryForStringList(String sql) {
		List list = new ArrayList();
		Connection dbConnection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			dbConnection = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);
			String sqlStr = DAOSQLUtils.getFilterSQL(sql);
			stmt = dbConnection.prepareStatement(sqlStr);

			result = stmt.executeQuery();
			int k = 0;
			while (result.next() && k<= MAX_SIZE) {
				list.add(result.getString(1));
				k++;
			}
		} catch (Exception se) {
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(result, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return list;
	}
	
	/*public List queryForStringListByName(String sqlName,String[] sqlParams) {
		List list = new ArrayList();
		Connection dbConnection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String sql = "";
		try {
			dbConnection = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);
		    sql = Sqls.getSql(sqlName);
			String sqlStr = DAOSQLUtils.getFilterSQL(sql);
			stmt = dbConnection.prepareStatement(sqlStr);
			for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
				//////Debug.print("sqlParams[" + i + "]=" + sqlParams[i]);
				DAOUtils.dealParam(i ,sqlParams[i]);
				stmt.setString(i + 1, sqlParams[i]);
			}
			result = stmt.executeQuery();
			int k=0;
			while (result.next()&& k <= MAX_SIZE) {
				list.add(result.getString(1));
				k++;
			}
		} catch (Exception se) {
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(result, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return list;
	}*/

	/*public List queryForStringListByName(String sqlName) {
		List list = new ArrayList();
		Connection dbConnection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String sql = "";
		try {
			dbConnection = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);
		    sql = Sqls.getSql(sqlName);
			String sqlStr = DAOSQLUtils.getFilterSQL(sql);
			stmt = dbConnection.prepareStatement(sqlStr);

			result = stmt.executeQuery();
			int k=0;
			while (result.next()&& k <= MAX_SIZE) {
				list.add(result.getString(1));
				k++;
			}
		} catch (Exception se) {
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(result, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return list;
	}*/


	@Deprecated
	public List queryForMapList(String sql) throws DAOSystemException {

		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);
			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			rs = stmt.executeQuery();

			list = handle(rs);
		} catch (SQLException se) {
			////Debug.print(se.toString(), this);
			////Debug.print(sql, this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return list;
	}

	/*@Deprecated  
	public List queryForMapListByName(String sqlName) throws DAOSystemException {

		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = Sqls.getSql(sqlName);
		try {

			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);
			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			rs = stmt.executeQuery();

			list = handle(rs);
		} catch (SQLException se) {
			////Debug.print(se.toString(), this);
			////Debug.print(sql, this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return list;
	}
*/
	//连接地址库临时测试

	/**
	 * @param sqlname
	 * @param sqlParams
	 * @return
	 * @throws DAOSystemException
	 */
	public List queryForMapList(String sqlname, List sqlParams,String JNDINames) throws DAOSystemException {

		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = sqlname;

		try {

			conn = ConnectionContext.getContext().getConnection(JNDINames);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			for (int i = 0; sqlParams != null && i < sqlParams.size(); i++) {
				//////Debug.print("sqlParams[" + i + "]=" + sqlParams.get(i));
				DAOUtils.dealParam(i ,(String) sqlParams.get(i));
				stmt.setString(i + 1, (String) sqlParams.get(i));
			}
			rs = stmt.executeQuery();

			list = handle(rs);
		} catch (SQLException se) {
			////Debug.print(se.toString(), this);
			////Debug.print(sql, this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);
			/*
			 * try{ ConnectionContext.getContext().closeConnection(JNDINames.CRM_DATASOURCE); }catch(Exception e){
			 *  }
			 */

		}
		return list;
	}


	//add by AyaKoizumi 110304
	public List execForMapList(String sql, String[] sqlParams,String dataSource) throws DAOSystemException {

		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			conn = ConnectionContext.getContext().getConnection(dataSource);

			LOG.debug(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
				//////Debug.print("sqlParams[" + i + "]=" + sqlParams[i]);
				DAOUtils.dealParam(i ,sqlParams[i]);
				stmt.setString(i + 1, sqlParams[i]);
			}
			rs = stmt.executeQuery();

			list = handle(rs);
		} catch (Exception se) {
			////Debug.print(se.toString(), this);
			////Debug.print(sql, this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return list;
	}

	public List execForMapList(String sql, String[] sqlParams) throws DAOSystemException {
		return this.execForMapList(sql, sqlParams,JNDINames.CRM_DATASOURCE);
	}

	/**
	* 根据SQL,参数，分页查询结果
	* @param String  sql，List params
	* @return PageModel
	*@throws Exception
	*/
	/*
	 * public PageModel queryPageModelResult(String sql,ArrayList params,int pi,int
	 * ps) throws Exception { PageModel pageModel = new PageModel();
	 * 
	 * String countSql = " select count(*) count from ( " + sql + " ) ";//计算总记录数
	 * 
	 * int totalCount = new Long(this.querySingleValue(countSql,
	 * params)).intValue();
	 * 
	 * pageModel.setTotal( totalCount);
	 * 
	 * if (totalCount == 0) return new PageModel();
	 * 
	 * if (totalCount % ps > 0) { pageModel.setPageCount(totalCount / ps + 1); }
	 * else { pageModel.setPageCount(totalCount / ps); }
	 * 
	 * // 边界的处理 if (pi < 0) { pageModel.setPageNumber( 1); } else
	 * if(pi>pageModel.getPageCount()){
	 * pageModel.setPageNumber(pageModel.getPageCount()); }else {
	 * pageModel.setPageNumber(pi); }
	 * 
	 * if (ps < 0) { pageModel.setPageSize(totalCount); } else {
	 * pageModel.setPageSize(ps); }
	 * 
	 * String queryResultSql = null;
	 * 
	 * if (CrmConstants.DB_TYPE_INFORMIX.equals(databaseType)) { queryResultSql =
	 * " select skip " + (pi - 1) + " first " + ps +
	 * "  *  from  ( select my_table.*  from (   " + sql + " )  my_table  ) "; List
	 * resultList = this.queryForMapListBySql(queryResultSql, params);
	 * pageModel.setRows( resultList);
	 * 
	 * } else { queryResultSql =
	 * " select * from ( select mytable.*, rownum num from ( "; queryResultSql +=
	 * sql; queryResultSql += " ) mytable )where num > ? and num <= ?";
	 * 
	 * ArrayList new_params = new ArrayList(); new_params.addAll(params);
	 * new_params.add(String.valueOf(ps * (pi - 1)));
	 * new_params.add(String.valueOf(ps * pi));
	 * 
	 * List resultList = this.queryForMapListBySql(queryResultSql, new_params,ps);
	 * pageModel.setRows( resultList); }
	 * 
	 * return pageModel;
	 * 
	 * }
	 */
	public List getUpcaseKeyMapList(String sql, String[] sqlParams) throws DAOSystemException {
		return getMapList(sql, sqlParams, "T");
	}

	public List getLowercaseKeyMapList(String sql, String[] sqlParams) throws DAOSystemException {
		return getMapList(sql, sqlParams, "F");
	}

	// 现在系统都是默认小写key，增加一个方法，用来选定MAP中是大写还是小写
	// T 大写 否则小写
	public List getMapList(String sql, String[] sqlParams, String Upcaseflag) throws DAOSystemException {

		List list = new ArrayList();
		List retList = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

			LOG.debug(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
				//////Debug.print("sqlParams[" + i + "]=" + sqlParams[i]);
				DAOUtils.dealParam(i ,sqlParams[i]);
				stmt.setString(i + 1, sqlParams[i]);
			}
			rs = stmt.executeQuery();

			list = handle(rs);
			for (int i = 0; i < list.size(); i++) {
				HashMap map = (HashMap) list.get(i);
				HashMap cloneMap = new HashMap();
				Iterator ite = map.keySet().iterator();
				while (ite.hasNext()) {
					String key = (String) ite.next();

					if ("T".equals(Upcaseflag)) {
						key.toUpperCase();
					} else {
						key.toLowerCase();
					}

					cloneMap.put(key, (String) map.get(key));
				}
				retList.add(cloneMap);
			}
		} catch (SQLException se) {
			////Debug.print(se.toString(), this);
			////Debug.print(sql, this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return retList;
	}

	private List handle(ResultSet rs) throws SQLException {

		List results = new ArrayList();
		int i=0;
		while (rs.next()&&i<=MAX_SIZE) {
			//delete by AyaKoizumi 100924
//			results.add(this.rowToMap(rs));
			//add by AyaKoizumi 100924

			results.add(this.populateDto(rs));
			i++;
		}

		return results;
	}

	private List handle2(ResultSet rs) throws SQLException {
		List results = new ArrayList();
		int i = 0;
		while (rs.next()&& i <= MAX_SIZE) {
			results.add(this.populateDto2(rs));
			i++;
		}
		return results;
	}
	
	private List handle(ResultSet rs,int maxCount) throws SQLException {

		List results = new ArrayList();
		int i=0;
		while (rs.next()&&i<=maxCount) {

			results.add(this.populateDto(rs));
			i++;
		}

		return results;
	}
	
	private List handle2(ResultSet rs, int maxReturn) throws SQLException {
		List results = new ArrayList();
		int i = 0;
		if(maxReturn < 0){
			while (rs.next()) {
				results.add(this.populateDto2(rs));
				i++;
			}
		}else if(maxReturn==0){
			while (rs.next()&& i <= MAX_SIZE) {
				results.add(this.populateDto2(rs));
				i++;
			}
		}else{
			while (rs.next()&& i <= maxReturn) {
				results.add(this.populateDto2(rs));
				i++;
			}
		}
		return results;
	}

	//add by AyaKoizumi 100924
	private HashMap populateDto(ResultSet rs) throws SQLException
	{

		HashMap vo=new HashMap();
		int fieldcount = rs.getMetaData().getColumnCount();
		String ColumnName = "";
		String ColumnTypeName = "";

		for (int i = 0; i < fieldcount; i++)
		{
			ColumnTypeName = rs.getMetaData().getColumnTypeName(i + 1);
			ColumnName = rs.getMetaData().getColumnName(i + 1).toLowerCase();
			if (ColumnTypeName.toLowerCase().equals("date") || ColumnTypeName.toLowerCase().equals("datetime year to second")){
				String _dateVal=DAOUtils.getFormatedDateTime( rs.getTimestamp(ColumnName));
				if(_dateVal!=null && !_dateVal.equals("")){
					if(_dateVal.indexOf(".")>=0){
						String[]_lstDateVal=_dateVal.split(".");//如果是2009-12-11 00:00:00.0改成2009-12-11 00:00:00
						vo.put(ColumnName, _lstDateVal[0]);
					}else{
						vo.put(ColumnName, _dateVal);
					}
				}
				else{
					vo.put(ColumnName, "");
				}
			}else if(ColumnTypeName.toLowerCase().equals("number")){
				Object colVal=rs.getObject(ColumnName);
				if(colVal==null)colVal="";
				vo.put(ColumnName, String.valueOf(colVal));
			}else{
				Object colVal=rs.getObject(ColumnName);
				if(colVal==null)colVal="";
				vo.put(ColumnName, colVal);
			}
		}
		return vo;
	}


	//add by AyaKoizumi 100924
	private HashMap populateDto2(ResultSet rs) throws SQLException
	{
		HashMap vo=new HashMap();
		int fieldcount = rs.getMetaData().getColumnCount();
		String ColumnName = "";
		String ColumnTypeName = "";
		for (int i = 0; i < fieldcount; i++)
		{
			ColumnTypeName = rs.getMetaData().getColumnTypeName(i + 1);
			ColumnName = rs.getMetaData().getColumnName(i + 1).toLowerCase();
			if (ColumnTypeName.toLowerCase().equals("date") || ColumnTypeName.toLowerCase().equals("datetime year to second")){
				String _dateVal=DAOUtils.getFormatedDateTime( rs.getTimestamp(ColumnName));
				if(_dateVal!=null && !_dateVal.equals("")){
					if(_dateVal.indexOf(".")>=0){
						String[]_lstDateVal=_dateVal.split(".");//如果是2009-12-11 00:00:00.0改成2009-12-11 00:00:00
						vo.put(ColumnName, _lstDateVal[0]);
					}else{
						vo.put(ColumnName, _dateVal);
					}
				}
				else{
					vo.put(ColumnName, "");
				}
			}
			else
			{
				vo.put(ColumnName, rs.getString(ColumnName)==null?"":rs.getString(ColumnName).trim());
			}
		}
		return vo;
	}
	private Map rowToMap(ResultSet rs) throws SQLException {
		Map result = new HashMap();
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		for (int i = 1; i <= cols; i++) {
			result.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(i));
		}
		return result;
	}

	public Map queryMapBySql(String sql, String[] sqlParams) throws DAOSystemException {

		Map map = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);

//			System.out.println(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));

			for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
				//////Debug.print("sqlParams[" + i + "]=" + sqlParams[i]);
				DAOUtils.dealParam(i ,sqlParams[i]);
				stmt.setString(i + 1, sqlParams[i]);
			}
			rs = stmt.executeQuery();
			if (rs.next()) {
				map = this.rowToMap(rs);
			}

			return map;
		} catch (SQLException se) {
			////Debug.print(se.toString(), this);
			////Debug.print(sql, this);
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);

		}
	}
	
	public long countBySql(String sql,List sqlParams) throws DAOSystemException { 
		Connection conn = null;
		long lCount = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionContext.getContext().getConnection(
					JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql));
			for (int i = 0; sqlParams != null && i < sqlParams.size(); i++) {
				//////Debug.print("sqlParams[" + i + "]=" + sqlParams.get(i));
				DAOUtils.dealParam(i ,(String) sqlParams.get(i));
				stmt.setString(i + 1, (String) sqlParams.get(i));
			}
			rs = stmt.executeQuery();

			int k = 0;
			while (rs.next()&& k <= MAX_SIZE) {
				lCount = rs.getLong(1);
				k++;
			}
		} catch (SQLException se) {
			se.printStackTrace();
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return lCount;
	}
	
	@Deprecated
	public long countBySql(String sql) throws DAOSystemException {
		Connection conn = null;
		long lCount = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionContext.getContext().getConnection(
					JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql));
			rs = stmt.executeQuery();

			int k = 0;
			while (rs.next()&& k <= MAX_SIZE) {
				lCount = rs.getLong(1);
				k++;
			}
		} catch (SQLException se) {
			se.printStackTrace();
			throw new DAOSystemException(DAOUtils.formatSqlErr(sql), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);

		}
		return lCount;
	}  
	public String queryBlobToString(String sqlString,String[] sqlParams) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			 conn = ConnectionContext.getContext().getConnection(
					JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(sqlString);
			for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
				DAOUtils.dealParam(i, sqlParams[i]);
				stmt.setString(i + 1, sqlParams[i]);
			}
			rs = stmt.executeQuery();
			if (rs.next()) {
				java.sql.Blob blob = rs.getBlob(1);
				if (blob != null) {
					byte[] bdata = blob.getBytes(1, (int) blob.length());
					String queryContent = new String(bdata);
					return queryContent;
				}
			}

			return "";
		} catch (Exception se) {
			////Debug.print("select  query_content_blob exception!!!"
				//	+ se.getMessage(), this);
			throw new DAOSystemException("SQLException while getting "
					+ "query_content_blob:\n" + se.getMessage(), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);
			DAOUtils.closeConnection(conn, this);
		}
	}
	public String queryBlobToString(String sqlString) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			 conn = ConnectionContext.getContext().getConnection(
					JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(sqlString);
			rs = stmt.executeQuery();
			if (rs.next()) {
				java.sql.Blob blob = rs.getBlob(1);
				if (blob != null) {
					byte[] bdata = blob.getBytes(1, (int) blob.length());
					String queryContent = new String(bdata);
					return queryContent;
				}
			}

			return "";
		} catch (Exception se) {
			////Debug.print("select  query_content_blob exception!!!"
					//+ se.getMessage(), this);
			throw new DAOSystemException("SQLException while getting "
					+ "query_content_blob:\n" + se.getMessage(), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);
			DAOUtils.closeConnection(conn, this);
		}
	}
	public String queryImgBlobToString(String sqlString,String[] sqlParams) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			 conn = ConnectionContext.getContext().getConnection(
					JNDINames.CRM_DATASOURCE);

			stmt = conn.prepareStatement(sqlString);
			for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
				DAOUtils.dealParam(i, sqlParams[i]);
				stmt.setString(i + 1, sqlParams[i]);
			}
			rs = stmt.executeQuery();
			if (rs.next()) {
				java.sql.Blob blob = rs.getBlob(1);
				if (blob != null) {
					byte[] bdata = new byte[(int) blob.length()];
					InputStream inStream = blob.getBinaryStream();
					inStream.read(bdata);
                    inStream.close();
                    Base64Encoder base64Encoder =  new Base64Encoder();
					return base64Encoder.encode(bdata);
				}
			}

			return "";
		} catch (Exception se) {
			////Debug.print("select  query_content_blob exception!!!"
				//	+ se.getMessage(), this);
			throw new DAOSystemException("SQLException while getting "
					+ "query_content_blob:\n" + se.getMessage(), se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);
			DAOUtils.closeConnection(conn, this);
		}
	}
	
	/**
	 * [715786]SELF-取消订单时记录代码轨迹
	 * @param sql ： 示例：select print_content from CUSTOMER_ORDER_PRINT where cust_order_id = ?
	 * @param printContent
	 * @return
	 */
	public boolean excuteUpdateByBlog(String sql,String blogContent) {
		return excuteUpdateByBlog(sql, blogContent.getBytes());
	}
	
	/**
	 * [715786]SELF-取消订单时记录代码轨迹
	 * @param sql ： 示例：select print_content from CUSTOMER_ORDER_PRINT where cust_order_id = ?
	 * @param printContent
	 * @return
	 */	
	public boolean excuteUpdateByBlog(String sql,byte[] blogContent) {
		Statement _stmt = null;
		Connection _conn = null;
		ResultSet _rs = null;
		try {
			_conn = ConnectionContext.getContext().getConnection(
					JNDINames.CRM_DATASOURCE);
			_conn.setAutoCommit(false);
			_stmt = _conn.createStatement();
			_rs = _stmt.executeQuery(sql) ;
			if (_rs.next()) {
				OutputStream _outStream = null;
				Blob _blob = _rs.getBlob(1);
				/*if (_blob instanceof weblogic.jdbc.vendor.oracle.OracleThinBlob) {
					weblogic.jdbc.vendor.oracle.OracleThinBlob _thinblob = (weblogic.jdbc.vendor.oracle.OracleThinBlob) _blob;
					_outStream = _thinblob.getBinaryOutputStream();
				} else {*/
					oracle.sql.BLOB _thinblob = (oracle.sql.BLOB) _blob;
					_outStream = _thinblob.getBinaryOutputStream();
//				}

				//_inStream.read(_byte);
				_outStream.write(blogContent);
				_outStream.flush();
				_stmt.execute("commit");
				_outStream.close();
			}
			_conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(_stmt!=null)
				_stmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return true;
	}
	
	/**
	 * @param sql SQL
	 * @param maxReturn maxReturn when less then 0,the count of return result is unlimited;
	 * 	  	  when equals to 0,it decided by configuration file;
	 * 		  when larger then 0,limited by the parameter.
	 * @return
	 * @throws DAOSystemException
	 */
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> queryForMapListWithLimit(String sql, int maxReturn) throws DAOSystemException {
		
		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			
			conn = ConnectionContext.getContext().getConnection(JNDINames.CRM_DATASOURCE);
			stmt = conn.prepareStatement(DAOSQLUtils.getFilterSQL(sql, DAOSQLUtils.CRM_DB));
			rs = stmt.executeQuery();
			
			list = handle2(rs, maxReturn);
		} catch (SQLException se) {
			////Debug.print(se.toString(), this);
			////Debug.print(sql, this);
			throw new DAOSystemException("", se);
		} finally {
			DAOUtils.closeResultSet(rs, this);
			DAOUtils.closeStatement(stmt, this);
			
		}
		return list;
	}
	
	
	/*public List queryForMapListByPage(String sqlName, ArrayList params, int pi, int ps) throws Exception{
		String sql = Sqls.getSql(sqlName);
		if(StringUtils.isEmpty(sql)){
			sql = sqlName;
		}
		String queryResultSql = null;
		if (CrmConstants.DB_TYPE_INFORMIX.equals(databaseType)) {
			queryResultSql = " select skip " + (pi - 1) + " first " + ps
					+ "  *  from  ( select my_table.*  from (   " + sql
					+ " )  my_table  ) ";
			List resultList = this.queryForMapListBySql(queryResultSql, params);
			return resultList;
		} else {
			queryResultSql = 
					"select temp2.* " +
					"  from (" + 
					"		select rownum temp_rownum, temp1.* from (" + sql + ") temp1 where rownum <= ? " + 
					"		) temp2 " +
					" where temp2.temp_rownum > ? ";
			ArrayList new_params = new ArrayList();
			new_params.addAll(params);
			new_params.add(String.valueOf(ps * pi));
			new_params.add(String.valueOf(ps * (pi - 1)));

			List resultList = this.queryForMapListBySql(queryResultSql, new_params);
			return resultList;
		}
	}*/
}
