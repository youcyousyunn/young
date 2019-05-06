package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 查询部门信息请求DTO
 * @author youcyousyunn
 * @date 2018年3月16日
 */
public class QueryDeptmentRequestDto extends BaseRequestDto {

	/**
     * 部门编号
     */
    private Integer deparNo;

	public Integer getDeparNo() {
		return deparNo;
	}

	public void setDeparNo(Integer deparNo) {
		this.deparNo = deparNo;
	}
	
	
    
	
	

}
