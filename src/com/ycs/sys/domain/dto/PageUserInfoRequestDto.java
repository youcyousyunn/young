package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseRequestDto;

/**
 * 分页查询用户信息DTO
 * @author youcyousyunn
 * @date 2018年3月13日
 */
public class PageUserInfoRequestDto extends BaseRequestDto {

	/**
     * 部门编号
     */
    private Integer deparNo;

    /**
     * 文本查询
     */
    private String search;

    /**
     * 性别
     */
    private String sex;

    /**
     * 状态
     */
    private String status;

    /**
     * 排序列
     */
    private String sort;

    /**
     * 排序方法(DESC、ASC)
     */
    private String sortName;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 页大小
     */
    private Integer pageSize;

	public Integer getDeparNo() {
		return deparNo;
	}

	public void setDeparNo(Integer deparNo) {
		this.deparNo = deparNo;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
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
