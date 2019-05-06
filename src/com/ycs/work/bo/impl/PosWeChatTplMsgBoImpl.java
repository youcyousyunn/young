package com.ycs.work.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ycs.work.bo.OutWeChatMsgBo;
import com.ycs.work.bo.PosWeChatTplMsgBo;
import com.ycs.work.dao.PosWeChatTplMsgDao;
import com.ycs.work.domain.po.SendWeChatMsgOutPosRequestPo;
import com.ycs.work.domain.po.SendWeChatMsgOutPosResponsePo;
import com.ycs.work.domain.po.WeMsgArrtPo;
import com.ycs.work.domain.po.WechatMsgPo;

@Component
public class PosWeChatTplMsgBoImpl implements PosWeChatTplMsgBo {
	@Autowired
    private OutWeChatMsgBo outWeChatMsgBo;
	@Autowired
    private PosWeChatTplMsgDao posWeChatTplMsgDao;
	
	@Override
	public WechatMsgPo qryWechatMsg(String tempId) {
		WechatMsgPo wechatMsgPo = posWeChatTplMsgDao.qryWechatMsg(tempId);
		List<WeMsgArrtPo> data = posWeChatTplMsgDao.qryWechatMsgArrts(tempId);
		wechatMsgPo.setData(data);
		return wechatMsgPo;
	}

	@Override
	public boolean sendWeChatMsg(String jsonStr) {
		SendWeChatMsgOutPosRequestPo requestPo = new SendWeChatMsgOutPosRequestPo();
        requestPo.setRequestJson(jsonStr);
        SendWeChatMsgOutPosResponsePo responsePo = outWeChatMsgBo.sendWeChatMsg(requestPo);
        if(null != responsePo){
            return true;
        }
        return false;
	}

}
