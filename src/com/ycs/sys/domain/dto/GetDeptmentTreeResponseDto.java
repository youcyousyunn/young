package com.ycs.sys.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.sys.domain.vo.TreeVO;

/**
 * 获取部门树形菜单响应DTO
 * @author youcyousyunn
 * @date 2018年3月13日
 */
public class GetDeptmentTreeResponseDto extends BaseResponseDto {

	/**
     * 部门集合
     */
    private List<TreeVO> deptments;

    /**
     * 构造函数
     */
    public GetDeptmentTreeResponseDto() {
        super();
    }
    
    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param responseInfo 响应信息
     */
    public GetDeptmentTreeResponseDto(String responseCode, String responseInfo) {
        super(responseCode, responseInfo);
    }

    /**
     * 构造函数
     * @param responseCode 响应代码
     * @param deptments 部门集合
     */
    public GetDeptmentTreeResponseDto(String responseCode, List<TreeVO> deptments) {
        super(responseCode);
        this.deptments = deptments;
    }

    public List<TreeVO> getDeptments() {
		return deptments;
	}

	public void setDeptments(List<TreeVO> deptments) {
		this.deptments = deptments;
	}
    
    
    
    
    

}
