package com.ycs.base.spring.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.base.spring.bo.TxIfBo;
import com.ycs.base.spring.dao.TxIfDao;
import com.ycs.base.spring.domain.po.TxIfPo;

@Component
public class TxIfBoImpl implements TxIfBo {

	@Autowired
	private TxIfDao txIfDao;

	@Override
	public TxIfPo queryTxIf(String url) {
		return txIfDao.queryTxIf(url);
	}

}
