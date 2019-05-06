package com.ycs.sys.domain.dto;

import org.apache.commons.lang.StringUtils;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * @description 流程单据信息请求
 * @author youcyousyunn
 * @date 2018年10月7日
 */
public class QryFlowOrdInfoRequestDto extends BaseRequestDto {
    //流程订单号
    private String ordNo;
    //流程单号
    private String flowOrd;
    
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
     * @description 接口报文检查
     * @param
     * @return boolean
     */
    public boolean checkRequestDto() {
        if ((null == ordNo || StringUtils.isBlank(ordNo)) && (null == flowOrd || StringUtils.isBlank(flowOrd))) {
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
        return "QryFlowOrdInfoRequestDto [ordNo=" + ordNo + ", flowOrd=" + flowOrd + "]";
    }
}
