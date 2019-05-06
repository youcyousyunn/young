package com.ycs.work.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * 流程信息唯一流水PO
 * @author youcyousyunn
 * @date 2018年6月21日
 */
public class FlowJrnPo extends BasePo {
    private String jrnNo;//流水号
    private String txDt;//交易日期
    private String txTm;//交易时间
    private String flowOrd;//流程单号
    private String ordNo;//订单号
    private String nodeNo;//当前动作节点
    private String upStep;//上一个动作节点
    private String downStep;//下一个动作节点
    private String txSts;//交易状态
    private String txUsrNo;//交易人内部用户号
    private String txUsrNm;//交易人
    private Integer txDeparNo;//交易人部门
    private String txType;//交易类型
    private String txCoent;//交易内容
    
    /**
     * @return the jrnNo
     */
    public String getJrnNo() {
        return jrnNo;
    }
    /**
     * @param jrnNo the jrnNo to set
     */
    public void setJrnNo(String jrnNo) {
        this.jrnNo = jrnNo;
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
     * @return the nodeNo
     */
    public String getNodeNo() {
        return nodeNo;
    }
    /**
     * @param nodeNo the nodeNo to set
     */
    public void setNodeNo(String nodeNo) {
        this.nodeNo = nodeNo;
    }
    /**
     * @return the upStep
     */
    public String getUpStep() {
        return upStep;
    }
    /**
     * @param upStep the upStep to set
     */
    public void setUpStep(String upStep) {
        this.upStep = upStep;
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
     * @return the txSts
     */
    public String getTxSts() {
        return txSts;
    }
    /**
     * @param txSts the txSts to set
     */
    public void setTxSts(String txSts) {
        this.txSts = txSts;
    }
    /**
     * @return the txUsrNo
     */
    public String getTxUsrNo() {
        return txUsrNo;
    }
    /**
     * @param txUsrNo the txUsrNo to set
     */
    public void setTxUsrNo(String txUsrNo) {
        this.txUsrNo = txUsrNo;
    }
    /**
     * @return the txUsrNm
     */
    public String getTxUsrNm() {
        return txUsrNm;
    }
    /**
     * @param txUsrNm the txUsrNm to set
     */
    public void setTxUsrNm(String txUsrNm) {
        this.txUsrNm = txUsrNm;
    }
    /**
     * @return the txType
     */
    public String getTxType() {
        return txType;
    }
    /**
     * @param txType the txType to set
     */
    public void setTxType(String txType) {
        this.txType = txType;
    }
    /**
     * @return the txDeparNo
     */
    public Integer getTxDeparNo() {
        return txDeparNo;
    }
    /**
     * @param txDeparNo the txDeparNo to set
     */
    public void setTxDeparNo(Integer txDeparNo) {
        this.txDeparNo = txDeparNo;
    }
    /**
     * @return the txCoent
     */
    public String getTxCoent() {
        return txCoent;
    }
    /**
     * @param txCoent the txCoent to set
     */
    public void setTxCoent(String txCoent) {
        this.txCoent = txCoent;
    }
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FlowJrnPo [jrnNo=" + jrnNo + ", txDt=" + txDt + ", txTm=" + txTm + ", flowOrd=" + flowOrd + ", ordNo="
                + ordNo + ", nodeNo=" + nodeNo + ", upStep=" + upStep + ", downStep=" + downStep + ", txSts=" + txSts
                + ", txUsrNo=" + txUsrNo + ", txUsrNm=" + txUsrNm + ", txDeparNo=" + txDeparNo + ", txType=" + txType
                + ", txCoent=" + txCoent + "]";
    }
}
