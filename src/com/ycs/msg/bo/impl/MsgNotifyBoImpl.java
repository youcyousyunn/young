package com.ycs.msg.bo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.base.domain.dto.BaseDto;
import com.ycs.base.exception.HiException;
import com.ycs.msg.bo.MsgNotifyBo;
import com.ycs.msg.dao.MsgNotifyDao;
import com.ycs.msg.domain.po.MsgNotifyPo;
import com.ycs.msg.domain.po.NotifyPo;

@Component
public class MsgNotifyBoImpl implements MsgNotifyBo {
	
	@Autowired
	private MsgNotifyDao msgNotifyDao;

	@Override
	public int qryMsgNotifyCount(Map<String, Object> paramMap) {
		return msgNotifyDao.qryMsgNotifyCount(paramMap);
	}

	@Override
	public List<NotifyPo> qryMsgNotifyPage(Map<String, Object> paramMap, Integer startRow, Integer offset) {
		List<NotifyPo> result = new ArrayList<>();
		paramMap.put("startRow", startRow);
		paramMap.put("offset", offset);
		List<MsgNotifyPo> msgNotifyLst = msgNotifyDao.qryMsgNotifyPage(paramMap);
		try {
			for (MsgNotifyPo item : msgNotifyLst){
				NotifyPo notifyPo = new NotifyPo();
				notifyPo = BaseDto.entity2Trans(item, NotifyPo.class);
				result.add(notifyPo);
			}
        } catch (HiException e) {
            e.printStackTrace();
        }
		return result;
	}

	@Override
	public MsgNotifyPo qryMsgNotifyByMsgId(String msgId) {
		return msgNotifyDao.qryMsgNotifyByMsgId(msgId);
	}

	@Override
	public boolean addMsgNotify(MsgNotifyPo msgNotifyPo) {
		int result = msgNotifyDao.addMsgNotify(msgNotifyPo);
		if(result > 0){
			return true;
		}
		return	false;
	}

	@Override
	public int qryNotifyCount(Map<String, Object> paramMap) {
		return msgNotifyDao.qryNotifyCount(paramMap);
	}

	@Override
	public List<MsgNotifyPo> qryNotifyPage(Map<String, Object> paramMap, Integer startRow, Integer offset) {
		paramMap.put("startRow", startRow);
		paramMap.put("offset", offset);
		List<MsgNotifyPo> notifyLst = msgNotifyDao.qryNotifyPage(paramMap);
		return notifyLst;
	}
	
}
