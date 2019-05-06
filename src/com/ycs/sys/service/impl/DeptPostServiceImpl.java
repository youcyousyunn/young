package com.ycs.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.sys.bo.IDeptPostBo;
import com.ycs.sys.domain.dto.FindDeptPostRequestDto;
import com.ycs.sys.domain.dto.FindDeptPostResponseDto;
import com.ycs.sys.domain.po.DeptPostPo;
import com.ycs.sys.service.IDeptPostService;

@Service
public class DeptPostServiceImpl implements IDeptPostService {

	/**
     * 部门岗位BO
     */
    @Autowired
    private IDeptPostBo iDeptPostBo;

	@Override
	@Transactional(readOnly=true)
	public FindDeptPostResponseDto findDeptPost(FindDeptPostRequestDto request) throws HiBusinessReturnException {
		FindDeptPostResponseDto response = new FindDeptPostResponseDto();
		if (null != request) {
            List<DeptPostPo> posts = iDeptPostBo.findDeptPost(request.getDeparNo());
            if (null != posts) {
            	response.setPosts(posts);
                return response;
            }
        }
        throw new HiBusinessReturnException(HiMsgCdConstants.QUERY_DEPTPOST_FAIL, "查询部门岗位信息失败");
	}

}
