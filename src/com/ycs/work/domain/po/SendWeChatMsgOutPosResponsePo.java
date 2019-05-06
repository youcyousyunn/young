package com.ycs.work.domain.po;

/**
 * @description 通过POS系统发送微信模板消息返回PO
 * @author youcyousyunn
 * @date 2018年11月6日
 */
public class SendWeChatMsgOutPosResponsePo {
    //错误码
    private String code;
    //错误信息
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
        return "SendWeChatMsgOutPosResponsePo [code=" + code + ", message=" + message + "]";
    }
}
