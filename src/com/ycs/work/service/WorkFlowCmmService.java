package com.ycs.work.service;

import java.util.LinkedHashMap;

import com.ycs.base.exception.HiBusinessAbortException;
import com.ycs.base.exception.HiBusinessReturnException;
import com.ycs.work.domain.po.WorkFlowCmmParamPo;

public interface WorkFlowCmmService {

	/**
	 * 门店下单流程初始化
	 * @param workFlowCmmPramPo
	 * @return boolean
	 * @throws HiBusinessAbortException,HiBusinessReturnException
	 */
	boolean workFlowStart(WorkFlowCmmParamPo workFlowCmmPramPo) throws HiBusinessAbortException,HiBusinessReturnException;

	/**
	 * 流程结束
	 * @param workFlowCmmParamPo
	 * @return boolean
	 */
	boolean workFlowEnd(WorkFlowCmmParamPo workFlowCmmParamPo) throws HiBusinessAbortException,HiBusinessReturnException;

	/**
	 * 流程进行中
	 * @param workFlowCmmParamPo
	 * @return boolean
	 */
	boolean workFlowOnIng(WorkFlowCmmParamPo workFlowCmmParamPo)throws HiBusinessAbortException,HiBusinessReturnException;
	
	/**
	 * 通过POS系统发送微信模板消息
	 * @param pramMap
	 */
	public void sendWeChatMsgByPos(LinkedHashMap<String, Object> pramMap)throws HiBusinessAbortException,HiBusinessReturnException;

}
