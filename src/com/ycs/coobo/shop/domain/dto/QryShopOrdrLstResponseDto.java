package com.ycs.coobo.shop.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.coobo.shop.domain.po.ShopOrdrPo;

/**
 * 查询门店订单列表数据响应DTO
 * @author youcyousyunn
 * @date 2018年5月19日
 */
public class QryShopOrdrLstResponseDto extends BaseResponseDto {

	//总数
    private Integer total;
    //列表数据
    private List<ShopOrdrPo> rows;
	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	/**
	 * @return the rows
	 */
	public List<ShopOrdrPo> getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<ShopOrdrPo> rows) {
		this.rows = rows;
	}
    

}
