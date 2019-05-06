package com.ycs.msg.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * 用户消息队列对象PO
 * @author youcyousyunn
 * @date 2018年6月21日
 */
public class UserNotifyPo extends BasePo {
    //队列ID
    private String notifId;
    //是否已读
    private String isRead;
    //是否星标
    private String isStar;
    //内部用户号
    private String usrNo;
    //消息ID
    private String msgId;
    
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
     * @return the usrNo
     */
    public String getUsrNo() {
        return usrNo;
    }
    /**
     * @param usrNo the usrNo to set
     */
    public void setUsrNo(String usrNo) {
        this.usrNo = usrNo;
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
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserNotifyPo [notifId=" + notifId + ", isRead=" + isRead + ", isStar=" + isStar + ", usrNo=" + usrNo
                + ", msgId=" + msgId + "]";
    }
}
