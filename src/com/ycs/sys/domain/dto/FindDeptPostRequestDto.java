package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 查询部门岗位信息请求DTO
 * @author youcyousyunn
 * @date 2018年5月18日
 */
public class FindDeptPostRequestDto extends BaseRequestDto {

	/**
     * 部门编号
     */
    private Integer deparNo;

	/**
	 * @return the deparNo
	 */
	public Integer getDeparNo() {
		return deparNo;
	}

	/**
	 * @param deparNo the deparNo to set
	 */
	public void setDeparNo(Integer deparNo) {
		this.deparNo = deparNo;
	}
    
    

}
