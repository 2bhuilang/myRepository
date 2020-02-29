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
public enum TableConst {
    
    TRUCK("TRUCK");
    private String tableName;
    private TableConst(String tableName){
        this.tableName=tableName;
    }
}
