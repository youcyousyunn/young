package com.ycs.sys.domain.dto;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.sys.domain.po.DeptmentPo;

/**
 * 查询部门信息响应DTO
 * @author youcyousyunn
 * @date 2018年3月16日
 */
public class QueryDeptmentResponseDto extends BaseResponseDto {

	/**
     * 部门信息
     */
    private DeptmentPo deptment;
    
    
    /**
     * 构造函数
     */
    public QueryDeptmentResponseDto() {
        super();
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param responseInfo 响应信息
     */
    public QueryDeptmentResponseDto(String responseCode, String responseInfo) {
        super(responseCode, responseInfo);
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param deptment 部门
     */
    public QueryDeptmentResponseDto(String responseCode, DeptmentPo deptment) {
        super(responseCode);
        this.deptment = deptment;
    }

	public DeptmentPo getDeptment() {
		return deptment;
	}

	public void setDeptment(DeptmentPo deptment) {
		this.deptment = deptment;
	}
    
    
    

	
	
	
	

}
