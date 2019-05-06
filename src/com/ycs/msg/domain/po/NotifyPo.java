package com.ycs.msg.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * 前端展示用消息PO对象
 * @author youcyousyunn
 * @date 2018年6月25日
 */
public class NotifyPo extends BasePo {
    //队列ID
    private String notifId;
    //是否已读
    private String isRead;
    //是否星标
    private String isStar;
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
    //发送者用户名
    private String lunchUsrNm;
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
     * @return the notifId
     */
    public String getNotifId() {
        return notifId;
    }
    /**
     * @param notifId the notifId to set
     */
    public void setNotifId(String notifId) {
        this.notifId = notifId;
    }
    /**
     * @return the isRead
     */
    public String getIsRead() {
        return isRead;
    }
    /**
     * @param isRead the isRead to set
     */
    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }
    /**
     * @return the isStar
     */
    public String getIsStar() {
        return isStar;
    }
    /**
     * @param isStar the isStar to set
     */
    public void setIsStar(String isStar) {
        this.isStar = isStar;
    }
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
     * @return the lunchUsrNm
     */
    public String getLunchUsrNm() {
        return lunchUsrNm;
    }
    /**
     * @param lunchUsrNm the lunchUsrNm to set
     */
    public void setLunchUsrNm(String lunchUsrNm) {
        this.lunchUsrNm = lunchUsrNm;
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

}
