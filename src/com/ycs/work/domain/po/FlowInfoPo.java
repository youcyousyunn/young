package com.ycs.work.domain.po;

import com.ycs.base.domain.po.BasePo;

/**
 * 流程定义PO
 * @author youcyousyunn
 * @date 2018年6月19日
 */
public class FlowInfoPo extends BasePo{
    private String flowNo;//流程号
    private String flowNm;//流程名称
    private String flowDesc;//流程描述
    private String actionNo;//动作序号
    private String flowTyp;//流程类型
    private String attach;//归属业务
    private String staNode;//开始节点
    private String endNode;//结束节点
    private String flowSts;//流程状态
    private String updDesc;//修改描述
    
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
     * @return the flowNm
     */
    public String getFlowNm() {
        return flowNm;
    }
    /**
     * @param flowNm the flowNm to set
     */
    public void setFlowNm(String flowNm) {
        this.flowNm = flowNm;
    }
    /**
     * @return the flowDesc
     */
    public String getFlowDesc() {
        return flowDesc;
    }
    /**
     * @param flowDesc the flowDesc to set
     */
    public void setFlowDesc(String flowDesc) {
        this.flowDesc = flowDesc;
    }
    /**
     * @return the actionNo
     */
    public String getActionNo() {
        return actionNo;
    }
    /**
     * @param actionNo the actionNo to set
     */
    public void setActionNo(String actionNo) {
        this.actionNo = actionNo;
    }
    /**
     * @return the flowTyp
     */
    public String getFlowTyp() {
        return flowTyp;
    }
    /**
     * @param flowTyp the flowTyp to set
     */
    public void setFlowTyp(String flowTyp) {
        this.flowTyp = flowTyp;
    }
    /**
     * @return the attach
     */
    public String getAttach() {
        return attach;
    }
    /**
     * @param attach the attach to set
     */
    public void setAttach(String attach) {
        this.attach = attach;
    }
    /**
     * @return the staNode
     */
    public String getStaNode() {
        return staNode;
    }
    /**
     * @param staNode the staNode to set
     */
    public void setStaNode(String staNode) {
        this.staNode = staNode;
    }
    /**
     * @return the endNode
     */
    public String getEndNode() {
        return endNode;
    }
    /**
     * @param endNode the endNode to set
     */
    public void setEndNode(String endNode) {
        this.endNode = endNode;
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
     * @return the updDesc
     */
    public String getUpdDesc() {
        return updDesc;
    }
    /**
     * @param updDesc the updDesc to set
     */
    public void setUpdDesc(String updDesc) {
        this.updDesc = updDesc;
    }
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FlowInfoPo [flowNo=" + flowNo + ", flowNm=" + flowNm + ", flowDesc=" + flowDesc + ", actionNo="
                + actionNo + ", flowTyp=" + flowTyp + ", attach=" + attach + ", staNode=" + staNode + ", endNode="
                + endNode + ", flowSts=" + flowSts + ", updDesc=" + updDesc
                + "]";
    }
}
