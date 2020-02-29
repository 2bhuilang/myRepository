/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.greatcattle.framework;

import com.greatcattle.dataservice.common.dto.PageModel;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public interface IPageAction {
    
    public PageModel executeAction(Map reqParam);
    
}
