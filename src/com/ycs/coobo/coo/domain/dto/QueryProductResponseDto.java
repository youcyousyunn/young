package com.ycs.coobo.coo.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.coobo.coo.domain.po.ProductPo;

/**
 * 查询商品响应DTO
 * @author youcyousyunn
 * @date 2018年5月15日
 */
public class QueryProductResponseDto extends BaseResponseDto {

	//总数
    private Integer total;
    
    //列表数据
    private List<ProductPo> rows;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<ProductPo> getRows() {
		return rows;
	}

	public void setRows(List<ProductPo> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "QueryProductResponseDto [total=" + total + ", rows=" + rows + "]";
	}
    
    

}
