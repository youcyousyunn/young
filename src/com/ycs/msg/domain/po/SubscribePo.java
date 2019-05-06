package com.ycs.msg.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * 用户消息订阅PO
 * @author youcyousyunn
 * @date 2018年6月19日
 */
public class SubscribePo extends BasePo {
    //目标ID
    private String targetId;
    //目标类型
    private String targetTyp;
    //订阅动作
    private String action;
    //消息接收人
    private String usrNo;
    
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
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SubscribePo [targetId=" + targetId + ", targetTyp=" + targetTyp + ", action=" + action + ", usrNo="
                + usrNo + "]";
    }
}
