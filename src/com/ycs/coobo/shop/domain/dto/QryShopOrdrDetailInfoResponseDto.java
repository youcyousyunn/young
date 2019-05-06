package com.ycs.coobo.shop.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.coobo.shop.domain.po.ShopOrdrDetailPo;

/**
 * 查询门店订单明细响应DTO
 * @author youcyousyunn
 * @date 2018年6月13日
 */
public class QryShopOrdrDetailInfoResponseDto extends BaseResponseDto {
    //总数
    private Integer total;
    //订单明细列表
    private List<ShopOrdrDetailPo> rows;
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
	public List<ShopOrdrDetailPo> getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<ShopOrdrDetailPo> rows) {
		this.rows = rows;
	}

}
