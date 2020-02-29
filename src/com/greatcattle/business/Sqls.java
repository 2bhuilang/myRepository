/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.greatcattle.business;

/**
 *
 * @author Administrator
 */
public interface Sqls {
    
    public static final String loginPwdCheck="select 1 from user where user_code = ? and pass_word=?";
    
    public static final String loginUserNameCheck = "select 1 from user where user_code=?";
    
    public static final String queryTruckInfoSql="select * from truck a where 1=1 ";
    
    public static final String queryTableDef= "select COLUMN_COMMENT,COLUMN_NAME from information_schema.`COLUMNS` where table_name = ?";
    
    public static final String addTruckSql = "INSERT INTO truck (\n" +
        "	truck_no,\n" +
        "	weight,\n" +
        "	COMMENTs,\n" +
        "	card_no,\n" +
        "	create_date,\n" +
        "	create_staff\n" +
        ")values(?,?,?,?,NOW(),?)";
    
}
