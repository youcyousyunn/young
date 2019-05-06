package com.ycs.sys.service;

import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.sys.domain.dto.QryFlowActionLstRequestDto;
import com.ycs.sys.domain.dto.QryFlowActionLstResponseDto;
import com.ycs.sys.domain.dto.QryFlowJrnLstRequestDto;
import com.ycs.sys.domain.dto.QryFlowJrnLstResponseDto;
import com.ycs.sys.domain.dto.QryFlowOrdInfoRequestDto;
import com.ycs.sys.domain.dto.QryFlowOrdInfoResponseDto;
import com.ycs.sys.domain.dto.UpdFlowActionRequestDto;
import com.ycs.sys.domain.dto.UpdFlowActionResponseDto;

public interface FlowOrdMngService {

	/**
	 * 查询流程动作列表
	 * @param
	 * @return QryFlowActionLstResponseDto
	 * @throws HiBusinessAbortException,HiBusinessReturnException
	 */
	public QryFlowActionLstResponseDto qryFlowActionLst(QryFlowActionLstRequestDto request) throws HiBusinessAbortException,HiBusinessReturnException;

	/**
	 * 更改流程动作部门或者岗位
	 * @param
	 * @return UpdFlowActionResponseDto
	 * @throws 
	 */
	public UpdFlowActionResponseDto updFlowAction(UpdFlowActionRequestDto request)throws HiBusinessAbortException,HiBusinessReturnException;

	/**
     * 流程单据信息获取
     * @param request
     * @return QryFlowOrdInfoResponseDto
     * @throws HiBusinessAbortException,HiBusinessReturnException
     */
	public QryFlowOrdInfoResponseDto getFlowOrdInfo(QryFlowOrdInfoRequestDto request)throws HiBusinessAbortException,HiBusinessReturnException;

	/**
	 * 查询流程流水信息
	 * @param request
	 * @return QryFlowJrnLstResponseDto
	 * @throws HiBusinessAbortException,HiBusinessReturnException
	 */
	public QryFlowJrnLstResponseDto qryFlowJrnLst(QryFlowJrnLstRequestDto request)throws HiBusinessAbortException,HiBusinessReturnException;

	
	
}
