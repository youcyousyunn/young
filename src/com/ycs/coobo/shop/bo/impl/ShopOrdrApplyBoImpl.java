package com.ycs.coobo.shop.bo.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.base.domain.dto.BaseDto;
import com.ycs.base.exception.HiException;
import com.ycs.coobo.shop.bo.ShopOrdrApplyBo;
import com.ycs.coobo.shop.dao.ShopOrdrApplyDao;
import com.ycs.coobo.shop.domain.po.ShopOrdrCombinPo;
import com.ycs.coobo.shop.domain.po.ShopOrdrDetailPo;
import com.ycs.coobo.shop.domain.po.ShopOrdrPo;

@Component
public class ShopOrdrApplyBoImpl implements ShopOrdrApplyBo {

	@Autowired
    private ShopOrdrApplyDao shopOrdrApplyDao;

	@Override
	public int qryShopOrdrCount(LinkedHashMap<String, Object> paramMap) {
		return shopOrdrApplyDao.qryShopOrdrCount(paramMap);
	}

	@Override
	public List<ShopOrdrPo> qryShopOrdrLst(LinkedHashMap<String, Object> paramMap, Integer startRow, Integer offset) {
		paramMap.put("startRow", startRow);
		paramMap.put("offset", offset);
		return shopOrdrApplyDao.qryShopOrdrLst(paramMap);
	}

	@Override
	public boolean addShopOrdr(ShopOrdrPo shopOrdrPo) {
		return shopOrdrApplyDao.addShopOrdr(shopOrdrPo);
	}

	@Override
	public boolean addShopOrdrDetail(List<ShopOrdrDetailPo> shopOrdrDetailLst) {
		List<Map<String, Object>> paramList = new LinkedList<>();
		for (ShopOrdrDetailPo shopOrdrDetailPo : shopOrdrDetailLst){
			Map<String, Object> map = null;
			try {
				map = BeanUtils.describe(shopOrdrDetailPo);
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
			}
			paramList.add(map);
		}
		int result = shopOrdrApplyDao.addShopOrdrDetail(shopOrdrDetailLst);
		if (result > 0){
			return true;
		}
		return false;
	}

	@Override
	public int qryShopOrdrDetailCountByOrdNo(String ordNo) {
		return shopOrdrApplyDao.qryShopOrdrDetailCountByOrdNo(ordNo);
	}

	@Override
	public List<ShopOrdrDetailPo> qryShopOrdrDetailLstByOrdNo(String ordNo, Integer startRow, Integer offset) {
		List<ShopOrdrDetailPo> result = new ArrayList<>();
		List<ShopOrdrPo> result_ = new ArrayList<>();
		LinkedHashMap<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("ordNo", ordNo);
		paramMap.put("startRow", startRow);
		paramMap.put("offset", offset);
		result_ = shopOrdrApplyDao.qryShopOrdrDetailLstByOrdNo(paramMap);
		for (ShopOrdrPo shopOrdrPo : result_){
			try {
				ShopOrdrDetailPo shopOrdrDetailPo = BaseDto.entity2Trans(shopOrdrPo, ShopOrdrDetailPo.class);
				result.add(shopOrdrDetailPo);
			} catch (HiException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}

	@Override
	public ShopOrdrPo qryShopOrdInfoByOrdNo(String ordNo) {
		return shopOrdrApplyDao.qryShopOrdInfoByOrdNo(ordNo);
	}

	@Override
	public ShopOrdrPo qryShopOrdInfo(String ordNo) {
		return shopOrdrApplyDao.qryShopOrdInfo(ordNo);
	}

	@Override
	public boolean updShopOrdrSts(String shopOrdrSts, String ordNo) {
		int result = -1;
		Map<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("shopOrdrSts", shopOrdrSts);
		paramMap.put("ordNo", ordNo);	
		result = shopOrdrApplyDao.updShopOrdrSts(paramMap);
		if (1 == result) {
			return true;
		}
		return false;
	}

	@Override
	public List<ShopOrdrPo> qryShopOrdInfoLstByOrdrNoLst(List<String> shopOrdNoLst) {
		return shopOrdrApplyDao.qryShopOrdInfoLstByOrdrNoLst(shopOrdNoLst);
	}

	@Override
	public List<ShopOrdrCombinPo> shopOrdrCombin(LinkedHashMap<String, Object> paramMap) {
		return shopOrdrApplyDao.shopOrdrCombin(paramMap);
	}

	@Override
	public List<ShopOrdrDetailPo> qryShopOrdrDetailsByOrdNo(String ordNo) {
		return shopOrdrApplyDao.qryShopOrdrDetailsByOrdNo(ordNo);
	}
	

}
