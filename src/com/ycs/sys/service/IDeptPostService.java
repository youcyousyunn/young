package com.ycs.sys.service;

import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.sys.domain.dto.FindDeptPostRequestDto;
import com.ycs.sys.domain.dto.FindDeptPostResponseDto;

public interface IDeptPostService {

	public FindDeptPostResponseDto findDeptPost(FindDeptPostRequestDto request) throws HiBusinessReturnException;

	
	
}
