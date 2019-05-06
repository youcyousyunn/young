package com.ycs.work.domain.po;

/**
 * @description 通过POS系统发送微信模板消息请求PO
 * @author youcyousyunn
 * @date 2018年11月6日
 */
public class SendWeChatMsgOutPosRequestPo {
    //请求报文
    private String requestJson;
    
    /**
     * @return the requestJson
     */
    public String getRequestJson() {
        return requestJson;
    }
    /**
     * @param requestJson the requestJson to set
     */
    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SendWeChatMsgOutPosRequestPo [requestJson=" + requestJson + "]";
    }
}
