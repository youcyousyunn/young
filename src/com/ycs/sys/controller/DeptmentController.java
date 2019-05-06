package com.ycs.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycs.basbo.constants.HiMsgCdConstants;
import com.ycs.base.annotation.HiOperLog;
import com.ycs.base.controller.IBaseController;
import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.base.exception.HiException;
import com.ycs.base.log4j.logger.HiBizLogger;
import com.ycs.sys.domain.dto.GetDeptmentTreeRequestDto;
import com.ycs.sys.domain.dto.GetDeptmentTreeResponseDto;
import com.ycs.sys.domain.dto.QueryAllDeptmentRequestDto;
import com.ycs.sys.domain.dto.QueryAllDeptmentResponseDto;
import com.ycs.sys.domain.dto.QueryDeptmentRequestDto;
import com.ycs.sys.domain.dto.QueryDeptmentResponseDto;
import com.ycs.sys.domain.dto.RenameDeptmentRequestDto;
import com.ycs.sys.domain.dto.RenameDeptmentResponseDto;
import com.ycs.sys.domain.dto.UpdateDeptmentRequestDto;
import com.ycs.sys.domain.dto.UpdateDeptmentResponseDto;
import com.ycs.sys.service.IDeptmentService;

/**
 * 部门Controller
 * @author youcyousyunn
 * @date 2018年3月13日
 */
@Controller
@RequestMapping(value = "/deptment/v1")
public class DeptmentController extends IBaseController {

	/**
     * 部门service
     */
    @Autowired
    private IDeptmentService iDeptmentService;

    
    /**
     * 更新部门
     * @param request 请求
     * @return 响应
     */
    @RequestMapping(value = "/update.do")
    @HiOperLog(title = "更新部门", action = "更新操作", isSaveData = true, channel = "WEB")
    public @ResponseBody UpdateDeptmentResponseDto updateDeptment(@RequestBody UpdateDeptmentRequestDto request) throws HiException{
        UpdateDeptmentResponseDto responseDto = new UpdateDeptmentResponseDto();
        //接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        try {
            responseDto = iDeptmentService.updateDeptment(request);
        } catch (HiBusinessAbortException e) {
            // 业务处理终止异常HiBusinessAbortException捕获后，需将异常码设置到传输对象，并返回
            // 业务处理终止异常意为，该错误导致业务无法继续进行
            responseDto.setRspCd(e.getCode());
            return responseDto;
        } catch (HiBusinessReturnException e) {
            // 业务处理返回异常HiBusinessReturnException补货后，需检查code，根据返回做特殊处理，比如：忽略错误等
            responseDto.setRspCd(e.getCode());
            return responseDto;
        }
        responseDto.setRspCd(HiMsgCdConstants.SUCCESS);
        return responseDto;
    }
	
    /**
     * 查询部门树形菜单
     * @param request 请求
     * @return 响应
     */
    @ResponseBody
    @RequestMapping(value = "/tree.do")
    @HiOperLog(title = "查询部门树形菜单", action = "查询操作", isSaveData = true, channel = "WEB")
    public GetDeptmentTreeResponseDto getDeptmentTree(@RequestBody GetDeptmentTreeRequestDto request) {
        try {
            return iDeptmentService.queryDeptmentTree(request);
        } catch (HiBusinessReturnException e) {
            return new GetDeptmentTreeResponseDto(e.getCode(), e.getMessage());
        }
    }
	
    /**
     * 查询所有部门
     * @param request 请求
     * @return 响应
     */
    @ResponseBody
    @RequestMapping(value = "/all.do")
    @HiOperLog(title = "查询所有部门", action = "查询操作", isSaveData = true, channel = "WEB")
    public QueryAllDeptmentResponseDto queryDeptment(@RequestBody QueryAllDeptmentRequestDto request) {
        try {
            return iDeptmentService.queryDeptment(request);
        } catch (HiBusinessReturnException e) {
            return new QueryAllDeptmentResponseDto(e.getCode(), e.getMessage());
        }
    }
	
    /**
     * 更新部门名称
     * @param request 请求
     * @return 响应
     */
    @ResponseBody
    @RequestMapping(value = "/rename.do")
    @HiOperLog(title = "更新部门名称", action = "更新操作", isSaveData = true, channel = "WEB")
    public RenameDeptmentResponseDto renameDeptment(@RequestBody RenameDeptmentRequestDto request) throws HiException{
        
        RenameDeptmentResponseDto responseDto = new RenameDeptmentResponseDto();
        //接口报文检查
        if(!request.checkRequestDto()){
            HiBizLogger.info("接口请求报文异常" + request.toString());
            throw new HiBusinessReturnException(HiMsgCdConstants.TX_REQUESTBODY_FAIL, "接口请求报文异常 ");
        }
        try {
            responseDto = iDeptmentService.renameDeptment(request);
        } catch (HiBusinessReturnException e) {
            // 业务处理返回异常HiBusinessReturnException补货后，需检查code，根据返回做特殊处理，比如：忽略错误等
            responseDto.setRspCd(e.getCode());
            return responseDto;
        }
        responseDto.setRspCd(HiMsgCdConstants.SUCCESS);
        return responseDto;
    }
    
    /**
     * 获取部门信息
     * @param request 请求
     * @return 响应
     */
    @ResponseBody
    @RequestMapping(value = "/get.do")
    @HiOperLog(title = "查询部门", action = "查询操作", isSaveData = true, channel = "WEB")
    public QueryDeptmentResponseDto queryDeptment(@RequestBody QueryDeptmentRequestDto request) {
        try {
            return iDeptmentService.queryDeptment(request);
        } catch (HiBusinessReturnException e) {
            return new QueryDeptmentResponseDto(e.getCode(), e.getMessage());
        }
    }
    
    
    
    
    
    
    
	
	
	
	
	
	
	
	
}
