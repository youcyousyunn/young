package com.ycs.coobo.stoh.bo;

import java.util.LinkedHashMap;
import java.util.List;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.coobo.coo.domain.po.StohPrdPo;
import com.ycs.coobo.stoh.domain.po.StoHoseOrdrDetailPo;
import com.ycs.coobo.stoh.domain.po.StoHoseOrdrPo;
import com.ycs.coobo.stoh.domain.po.StoHosePo;

public interface StoHoseMngBo {

	/**
	 * 查询仓库可供产品信息
	 * @param paramMap
	 * @return List<StohPrdPo>
	 */
	@HiBoMethod
	public List<StohPrdPo> qryStohAllPrdLst(LinkedHashMap<String, Object> paramMap);

	/**
	 * 根据仓库号查询仓库信息
	 * @param stohNo
	 * @return StoHosePo
	 */
	@HiBoMethod
	public StoHosePo qryStoHoseInfoByStohNo(String stohNo);

	/**
	 * 添加仓库订单
	 * @param stoHoseOrdrPo
	 * @return boolean
	 */
	@HiBoMethod
	public boolean addStoHoseOrdr(StoHoseOrdrPo stoHoseOrdrPo);

	/**
	 * 添加仓库订单明细
	 * @param stoHoseOrdrDetailPoLst
	 * @return boolean
	 */
	@HiBoMethod
	public boolean addStoHoseOrdrDetail(List<StoHoseOrdrDetailPo> stoHoseOrdrDetailPoLst);
	

}
