package com.ycs.msg.bo;

import com.ycs.base.annotation.HiBoMethod;

/**
 * 用户消息相关BO接口
 * @author youcyousyunn
 * @date 2018年6月26日
 */
public interface UserNotifyBo {

	/**
	 * 更新消息已读未读
	 * @param
	 * @return boolean
	 */
	@HiBoMethod
	boolean isReadMsg(String msgId, String usrNo);

	
	
	
}
