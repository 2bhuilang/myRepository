/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.greatcattle.business.bo;

import com.greatcattle.business.Sqls;
import com.greatcattle.business.consts.Msg;
import com.greatcattle.dataservice.common.SqlMapExe;
import com.greatcattle.dataservice.common.dto.PageModel;
import com.greatcattle.utils.Const;
import com.greatcattle.utils.StrTools;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class TruckBO {
    
    private SqlMapExe sqlMapExe = SqlMapExe.getInstance();
    
    public PageModel queryTruckInfo(Map queryParam){
        String truckNo = Const.getStrValue(queryParam, "truck_no");
        int pi = Const.getPageIndex(queryParam);
        int ps = Const.getPageSize(queryParam);
        StringBuilder sbuffer = new StringBuilder(Sqls.queryTruckInfoSql);
        ArrayList sqlParam = new ArrayList();
        if(StrTools.isNotEmpty(truckNo)){
            sbuffer.append(" and truck_no = ?");
            sqlParam.add(truckNo);
        }
        PageModel pageModel = sqlMapExe.queryPageModelResult(sbuffer.toString(), sqlParam, pi, ps);
        return pageModel;
    }
    
    public String addTruckInfo(Map entityParam){
        StringBuilder sbuffer = new StringBuilder(Sqls.addTruckSql);
        String truckNo = Const.getStrValue(entityParam, "truck_no");
        String cardNo = Const.getStrValue(entityParam, "card_no");
        String weight = Const.getStrValue(entityParam, "weight");
        String createStaff = Const.getStrValue(entityParam, "create_staff");
        String comment = Const.getStrValue(entityParam, "comment");
        if(StrTools.isEmpty(truckNo)){
            return Msg.TruckNo_isNull.getCode();
        }
        sqlMapExe.excuteUpdate(sbuffer.toString(), new String[]{truckNo,weight,comment,cardNo,createStaff});
        return Msg.Succ.getCode();
    }
}
