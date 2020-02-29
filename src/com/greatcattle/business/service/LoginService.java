/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.greatcattle.business.service;

import com.greatcattle.business.bo.LoginBO;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginBO loginBo;
    public String loginIn(Map loginParam){
       return loginBo.loginIn(loginParam);
    }
    
}
