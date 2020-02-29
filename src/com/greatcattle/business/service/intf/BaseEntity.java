/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.greatcattle.business.service.intf;

import com.greatcattle.dataservice.common.dto.PageModel;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public interface BaseEntity {
    
    public PageModel queryEntity(Map queryParam);
    
    public String addEntity(Map addParam);
    
    public String delEntity(Map delParam);
    
    public String updateEntity(Map updateParam);
    
}
