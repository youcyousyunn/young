package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 城市列表请求DTO
 * @author youcyousyunn
 * @date 2018年5月15日
 */
public class QryCityLstRequestDto extends BaseRequestDto {

	//省份城市编码
    private String provCityCd;

	public String getProvCityCd() {
		return provCityCd;
	}

	public void setProvCityCd(String provCityCd) {
		this.provCityCd = provCityCd;
	}
    
    

}
