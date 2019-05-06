package com.ycs.msg.bo.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.basbo.utils.JrnGenerator;
import com.ycs.base.utils.DateUtil;
import com.ycs.base.utils.JsonUtils;
import com.ycs.msg.bo.MsgInfoBo;
import com.ycs.msg.dao.MsgNotifyDao;
import com.ycs.msg.dao.SubscribeDao;
import com.ycs.msg.dao.UserNotifyDao;
import com.ycs.msg.domain.po.MsgNotifyPo;
import com.ycs.msg.domain.po.SubscribePo;
import com.ycs.msg.domain.po.UserNotifyPo;

@Component
public class MsgInfoBoImpl implements MsgInfoBo {

	@Autowired
    private SubscribeDao subscribeDao;
	@Autowired
    private MsgNotifyDao msgNotifyDao;
    @Autowired
    private UserNotifyDao userNotifyDao;
	
	
	@Override
	public boolean querySubscribe(String targetId, String targetTyp, String actionNo, String lunchUsrNo) {
		Map<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("targetId", targetId);
		paramMap.put("targetTyp", targetTyp);
		paramMap.put("actionNo", actionNo);
		paramMap.put("lunchUsrNo", lunchUsrNo);
		int count = subscribeDao.querySubscribe(paramMap);
		if (1 == count){
			return true;
		}
		return false;
	}

	@Override
	public boolean createSubscribe(String targetId, String targetTyp, String actionNo, String recvUsrNo) {
		Map<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("targetId", targetId);
		paramMap.put("targetTyp", targetTyp);
		paramMap.put("actionNo", actionNo);
		paramMap.put("recvUsrNo", recvUsrNo);
		int result = subscribeDao.createSubscribe(paramMap);
		if (1 == result){
			return true;
		}
		return false;
	}

	@Override
	public boolean createRemind(String targetId, String targetTyp, String action, List<String> recvUsrNoLst,
			String lunchUsrNo, String title, String content, String linkUrl, Map<String, Object> extJsonMap) {
		// 1、添加平台公共推送消息
		MsgNotifyPo msgNotifyPo = new MsgNotifyPo();
        msgNotifyPo.setMsgId(JrnGenerator.genJrnNo());
        msgNotifyPo.setMsgTitle(title);
        msgNotifyPo.setMsgContent(content);
        msgNotifyPo.setMsgTyp("REMIND");
        msgNotifyPo.setLunchUsrNo(lunchUsrNo);
        msgNotifyPo.setSendTyp("FAST");//立即发送
        msgNotifyPo.setActionTyp(targetTyp);
        msgNotifyPo.setTargetId(targetId);
        msgNotifyPo.setExtJson(JsonUtils.map2json(extJsonMap));
        msgNotifyPo.setLinkUrl(linkUrl);
        msgNotifyPo.setCreDt(DateUtil.getDateStringByFormatString(DateUtil.dataFormatyyyy_MM_dd));
        msgNotifyPo.setCreTm(DateUtil.getDateStringByFormatString(DateUtil.dataFormatHH_mm_ss));
        msgNotifyDao.addMsgNotify(msgNotifyPo);
        
        //判定哪些人有订阅此事件,订阅了则需要新增一条未读记录
        Map<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("targetId", targetId);
		paramMap.put("targetTyp", null != targetTyp ? targetTyp : null);
		paramMap.put("action", action);
        List<SubscribePo> subscribePoLst = subscribeDao.qrySubscribeLst(paramMap);
        
        // 2、添加用户所有的消息队列
        for (String usrNo : recvUsrNoLst) {
            UserNotifyPo userNotifyPo = new UserNotifyPo();
            userNotifyPo.setMsgId(msgNotifyPo.getMsgId());
            userNotifyPo.setUsrNo(usrNo);
            userNotifyDao.addUserNotify(userNotifyPo);
            for (SubscribePo subscribePo : subscribePoLst) {
                if(usrNo.equals(subscribePo.getUsrNo())){
                    continue;
                }
                userNotifyPo.setUsrNo(subscribePo.getUsrNo());
                if(!subscribePo.getUsrNo().equals(lunchUsrNo)){
                    userNotifyDao.addUserNotify(userNotifyPo);
                }
            }
        }
        return true;
	}

	
	
}
