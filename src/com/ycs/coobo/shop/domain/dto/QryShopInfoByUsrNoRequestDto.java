package com.ycs.coobo.shop.domain.dto;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 根据内部用户号查询单个门店信息请求DTO
 * @author youcyousyunn
 * @date 2018年5月19日
 */
public class QryShopInfoByUsrNoRequestDto extends BaseRequestDto {

	/**
	 * 接口报文检查
	 * @param
	 * @return boolean
	 */
	public boolean checkRequestDto() {
        if (null == usrNo) {
            return false;
        }
        return true;
    }
	
}
