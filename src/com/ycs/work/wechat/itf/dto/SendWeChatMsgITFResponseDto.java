package com.ycs.work.wechat.itf.dto;

import com.ycs.work.wechat.itf.dto.BaseITFResponseDto;

/**
 * @description 发送微信模板消息响应DTO
 * @author youcyousyunn
 * @date 2018年11月6日
 */
public class SendWeChatMsgITFResponseDto extends BaseITFResponseDto {
    //错误码
    private String code;
    //错误消息
    private String message;
    
    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }
    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SendWeChatMsgITFResponseDto [code=" + code + ", message=" + message + "]";
    }
}
