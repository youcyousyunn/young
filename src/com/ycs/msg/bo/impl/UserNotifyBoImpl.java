package com.ycs.msg.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.msg.bo.UserNotifyBo;
import com.ycs.msg.dao.UserNotifyDao;
import com.ycs.msg.domain.po.UserNotifyPo;

@Component
public class UserNotifyBoImpl implements UserNotifyBo {

	@Autowired
    private UserNotifyDao userNotifyDao;
	
	
	@Override
	public boolean isReadMsg(String msgId, String usrNo) {
		int result = -1;
		UserNotifyPo userNotifyPo = new UserNotifyPo();
		userNotifyPo.setMsgId(msgId);
		userNotifyPo.setUsrNo(usrNo);
		result = userNotifyDao.isReadMsg(userNotifyPo);
		if (1 == result){
			return true;
		}
		return false;
	}
	
	
	
}
