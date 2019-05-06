package com.ycs.work.bo.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.log4j.logger.HiBizLogger;
import com.ycs.work.bo.OutWeChatMsgBo;
import com.ycs.work.domain.po.SendWeChatMsgOutPosRequestPo;
import com.ycs.work.domain.po.SendWeChatMsgOutPosResponsePo;
import com.ycs.work.wechat.WxHiClientProxy;
import com.ycs.work.wechat.itf.dto.SendWeChatMsgITFResponseDto;
import com.ycs.work.wechat.itf.exception.HiITFException;

/**
 * @description 对接Pos系统微信消息接口实现类
 * @author youcyousyunn
 * @date 2018年11月6日
 */
@Component
public class OutWeChatMsgBoImpl implements OutWeChatMsgBo {

    @Override
    public SendWeChatMsgOutPosResponsePo sendWeChatMsg(SendWeChatMsgOutPosRequestPo requestPo) {
        Map<String, String> paramMap = new HashMap<String, String>();
        SendWeChatMsgOutPosResponsePo responsePo = new SendWeChatMsgOutPosResponsePo();
        try {
            paramMap.put("json", requestPo.getRequestJson());
            HiBizLogger.info("通过Pos系统发送微信模板消息请求报文：" + requestPo.getRequestJson());
            SendWeChatMsgITFResponseDto responseDto = WxHiClientProxy.send("TWX", "sendwechatmsg", paramMap, SendWeChatMsgITFResponseDto.class, "json", "POST");
            if(null != responseDto){
                String retMsg = StringUtils.isNotBlank(responseDto.getMessage())?responseDto.getMessage():"交易成功";
                HiBizLogger.info("通过Pos系统发送微信模板消息数据返回，状态："+responseDto.getCode()+"，描述："+retMsg);
                //跟POS定义1000为成功
                if(!"1000".equals(responseDto.getCode())){
                    return null;
                }
                responsePo.setCode(responseDto.getCode());
                responsePo.setMessage(responseDto.getMessage());
            }
        } catch (HiITFException e) {
            HiBizLogger.error(HiMsgCdConstants.SYS_FAIL, e.getMessage());
            return null;
        }
        return responsePo;
    }
    
}
