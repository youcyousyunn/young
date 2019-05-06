package com.ycs.msg.dao;

import java.util.List;
import java.util.Map;

import com.ycs.msg.domain.po.SubscribePo;

public interface SubscribeDao {

	int querySubscribe(Map<String, Object> paramMap);

	int createSubscribe(Map<String, Object> paramMap);

	List<SubscribePo> qrySubscribeLst(Map<String, Object> paramMaps);

}
