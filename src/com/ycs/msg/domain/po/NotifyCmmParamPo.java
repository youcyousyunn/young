package com.ycs.msg.domain.po;

import java.util.HashMap;
import com.ycs.base.domain.po.BasePo;

/**
 * 消息发送公共参数对象PO
 * @author youcyousyunn
 * @date 2018年6月21日
 */
public class NotifyCmmParamPo extends BasePo {
    //发消息的用户内部号
    private String lunchUsrNo;
    //收消息的用户内部号
    private String recvUsrNo;
    //消息动作
    private String action;
    //目标ID
    private String targetId;
    //目标类型
    private String targetTyp;
    //消息标题
    private String title;
    //消息内容
    private String content;
    //消息URL
    private String linkUrl;
    //消息类型
    private String type;
    //是否发送微信消息
    private boolean isSendWeChat;
    //扩展字段信息
    private HashMap<String, Object> extJsonMap;
    
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
     * @return the recvUsrNo
     */
    public String getRecvUsrNo() {
        return recvUsrNo;
    }
    /**
     * @param recvUsrNo the recvUsrNo to set
     */
    public void setRecvUsrNo(String recvUsrNo) {
        this.recvUsrNo = recvUsrNo;
    }
    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }
    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
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
     * @return the targetTyp
     */
    public String getTargetTyp() {
        return targetTyp;
    }
    /**
     * @param targetTyp the targetTyp to set
     */
    public void setTargetTyp(String targetTyp) {
        this.targetTyp = targetTyp;
    }
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }
    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
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
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @return the isSendWeChat
     */
    public boolean isSendWeChat() {
        return isSendWeChat;
    }
    /**
     * @param isSendWeChat the isSendWeChat to set
     */
    public void setSendWeChat(boolean isSendWeChat) {
        this.isSendWeChat = isSendWeChat;
    }
    /**
     * @return the extJsonMap
     */
    public HashMap<String, Object> getExtJsonMap() {
        return extJsonMap;
    }
    /**
     * @param extJsonMap the extJsonMap to set
     */
    public void setExtJsonMap(HashMap<String, Object> extJsonMap) {
        this.extJsonMap = extJsonMap;
    }
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "NotifyCmmPramPo [lunchUsrNo=" + lunchUsrNo + ", recvUsrNo=" + recvUsrNo + ", action=" + action
                + ", targetId=" + targetId + ", targetTyp=" + targetTyp + ", title=" + title + ", content=" + content
                + ", linkUrl=" + linkUrl + ", type=" + type + ", isSendWeChat=" + isSendWeChat + ", extJsonMap="
                + extJsonMap + "]";
    }
}
