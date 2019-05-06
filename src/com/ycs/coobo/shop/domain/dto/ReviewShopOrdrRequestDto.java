package com.ycs.coobo.shop.domain.dto;

import org.apache.commons.lang.StringUtils;
import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * @description 门店订单审核请求DTO
 * @author youcyousyunn
 * @date 2018年11月5日
 */
public class ReviewShopOrdrRequestDto extends BaseRequestDto {
    //流程单号
    private String flowOrd;
    //门店订单号
    private String ordNo;
    //流程流水号
    private String flowJrn;
    //审核类型
    private String reviewType;
    
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
     * @return the reviewType
     */
    public String getReviewType() {
        return reviewType;
    }
    /**
     * @param reviewType the reviewType to set
     */
    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }
    /**
     * 接口报文检查
     * @return boolean
     */
    public boolean checkRequestDto() {
        if (null == ordNo || StringUtils.isBlank(ordNo)) {
            return false;
        }
        if (null == flowJrn || StringUtils.isBlank(flowJrn)) {
            return false;
        }
        if (null == flowOrd || StringUtils.isBlank(flowOrd)) {
            return false;
        }
        if (null == reviewType || StringUtils.isBlank(reviewType)) {
            return false;
        }
        return true;
    }
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ReviewShopOrdrRequestDto [flowOrd=" + flowOrd + ", ordNo=" + ordNo + ", flowJrn=" + flowJrn
                + ", reviewType=" + reviewType + "]";
    }
}
