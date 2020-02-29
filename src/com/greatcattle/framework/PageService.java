/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.greatcattle.framework;

import com.greatcattle.controllers.utils.UiUtil;
import com.greatcattle.dataservice.common.dto.PageModel;

/**
 *
 * @author Administrator
 */
public class PageService {
    
    private IPageAction action = null;
    
    private PageReqInfo pageReqInfo = null;
    
    public PageService(IPageAction action,PageReqInfo pageReqInfo){
         this.action = action;
         this.pageReqInfo=pageReqInfo;
    }
    
    public void fistPageAction(){
        String pageIndex = "1";
        String pageInfo = pageReqInfo.getjTextFiled().getText();
        String showPageIndex = pageInfo.split("/")[0];
        int calPageIndex = Integer.parseInt(showPageIndex)-1;
        if(calPageIndex<=0){
            return;
        }
        pageReqInfo.getReqParam().put("pageIndex", Integer.parseInt(pageIndex));
        pageReqInfo.getReqParam().put("pageSize", UiUtil.getDefaultPageSize());
        PageModel pageModel = action.executeAction(pageReqInfo.getReqParam());
        UiUtil.transPageModelToTableModel(pageReqInfo.getjTable(), pageModel);
        pageReqInfo.getjTextFiled().setText(UiUtil.showPageInfo(pageModel, pageIndex));
    }
    
    public void endPageAction(){
        String pageInfo = pageReqInfo.getjTextFiled().getText();
        String totalPages = pageInfo.split("/")[1];
        String pageIndex = totalPages;
        pageReqInfo.getReqParam().put("pageIndex", Integer.parseInt(pageIndex));
        pageReqInfo.getReqParam().put("pageSize", UiUtil.getDefaultPageSize());
        PageModel pageModel = action.executeAction(pageReqInfo.getReqParam());
        UiUtil.transPageModelToTableModel(pageReqInfo.getjTable(), pageModel);
        pageReqInfo.getjTextFiled().setText(UiUtil.showPageInfo(pageModel, pageIndex));
    }
    
    public void nextPageAction(){
        String pageInfo = pageReqInfo.getjTextFiled().getText();
        String showPageIndex = pageInfo.split("/")[0];
        String totalPages = pageInfo.split("/")[1];
        int calPageIndex = Integer.parseInt(showPageIndex)+1;
        int maxPageIndex = Integer.parseInt(totalPages);
        if(calPageIndex>maxPageIndex) return;
        String pageIndex = String.valueOf(calPageIndex);
        pageReqInfo.getReqParam().put("pageIndex", Integer.parseInt(pageIndex));
        pageReqInfo.getReqParam().put("pageSize", UiUtil.getDefaultPageSize());
        PageModel pageModel = action.executeAction(pageReqInfo.getReqParam());
        UiUtil.transPageModelToTableModel(pageReqInfo.getjTable(), pageModel);
        pageReqInfo.getjTextFiled().setText(UiUtil.showPageInfo(pageModel, pageIndex));
    }
    
    public void lastPageAction(){
        String pageInfo = pageReqInfo.getjTextFiled().getText();
        String showPageIndex = pageInfo.split("/")[0];
        int calPageIndex = Integer.parseInt(showPageIndex)-1;
        if(calPageIndex==0) return;
        String pageIndex = String.valueOf(calPageIndex);
        pageReqInfo.getReqParam().put("pageIndex", Integer.parseInt(pageIndex));
        pageReqInfo.getReqParam().put("pageSize", UiUtil.getDefaultPageSize());
        PageModel pageModel = action.executeAction(pageReqInfo.getReqParam());
        UiUtil.transPageModelToTableModel(pageReqInfo.getjTable(), pageModel);
        pageReqInfo.getjTextFiled().setText(UiUtil.showPageInfo(pageModel, pageIndex));
    }
    
    public void queryPageAction(){
        String pageIndex = "1";
        pageReqInfo.getReqParam().put("pageIndex", Integer.parseInt(pageIndex));
        pageReqInfo.getReqParam().put("pageSize", UiUtil.getDefaultPageSize());
        PageModel pageModel = action.executeAction(pageReqInfo.getReqParam());
        UiUtil.transPageModelToTableModel(pageReqInfo.getjTable(), pageModel);
        pageReqInfo.getjTextFiled().setText(UiUtil.showPageInfo(pageModel, pageIndex));
    }
    
}
