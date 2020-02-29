/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.greatcattle.business.bo;

import com.greatcattle.business.Sqls;
import com.greatcattle.business.consts.Msg;
import com.greatcattle.business.util.Md5Util;
import com.greatcattle.dataservice.common.SqlMapExe;
import com.greatcattle.utils.Const;
import com.greatcattle.utils.StrTools;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class LoginBO {
    private SqlMapExe sqlMapExe = SqlMapExe.getInstance();
    public String loginIn(Map loginParam){
        String userCode = Const.getStrValue(loginParam, "user_code");
        String passWord = Const.getStrValue(loginParam, "pass_word");
        if(StrTools.isEmpty(userCode)){
              return Msg.UserName_isNull.getCode();
        }
        if(StrTools.isEmpty(passWord)){
            return Msg.PassWord_isNull.getCode();
        }
        
        String flag = sqlMapExe.querySingleValue(Sqls.loginUserNameCheck,new String[]{userCode});
        if(StrTools.isEmpty(flag)){
            return Msg.UserName_noExists.getCode();
        }
        String pwdMd5 = Md5Util.MD5(passWord);
        flag = SqlMapExe.getInstance().querySingleValue(Sqls.loginPwdCheck,new String[]{userCode,pwdMd5});
        if(StrTools.isEmpty(flag)){
            return Msg.PassWord_error.getCode();
        }else{
            return Msg.Succ.getCode();
        }
     
      
    }
    
}
