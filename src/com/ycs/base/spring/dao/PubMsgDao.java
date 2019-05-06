package com.ycs.base.spring.dao;

import com.ycs.base.spring.domain.po.PubMsgPo;

public interface PubMsgDao {

	PubMsgPo queryMsgInf(String msgCd);
	
}
