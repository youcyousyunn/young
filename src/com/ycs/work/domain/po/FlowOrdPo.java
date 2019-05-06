package com.ycs.work.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * 流程信息唯一单据PO
 * @author youcyousyunn
 * @date 2018年6月21日
 */
public class FlowOrdPo extends BasePo {
    private String flowOrd;//流程单号
    private String ordNo;//订单号
    private String txDt;//交易日期
    private String txTm;//交易时间
    private String expDt;//失效日期
    private String expTm;//失效时间
    private String actionStep;//进行中的步奏节点
    private String downStep;//下一个步奏节点
    private String sendCnl;//发起渠道
    private String accpCnl;//接收渠道
    private String flowSts;//流程状态
    private String lunchUsrNo;//发起人内部用户号
    private String recevUsrNo;//审核人内部用户号
    private String endDt;//结束日期
    private String endTm;//结束时间
    private String isFollow;//用户标记
    private String flowNo;//流程号
    private String flowAction;//动作号
    
    /**
     * @return the flowOrd
     */
    public String getFlowOrd() {
        return flowOrd;
    }
    /**
     * @param flowOrd the flowOrd to set
     */
    public void setFlowOrd(String flowOrd) {
        this.flowOrd = flowOrd;
    }
    /**
     * @return the ordNo
     */
    public String getOrdNo() {
        return ordNo;
    }
    /**
     * @param ordNo the ordNo to set
     */
    public void setOrdNo(String ordNo) {
        this.ordNo = ordNo;
    }
    /**
     * @return the txDt
     */
    public String getTxDt() {
        return txDt;
    }
    /**
     * @param txDt the txDt to set
     */
    public void setTxDt(String txDt) {
        this.txDt = txDt;
    }
    /**
     * @return the txTm
     */
    public String getTxTm() {
        return txTm;
    }
    /**
     * @param txTm the txTm to set
     */
    public void setTxTm(String txTm) {
        this.txTm = txTm;
    }
    /**
     * @return the expDt
     */
    public String getExpDt() {
        return expDt;
    }
    /**
     * @param expDt the expDt to set
     */
    public void setExpDt(String expDt) {
        this.expDt = expDt;
    }
    /**
     * @return the expTm
     */
    public String getExpTm() {
        return expTm;
    }
    /**
     * @param expTm the expTm to set
     */
    public void setExpTm(String expTm) {
        this.expTm = expTm;
    }
    /**
     * @return the actionStep
     */
    public String getActionStep() {
        return actionStep;
    }
    /**
     * @param actionStep the actionStep to set
     */
    public void setActionStep(String actionStep) {
        this.actionStep = actionStep;
    }
    /**
     * @return the downStep
     */
    public String getDownStep() {
        return downStep;
    }
    /**
     * @param downStep the downStep to set
     */
    public void setDownStep(String downStep) {
        this.downStep = downStep;
    }
    /**
     * @return the sendCnl
     */
    public String getSendCnl() {
        return sendCnl;
    }
    /**
     * @param sendCnl the sendCnl to set
     */
    public void setSendCnl(String sendCnl) {
        this.sendCnl = sendCnl;
    }
    /**
     * @return the accpCnl
     */
    public String getAccpCnl() {
        return accpCnl;
    }
    /**
     * @param accpCnl the accpCnl to set
     */
    public void setAccpCnl(String accpCnl) {
        this.accpCnl = accpCnl;
    }
    /**
     * @return the flowSts
     */
    public String getFlowSts() {
        return flowSts;
    }
    /**
     * @param flowSts the flowSts to set
     */
    public void setFlowSts(String flowSts) {
        this.flowSts = flowSts;
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
     * @return the recevUsrNo
     */
    public String getRecevUsrNo() {
        return recevUsrNo;
    }
    /**
     * @param recevUsrNo the recevUsrNo to set
     */
    public void setRecevUsrNo(String recevUsrNo) {
        this.recevUsrNo = recevUsrNo;
    }
    /**
     * @return the endDt
     */
    public String getEndDt() {
        return endDt;
    }
    /**
     * @param endDt the endDt to set
     */
    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }
    /**
     * @return the endTm
     */
    public String getEndTm() {
        return endTm;
    }
    /**
     * @param endTm the endTm to set
     */
    public void setEndTm(String endTm) {
        this.endTm = endTm;
    }
    /**
     * @return the isFollow
     */
    public String getIsFollow() {
        return isFollow;
    }
    /**
     * @param isFollow the isFollow to set
     */
    public void setIsFollow(String isFollow) {
        this.isFollow = isFollow;
    }
    /**
     * @return the flowNo
     */
    public String getFlowNo() {
        return flowNo;
    }
    /**
     * @param flowNo the flowNo to set
     */
    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo;
    }
    /**
     * @return the flowAction
     */
    public String getFlowAction() {
        return flowAction;
    }
    /**
     * @param flowAction the flowAction to set
     */
    public void setFlowAction(String flowAction) {
        this.flowAction = flowAction;
    }
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FlowOrdPo [flowOrd=" + flowOrd + ", ordNo=" + ordNo + ", txDt=" + txDt + ", txTm=" + txTm + ", expDt="
                + expDt + ", expTm=" + expTm + ", actionStep=" + actionStep + ", downStep=" + downStep + ", sendCnl="
                + sendCnl + ", accpCnl=" + accpCnl + ", flowSts=" + flowSts + ", lunchUsrNo=" + lunchUsrNo
                + ", recevUsrNo=" + recevUsrNo + ", endDt=" + endDt + ", endTm=" + endTm + ", isFollow=" + isFollow
                + ", flowNo=" + flowNo + ", flowAction=" + flowAction + "]";
    }
}
