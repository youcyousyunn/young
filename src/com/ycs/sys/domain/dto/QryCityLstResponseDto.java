package com.ycs.sys.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.sys.domain.po.ProvCityPo;

/**
 * 省份城市列表响应DTO
 * @author youcyousyunn
 * @date 2018年5月15日
 */
public class QryCityLstResponseDto extends BaseResponseDto {

	//城市列表数据
    private List<ProvCityPo> row;

	public List<ProvCityPo> getRow() {
		return row;
	}

	public void setRow(List<ProvCityPo> row) {
		this.row = row;
	}

	@Override
	public String toString() {
		return "QryCityLstResponseDto [row=" + row + "]";
	}
    
    
    

}
