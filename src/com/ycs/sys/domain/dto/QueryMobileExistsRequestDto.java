package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 检查手机号是否存在请求DTO
 * @author youcyousyunn
 * @date 2018年3月15日
 */
public class QueryMobileExistsRequestDto extends BaseRequestDto {

	/**
     * 手机号
     */
    private String mobileNo;

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
    
    
    
    

}
