package com.ycs.work.bo;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.work.domain.po.SendWeChatMsgOutPosRequestPo;
import com.ycs.work.domain.po.SendWeChatMsgOutPosResponsePo;

/**
 * @description 对接Pos系统微信消息接口BO
 * @author youcyousyunn
 * @date 2018年11月6日
 */
public interface OutWeChatMsgBo {
    
    /**
     * 通过POS系统发送微信模板消息
     * @param requestPo
     * @return SendWeChatMsgOutPosResponsePo
     */
	@HiBoMethod
    public SendWeChatMsgOutPosResponsePo sendWeChatMsg(SendWeChatMsgOutPosRequestPo requestPo);
}
