/**
 *
 */
package com.ycs.coobo.coo.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseRequestDto;


/**
 * 查询产品分类树形菜单请求DTO
 * @author youcyousyunn
 * @date 2018年5月23日
 */
public class QueryProductTypeTreeRequestDto extends BaseRequestDto {

    /**
     * 是否统计产品
     */
    private boolean countProduct = false;

    /**
     * 省份
     */
    private String prov;

    /**
     * 城市
     */
    private String city;

    /**
     * 分类
     */
    private List<String> type;

	/**
	 * @return the countProduct
	 */
	public boolean isCountProduct() {
		return countProduct;
	}

	/**
	 * @param countProduct the countProduct to set
	 */
	public void setCountProduct(boolean countProduct) {
		this.countProduct = countProduct;
	}

	/**
	 * @return the prov
	 */
	public String getProv() {
		return prov;
	}

	/**
	 * @param prov the prov to set
	 */
	public void setProv(String prov) {
		this.prov = prov;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the type
	 */
	public List<String> getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(List<String> type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QueryProductTypeTreeRequestDto [countProduct=" + countProduct + ", prov=" + prov + ", city=" + city
				+ ", type=" + type + "]";
	}

    
    
}
