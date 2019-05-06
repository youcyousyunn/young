package com.ycs.base.spring.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.base.spring.bo.PubMsgBo;
import com.ycs.base.spring.dao.PubMsgDao;
import com.ycs.base.spring.domain.po.PubMsgPo;

@Component
public class PubMsgBoImpl implements PubMsgBo {

	@Autowired
	private PubMsgDao pubMsgDao;

	@Cacheable({"PUBMSG_CACHE"})
	@HiBoMethod
	public PubMsgPo queryMsgInf(String msgCd) {
		return pubMsgDao.queryMsgInf(msgCd);
	}

}
