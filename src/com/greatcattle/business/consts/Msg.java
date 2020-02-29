/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.greatcattle.business.consts;

/**
 *
 * @author Administrator
 */
public enum Msg {
    
    Succ("0","成功！"),
    UserName_isNull("00001","用户名不能为空！"),
    PassWord_isNull("00002","密码不能为空！"),
    PassWord_error("00003","密码错误！"),
    Msg00002("00004","     时间："),
    UserName_noExists("00005","用户名不存在！"),
    TruckNo_isNull("00006","车牌号不能为空！");
    private String code = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    private String message = null;

    private Msg(String code,String message){
        this.code=code;
        this.message = message;
    }

  
    
    public static String getMsg(String code){
        for( Msg cs : Msg.values()){
            if(code.equals(cs.getCode())){
                return cs.getMessage();
            }
        }
        return null;
    }
    
    
    
    
}
