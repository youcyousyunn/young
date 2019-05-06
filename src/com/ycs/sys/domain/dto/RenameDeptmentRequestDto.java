package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseRequestDto;
import org.apache.commons.lang.StringUtils;

/**
 * 更改部门名称请求DTO
 * @author youcyousyunn
 * @date 2018年3月16日
 */
public class RenameDeptmentRequestDto extends BaseRequestDto {

	/**
     * 部门编号
     */
    private Integer deptmentNo;

    /**
     * 部门名称
     */
    private String deptmentName;

	public Integer getDeptmentNo() {
		return deptmentNo;
	}

	public void setDeptmentNo(Integer deptmentNo) {
		this.deptmentNo = deptmentNo;
	}

	public String getDeptmentName() {
		return deptmentName;
	}

	public void setDeptmentName(String deptmentName) {
		this.deptmentName = deptmentName;
	}
    
	/**
     * 接口请求报文检查
     */
    public boolean checkRequestDto() {
        if (null == deptmentNo) {
            return false;
        }
        if (StringUtils.isBlank(deptmentName)) {
            return false;
        }
        return true;
    }
    
    

}
