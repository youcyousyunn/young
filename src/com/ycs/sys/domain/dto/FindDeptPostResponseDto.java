package com.ycs.sys.domain.dto;

import java.util.List;

import com.ycs.base.domain.dto.BaseResponseDto;
import com.ycs.sys.domain.po.DeptPostPo;

/**
 * 查询部门岗位信息响应DTO
 * @author youcyousyunn
 * @date 2018年5月18日
 */
public class FindDeptPostResponseDto extends BaseResponseDto {

	/**
     * 部门岗位信息
     */
    private List<DeptPostPo> posts;

	/**
	 * @return the posts
	 */
	public List<DeptPostPo> getPosts() {
		return posts;
	}

	/**
	 * @param posts the posts to set
	 */
	public void setPosts(List<DeptPostPo> posts) {
		this.posts = posts;
	}
    

}
