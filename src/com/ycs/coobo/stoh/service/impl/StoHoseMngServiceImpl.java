package com.ycs.coobo.stoh.service.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.coobo.coo.domain.dto.QryStohAllPrdLstRequestDto;
import com.ycs.coobo.coo.domain.dto.QryStohAllPrdLstResponseDto;
import com.ycs.coobo.coo.domain.po.StohPrdPo;
import com.ycs.coobo.stoh.bo.StoHoseMngBo;
import com.ycs.coobo.stoh.service.StoHoseMngService;

@Service
public class StoHoseMngServiceImpl implements StoHoseMngService {

	@Autowired
    private StoHoseMngBo stoHoseMngBo;
	

	@Override
	public QryStohAllPrdLstResponseDto qryStohAllPrdLst(QryStohAllPrdLstRequestDto request)
			throws HiBusinessAbortException, HiBusinessReturnException {
		LinkedHashMap<String, Object> paramMap = new LinkedHashMap<String, Object>();
        paramMap.put("prov", null != request.getProv() ? request.getProv() : null);
        paramMap.put("city", null != request.getCity() ? request.getCity() : null);
        paramMap.put("shopTyp", null != request.getShopTyp() ? request.getShopTyp() : null);
        paramMap.put("shopNo", null != request.getShopNo() ? request.getShopNo() : null);
        paramMap.put("classId", null != request.getClassId() ? request.getClassId() : null);
        paramMap.put("classTyp", null != request.getClassTyp() ? request.getClassTyp() : null);
        paramMap.put("serchTxt", null != request.getSearch() ? "%" + request.getSearch() + "%" : null);
        
        //删除对应的空Key为后面的DaoImpl参数赋值提供处理
        Iterator<Entry<String, Object>> iter = paramMap.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<String, Object> entry = iter.next();
            Object key = entry.getKey();
            if(null == paramMap.get(key) || "".equals(paramMap.get(key))){
                iter.remove();
            }
        }
        
        List<StohPrdPo> stohPrdLst =  stoHoseMngBo.qryStohAllPrdLst(paramMap);
        QryStohAllPrdLstResponseDto response = new QryStohAllPrdLstResponseDto();
        response.setTotal(stohPrdLst.size());
        response.setRows(stohPrdLst);
        return response;
	}
	
	

}
