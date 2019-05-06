package com.ycs.coobo.stoh.bo.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.base.utils.DateUtil;
import com.ycs.coobo.coo.dao.StoHoseMngDao;
import com.ycs.coobo.coo.domain.po.StohPrdPo;
import com.ycs.coobo.stoh.bo.StoHoseMngBo;
import com.ycs.coobo.stoh.domain.po.StoHoseOrdrDetailPo;
import com.ycs.coobo.stoh.domain.po.StoHoseOrdrPo;
import com.ycs.coobo.stoh.domain.po.StoHosePo;

@Component
public class StoHoseMngBoImpl implements StoHoseMngBo {

	@Autowired
	private StoHoseMngDao stoHoseMngDao;

	
	@Override
	public List<StohPrdPo> qryStohAllPrdLst(LinkedHashMap<String, Object> paramMap) {
		String lastWeekFirstDay = DateUtil.getUpWeekDay(DateUtil.dataFormatyyyy_MM_dd, 1);
        String lastWeekLastDay = DateUtil.getUpWeekDay(DateUtil.dataFormatyyyy_MM_dd, 7);
        String nowDate = DateUtil.getDateStringByFormatString(DateUtil.dataFormatyyyy_MM_dd);
        String upDate = DateUtil.getWeekDay(DateUtil.dataFormatyyyy_MM_dd, 1);
        paramMap.put("lastWeekFirstDay", lastWeekFirstDay);
        paramMap.put("lastWeekLastDay", lastWeekLastDay);
        paramMap.put("nowDate", nowDate);
        paramMap.put("upDate", upDate);
		return stoHoseMngDao.qryStohAllPrdLst(paramMap);
	}

	@Override
	public StoHosePo qryStoHoseInfoByStohNo(String stohNo) {
		return stoHoseMngDao.qryStoHoseInfoByStohNo(stohNo);
	}

	@Override
	public boolean addStoHoseOrdr(StoHoseOrdrPo stoHoseOrdrPo) {
		int result = -1;
		result = stoHoseMngDao.addStoHoseOrdr(stoHoseOrdrPo);
		if (1 == result) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addStoHoseOrdrDetail(List<StoHoseOrdrDetailPo> stoHoseOrdrDetailPoLst) {
		int result = -1;
		result = stoHoseMngDao.addStoHoseOrdrDetail(stoHoseOrdrDetailPoLst);
		if (1 <= result) {
			return true;
		}
		return false;
	}
	
	
	
	

}
