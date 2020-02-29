/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.greatcattle.framework;

import java.util.Map;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Administrator
 */
public class PageReqInfo {
    
    private JTable jTable = null;
    
    private JTextField jTextField = null;
    
    private Map reqParam = null;

    public Map getReqParam() {
        return reqParam;
    }

    public void setReqParam(Map reqParam) {
        this.reqParam = reqParam;
    }

    public JTable getjTable() {
        return jTable;
    }

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }

    public JTextField getjTextFiled() {
        return jTextField;
    }

    public void setjTextFiled(JTextField jTextFiled) {
        this.jTextField = jTextFiled;
    }
    
}
