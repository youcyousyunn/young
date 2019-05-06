package com.ycs.sys.domain.dto;

import org.apache.commons.lang.StringUtils;
import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * @description 查询流程流水信息请求DTO
 * @author youcyousyunn
 * @date 2018年11月5日
 */
public class QryFlowJrnLstRequestDto extends BaseRequestDto {
    //流水号
    private String jrnNo;
    //流程单号
    private String flowOrd;
    //订单号
    private String ordNo;
    
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
     * 接口报文检查
     * @return boolean
     */
    public boolean checkRequestDto() {
        if (null == flowOrd || StringUtils.isBlank(flowOrd)) {
            if(null == ordNo || StringUtils.isBlank(ordNo)){
                if(null == jrnNo || StringUtils.isBlank(jrnNo)){
                    return false;
                }
            }
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
        return "QryFlowJrnLstRequestDto [jrnNo=" + jrnNo + ", flowOrd=" + flowOrd + ", ordNo=" + ordNo + "]";
    }
}
