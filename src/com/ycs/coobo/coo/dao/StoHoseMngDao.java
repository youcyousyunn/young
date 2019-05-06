package com.ycs.coobo.coo.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.ycs.coobo.coo.domain.po.StohPrdPo;
import com.ycs.coobo.stoh.domain.po.StoHoseOrdrDetailPo;
import com.ycs.coobo.stoh.domain.po.StoHoseOrdrPo;
import com.ycs.coobo.stoh.domain.po.StoHosePo;

public interface StoHoseMngDao {

	public List<StohPrdPo> qryStohAllPrdLst(LinkedHashMap<String, Object> paramMap);

	public StoHosePo qryStoHoseInfoByStohNo(String stohNo);

	public int addStoHoseOrdr(StoHoseOrdrPo stoHoseOrdrPo);

	public int addStoHoseOrdrDetail(List<StoHoseOrdrDetailPo> stoHoseOrdrDetailPoLst);

}
