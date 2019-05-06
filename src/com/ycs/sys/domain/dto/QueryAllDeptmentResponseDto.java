package com.ycs.sys.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.sys.domain.po.DeptmentPo;

/**
 * 查询所有部门响应DTO
 * @author youcyousyunn
 * @date 2018年3月14日
 */
public class QueryAllDeptmentResponseDto extends BaseResponseDto {

	/**
     * 部门集合
     */
    private List<DeptmentPo> deptments;

    /**
     * 构造函数
     */
    public QueryAllDeptmentResponseDto() {
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param deptments 部门集合
     */
    public QueryAllDeptmentResponseDto(String responseCode, List<DeptmentPo> deptments) {
        super(responseCode);
        this.deptments = deptments;
    }

    public List<DeptmentPo> getDeptments() {
		return deptments;
	}

	public void setDeptments(List<DeptmentPo> deptments) {
		this.deptments = deptments;
	}

	/**
     * 构造函数
     * @param responseCode 响应代码
     * @param responseInfo 响应信息
     */
    public QueryAllDeptmentResponseDto(String responseCode, String responseInfo) {
        super(responseCode, responseInfo);
    }
    
    

}
