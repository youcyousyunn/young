package com.ycs.coobo.compan.domain.dto;

import org.apache.commons.lang.StringUtils;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 查询运营公司信息请求DTO
 * @author youcyousyunn
 * @date 2018年3月19日
 */
public class QryCompanInfoRequestDto extends BaseRequestDto {

	//运营公司号
    private String cotldNo;
    
    public String getCotldNo() {
		return cotldNo;
	}
	public void setCotldNo(String cotldNo) {
		this.cotldNo = cotldNo;
	}

	/**
	 * 请求接口报文检查
	 * @param
	 * @return boolean
	 */
	public boolean checkRequestDto() {
        if (null == cotldNo || StringUtils.isBlank(cotldNo)) {
            return false;
        }
        return true;
    }
    
    
    

}
