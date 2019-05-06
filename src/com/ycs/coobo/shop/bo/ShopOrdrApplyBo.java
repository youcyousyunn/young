package com.ycs.coobo.shop.bo;

import java.util.LinkedHashMap;
import java.util.List;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.coobo.shop.domain.po.ShopOrdrCombinPo;
import com.ycs.coobo.shop.domain.po.ShopOrdrDetailPo;
import com.ycs.coobo.shop.domain.po.ShopOrdrPo;

public interface ShopOrdrApplyBo {

	/**
	 * 门店订单列表条数
	 * @param paramMap
	 * @return int
	 */
	@HiBoMethod
	public int qryShopOrdrCount(LinkedHashMap<String, Object> paramMap);

	/**
	 * 门店订单列表
	 * @param paramMap
	 * @param startRow
	 * @param offset
	 * @return List<ShopOrdrPo>
	 */
	@HiBoMethod
	public List<ShopOrdrPo> qryShopOrdrLst(LinkedHashMap<String, Object> paramMap, Integer startRow, Integer offset);

	/**
	 * 新增门店订单
	 * @param shopOrdrPo
	 * @return boolean
	 */
	@HiBoMethod
	public boolean addShopOrdr(ShopOrdrPo shopOrdrPo);

	/**
	 * 新增门店订单明细
	 * @param
	 * @return boolean
	 */
	@HiBoMethod
	public boolean addShopOrdrDetail(List<ShopOrdrDetailPo> shopOrdrDetailLst);

	/**
	 * 根据订单号查询明细信息条数
	 * @param
	 * @return int
	 */
	@HiBoMethod
	public int qryShopOrdrDetailCountByOrdNo(String ordNo);

	/**
	 * 根据订单号查询明细分页数据
	 * @param
	 * @return List<ShopOrdrDetailPo>
	 */
	@HiBoMethod
	public List<ShopOrdrDetailPo> qryShopOrdrDetailLstByOrdNo(String ordNo, Integer startRow, Integer offset);
	
	/**
	 * 根据订单号查询订单
	 * @param
	 * @return ShopOrdrPo
	 */
	@HiBoMethod
	public ShopOrdrPo qryShopOrdInfoByOrdNo(String ordNo);

	/**
	 * 查询单个门店信息对象
	 * @param ordNo
	 * @return ShopOrdrPo
	 */
	@HiBoMethod
	public ShopOrdrPo qryShopOrdInfo(String ordNo);

	/**
	 * 更新门店订单状态
	 * @param shopOrdrSts
	 * @param ordNo
	 */
	@HiBoMethod
	public boolean updShopOrdrSts(String shopOrdrSts, String ordNo);

	/**
	 * 根据订单号列表查询对应的门店订单对象
	 * @param shopOrdNoLst
	 * @return List<ShopOrdrPo>
	 */
	@HiBoMethod
	public List<ShopOrdrPo> qryShopOrdInfoLstByOrdrNoLst(List<String> shopOrdNoLst);

	/**
	 * 采购合并门店订单
	 * @param 
	 * @return List<ShopOrdrCombinPo>
	 */
	@HiBoMethod
	public List<ShopOrdrCombinPo> shopOrdrCombin(LinkedHashMap<String, Object> paramMap);

	/**
	 * 根据订单号查询订单明细列表
	 * @param ordNo
	 * @return List<ShopOrdrDetailPo>
	 */
	@HiBoMethod
	public List<ShopOrdrDetailPo> qryShopOrdrDetailsByOrdNo(String ordNo);
	
	
}
