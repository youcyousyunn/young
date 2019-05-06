package com.ycs.coobo.coo.domain.dto;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 查询商品请求DTO
 * @author youcyousyunn
 * @date 2018年5月15日
 */
public class QueryProductRequestDto extends BaseRequestDto {

    /**
     * 省
     */
    private String prov;
    
    /**
     * 市
     */
    private String city;
    
    /**
     * 商品类别
     */
    private String classType;
    
    /**
     * 搜索关键词
     */
    private String search;
    
    /**
     * 当前页
     */
    private Integer currentPage;
    
    /**
     * 页大小
     */
    private Integer pageSize;

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
    

}
