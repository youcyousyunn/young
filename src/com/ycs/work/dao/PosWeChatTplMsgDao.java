package com.ycs.work.dao;

import java.util.List;

import com.ycs.work.domain.po.WeMsgArrtPo;
import com.ycs.work.domain.po.WechatMsgPo;

public interface PosWeChatTplMsgDao {

	WechatMsgPo qryWechatMsg(String tempId);

	List<WeMsgArrtPo> qryWechatMsgArrts(String tempId);


}
