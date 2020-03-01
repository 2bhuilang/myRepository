package com.greatcattle.controllers.utils;

import com.greatcattle.business.Sqls;
import com.greatcattle.dataservice.common.SqlMapExe;
import com.greatcattle.dataservice.common.dto.PageModel;
import com.greatcattle.utils.Const;
import com.greatcattle.utils.ListUtil;
import com.greatcattle.utils.MapUtil;
import com.greatcattle.utils.StrTools;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javax.swing.JTable;

public class UiUtil {

    public static Node loadView(String viewPath) {

        Parent root = null;
        try {
            root = FXMLLoader.load(UiUtil.class.getClassLoader().getResource(viewPath));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// getClass().getClassLoader().getResource() // getClass().getResource()�������ڲ��ڸ�Ŀ¼��

        return root;
    }

    /**
     * 主窗口默认大小
     */
    public static java.awt.Dimension getDefaultPanelSize() {
        return new java.awt.Dimension(1100, 600);
    }
    
    /**
     * 获得弹出窗口大小
     * 一列输入框
     */
    public static java.awt.Dimension getDialogSize(){
        return new java.awt.Dimension(500,400);
    }
    
    /**带标题的边框的字体大小*/
    public static java.awt.Font getBorderFont(){
        return new java.awt.Font("宋体", 0, 14);
    }
    
    /**标签字体*/
    public static java.awt.Font getLabelFont(){
        return new java.awt.Font("宋体", 0, 14);
    }
    
    /**按钮字体*/
    public static java.awt.Font getBtnFont(){
        return new java.awt.Font("宋体", 0, 14);
    }
    
    /**输入框字体*/
     public static java.awt.Font getTextFiledFont(){
        return new java.awt.Font("宋体", 0, 14);  
    }
     
     /***错误信息字体*/
    public static java.awt.Font getErrorMsgFont(){
        return new java.awt.Font("宋体", 0, 10);
    }
    

    public static int getDefaultPageSize() {
        return 10;
    }
    
    public static String showPageInfo(PageModel pm,String currIndex){
        int total = pm.getTotal();//数据总量	
		int row = pm.getPageSize();//一页显示条数
		int totalPages;//总页数
	totalPages = total / row;
	if (total % row != 0){
		totalPages ++;
	}
	return currIndex+"/"+totalPages;
    }

    public static void transPageModelToTableModel(JTable jTable, PageModel pm) {
        List tableDefList = SqlMapExe.getInstance().queryForMapListBySql(Sqls.queryTableDef, new String[]{jTable.getName().toUpperCase()});
        if (ListUtil.isEmpty(tableDefList)) {
            return;
        }
        if (pm == null || ListUtil.isEmpty(pm.getRows())) {
            return;
        }
        List dataList = pm.getRows();
        Map firstRow = (Map) dataList.get(0);
        String columnNames[] = new String[firstRow.keySet().size()];
        int i = 0;
        for (Object key : firstRow.keySet()) {
            for(int j=0;j<tableDefList.size();j++){
                Map tableDefMap = (Map)tableDefList.get(j);
                if(StrTools.isEqual(Const.getStrValue(tableDefMap, "column_name"), (String)key)){
                    columnNames[i] = Const.getStrValue(tableDefMap, "column_comment");
                    break;
                }
            }
            i++;
        }
        Object[][] value = new Object[dataList.size()][columnNames.length];
        for (int j = 0; j < dataList.size(); j++) {
            Map element = (Map) dataList.get(j);
            int k = 0;
            for (Object key : element.keySet()) {
                value[j][k] =  element.get((String) key);
                k++;
            }
        }
        jTable.setModel(new javax.swing.table.DefaultTableModel(
                value,
                columnNames
        ));
    }
    
    /**获取登陆系统ID*/
    public static String getLoginUserId(){
         return "2";
    }

}
