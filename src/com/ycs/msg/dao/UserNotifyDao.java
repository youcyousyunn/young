package com.ycs.msg.dao;

import com.ycs.msg.domain.po.UserNotifyPo;

/**
 * 用户消息状态相关接口Dao
 * @author youcyousyunn
 * @date 2018年6月21日
 */
public interface UserNotifyDao {

	/**
	 * 增加用户消息队列
	 * @param userNotifyPo
	 * @return boolean
	 */
	boolean addUserNotify(UserNotifyPo userNotifyPo);

	/**
	 * 更新消息已读未读
	 * @param userNotifyPo
	 * @return boolean
	 */
	int isReadMsg(UserNotifyPo userNotifyPo);

	
}
