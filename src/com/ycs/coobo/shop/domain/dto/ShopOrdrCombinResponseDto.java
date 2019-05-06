package com.ycs.coobo.shop.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.coobo.shop.domain.po.ShopOrdrCombinPo;

/**
 * @description 采购合并门店订单响应DTO
 * @author youcyousyunn
 * @date 2018年11月11日
 */
public class ShopOrdrCombinResponseDto extends BaseResponseDto {
    //总数
    private Integer total;
    //列表数据
    private List<ShopOrdrCombinPo> rows;
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
    public List<ShopOrdrCombinPo> getRows() {
        return rows;
    }
    /**
     * @param rows the rows to set
     */
    public void setRows(List<ShopOrdrCombinPo> rows) {
        this.rows = rows;
    }
    /* （non Javadoc）
     * Title: toString<br/>
     * <p>Description: <br/>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ShopOrdrCombinResponseDto [total=" + total + ", rows=" + rows + "]";
    }
}
