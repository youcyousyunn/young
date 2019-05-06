package com.ycs.work.domain.po;

import com.ycs.base.domain.po.BasePo;


/**
 * 流程订阅公共参数对象PO
 * @author youcyousyunn
 * @date 2018年6月19日
 */
public class WorkFlowCmmParamPo extends BasePo {
    //流程订单号
    private String flowOrdNo;
    //流程号
    private String flowNo;
    //动作号
    private String actionNo;
    //节点号
    private String nodeNo;
    //流程上一个步骤流水号
    private String flowJrn;
    //目标ID
    private String targetId;
    //目标类型
    private String targetTyp;
    //发起方
    private String initiator;
    //发起用户号
    private String lunchUsrNo;
    //接收用户号
    private String recvUsrNo;
    //消息标题
    private String title;
    //消息内容
    private String content;
    //消息URL
    private String linkUrl;
    //消息内容
    private String type;
    
    /**
     * @return the flowOrd
     */
    public String getFlowOrdNo() {
        return flowOrdNo;
    }
    /**
     * @param flowOrd the flowOrd to set
     */
    public void setFlowOrdNo(String flowOrdNo) {
        this.flowOrdNo = flowOrdNo;
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
     * @return the flowJrn
     */
    public String getFlowJrn() {
        return flowJrn;
    }
    /**
     * @param flowJrn the flowJrn to set
     */
    public void setFlowJrn(String flowJrn) {
        this.flowJrn = flowJrn;
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
     * @return the initiator
     */
    public String getInitiator() {
        return initiator;
    }
    /**
     * @param initiator the initiator to set
     */
    public void setInitiator(String initiator) {
        this.initiator = initiator;
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
    
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "WorkFlowCmmPramPo [flowOrdNo=" + flowOrdNo + ", flowNo=" + flowNo + ", actionNo=" + actionNo
                + ", nodeNo=" + nodeNo + ", flowJrn=" + flowJrn + ", targetId=" + targetId + ", targetTyp=" + targetTyp
                + ", initiator=" + initiator + ", lunchUsrNo=" + lunchUsrNo + ", recvUsrNo=" + recvUsrNo + ", title="
                + title + ", content=" + content + ", linkUrl=" + linkUrl + ", type=" + type + "]";
    }
    
}
