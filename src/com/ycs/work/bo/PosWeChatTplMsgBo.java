package com.ycs.work.bo;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.work.domain.po.WechatMsgPo;

public interface PosWeChatTplMsgBo {
	
	/**
	 * 查询微信模板消息
	 * @param tempId
	 * @return WechatMsgPo
	 */
    @HiBoMethod
    public WechatMsgPo qryWechatMsg(String tempId);

    /**
     * 通过POS系统发送微信模板消息
     * @param jsonStr
     * @return boolean
     */
    @HiBoMethod
	public boolean sendWeChatMsg(String jsonStr);
    
    
}
