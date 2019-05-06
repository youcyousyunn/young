package com.ycs.msg.bo;

import java.util.List;
import java.util.Map;

import com.ycs.base.annotation.HiBoMethod;

public interface MsgInfoBo {

	/**
	 * 查询是否存在订阅记录
	 * @param 
	 * @return boolean
	 */
	@HiBoMethod
	public boolean querySubscribe(String targetId, String targetTyp, String actionNo, String lunchUsrNo);

	/**
	 * 创建消息订阅
	 * @param
	 * @return void
	 */
	@HiBoMethod
	public boolean createSubscribe(String targetId, String targetTyp, String actionNo, String recvUsrNo);

	/**
	 * 创建提醒消息
	 * @param 
	 * @return boolean
	 */
	@HiBoMethod
	public boolean createRemind(String targetId, String targetTyp, String action, List<String> recvUsrNoLst,
			String lunchUsrNo, String title, String content, String linkUrl, Map<String, Object> extJsonMap);

	
	
}
