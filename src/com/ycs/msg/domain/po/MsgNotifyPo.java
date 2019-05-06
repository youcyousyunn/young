package com.ycs.msg.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * 消息通知对象PO
 * @author youcyousyunn
 * @date 2018年6月21日
 */
public class MsgNotifyPo extends NotifyPo {
    //消息ID
    private String msgId;
    //消息标题
    private String msgTitle;
    //消息内容
    private String msgContent;
    //消息类型
    private String msgTyp;
    //发送者用户号
    private String lunchUsrNo;
    //发送类型
    private String sendTyp;
    //动作类型
    private String actionTyp;
    //目标ID
    private String targetId;
    //链接URL
    private String linkUrl;
    //其它扩展自动扩展
    private String extJson;
    
    /**
     * @return the msgId
     */
    public String getMsgId() {
        return msgId;
    }
    /**
     * @param msgId the msgId to set
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    /**
     * @return the msgTitle
     */
    public String getMsgTitle() {
        return msgTitle;
    }
    /**
     * @param msgTitle the msgTitle to set
     */
    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }
    /**
     * @return the msgContent
     */
    public String getMsgContent() {
        return msgContent;
    }
    /**
     * @param msgContent the msgContent to set
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
    /**
     * @return the msgTyp
     */
    public String getMsgTyp() {
        return msgTyp;
    }
    /**
     * @param msgTyp the msgTyp to set
     */
    public void setMsgTyp(String msgTyp) {
        this.msgTyp = msgTyp;
    }
    /**
     * @return the lunchUsrNo
     */
    public String getLunchUsrNo() {
        return lunchUsrNo;
    }
    /**
     * @param lunchUsrNo the lunchUsrNo to set
     */
    public void setLunchUsrNo(String lunchUsrNo) {
        this.lunchUsrNo = lunchUsrNo;
    }
    /**
     * @return the sendTyp
     */
    public String getSendTyp() {
        return sendTyp;
    }
    /**
     * @param sendTyp the sendTyp to set
     */
    public void setSendTyp(String sendTyp) {
        this.sendTyp = sendTyp;
    }
    /**
     * @return the actionTyp
     */
    public String getActionTyp() {
        return actionTyp;
    }
    /**
     * @param actionTyp the actionTyp to set
     */
    public void setActionTyp(String actionTyp) {
        this.actionTyp = actionTyp;
    }
    /**
     * @return the targetId
     */
    public String getTargetId() {
        return targetId;
    }
    /**
     * @param targetId the targetId to set
     */
    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
    /**
     * @return the linkUrl
     */
    public String getLinkUrl() {
        return linkUrl;
    }
    /**
     * @param linkUrl the linkUrl to set
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
    /**
     * @return the extJson
     */
    public String getExtJson() {
        return extJson;
    }
    /**
     * @param extJson the extJson to set
     */
    public void setExtJson(String extJson) {
        this.extJson = extJson;
    }
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MsgNotifyPo [msgId=" + msgId + ", msgTitle=" + msgTitle + ", msgContent=" + msgContent + ", msgTyp="
                + msgTyp + ", lunchUsrNo=" + lunchUsrNo + ", sendTyp=" + sendTyp + ", actionTyp=" + actionTyp
                + ", targetId=" + targetId + ", linkUrl=" + linkUrl + ", extJson=" + extJson + "]";
    }
}
