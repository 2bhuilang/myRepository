/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.greatcattle.business.service;

import com.greatcattle.business.bo.TruckBO;
import com.greatcattle.business.service.intf.BaseEntity;
import com.greatcattle.dataservice.common.dto.PageModel;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class TruckService implements BaseEntity{

    @Autowired
    private TruckBO truckBO;
    @Override
    public PageModel queryEntity(Map queryParam) {
        return truckBO.queryTruckInfo(queryParam);
    }

    @Override
    public String addEntity(Map addParam) {
        return truckBO.addTruckInfo(addParam);
    }

    @Override
    public String delEntity(Map delParam) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String updateEntity(Map updateParam) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
