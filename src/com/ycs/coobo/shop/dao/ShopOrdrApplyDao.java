package com.ycs.coobo.shop.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ycs.coobo.shop.domain.po.ShopOrdrCombinPo;
import com.ycs.coobo.shop.domain.po.ShopOrdrDetailPo;
import com.ycs.coobo.shop.domain.po.ShopOrdrPo;

public interface ShopOrdrApplyDao {

	int qryShopOrdrCount(LinkedHashMap<String, Object> paramMap);

	List<ShopOrdrPo> qryShopOrdrLst(LinkedHashMap<String, Object> paramMap);

	boolean addShopOrdr(ShopOrdrPo shopOrdrPo);

	int addShopOrdrDetail(List<ShopOrdrDetailPo> shopOrdrDetailLst);

	int qryShopOrdrDetailCountByOrdNo(String ordNo);

	List<ShopOrdrPo> qryShopOrdrDetailLstByOrdNo(LinkedHashMap<String, Object> paramMap);

	ShopOrdrPo qryShopOrdInfoByOrdNo(String ordNo);

	ShopOrdrPo qryShopOrdInfo(String ordNo);

	int updShopOrdrSts(Map<String, Object> paramMap);

	List<ShopOrdrPo> qryShopOrdInfoLstByOrdrNoLst(List<String> shopOrdNoLst);

	List<ShopOrdrCombinPo> shopOrdrCombin(LinkedHashMap<String, Object> paramMap);

	List<ShopOrdrDetailPo> qryShopOrdrDetailsByOrdNo(String ordNo);

	
	
	
}
