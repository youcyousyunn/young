package com.ycs.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.annotation.HiOperLog;
import com.ycs.base.controller.IBaseController;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.sys.domain.dto.FindDeptPostRequestDto;
import com.ycs.sys.domain.dto.FindDeptPostResponseDto;
import com.ycs.sys.service.IDeptPostService;

/**
 * 部门岗位Controller
 * @author youcyousyunn
 * @date 2018年5月18日
 */
@Controller
@RequestMapping("/deptpost/v1")
public class DeptPostController extends IBaseController {

	/**
     * 部门岗位service
     */
    @Autowired
    private IDeptPostService deptPostService;
    
    
    /**
     * 查询部门岗位信息
     * @param
     * @return FindDeptPostResponseDto
     */
    @ResponseBody
    @RequestMapping(value = "/find.do")
    @HiOperLog(title = "查询部门岗位信息", action = "查询操作", isSaveData = true, channel = "WEB")
    public FindDeptPostResponseDto findDeptPost(@RequestBody FindDeptPostRequestDto request) {
    	FindDeptPostResponseDto responseDto = new FindDeptPostResponseDto();
        try {
        	responseDto = deptPostService.findDeptPost(request);
        } catch (HiBusinessReturnException e) {
        	responseDto.setRspCd(e.getCode());
        	return responseDto;
        }
        responseDto.setRspCd(HiMsgCdConstants.SUCCESS);
        return responseDto;
    }
    
    

}
