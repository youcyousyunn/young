package com.ycs.sys.bo;

import java.util.LinkedHashMap;
import java.util.List;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.sys.domain.po.FlowActionPo;
import com.ycs.work.domain.po.FlowInfoPo;
import com.ycs.work.domain.po.FlowJrnPo;
import com.ycs.work.domain.po.FlowOrdPo;

/**
 * 工作流BO
 * @author youcyousyunn
 * @date 2018年6月19日
 */
public interface FlowBo {

	/**
	 * 查询执行动作列表数
	 * @param
	 * @return int
	 */
	@HiBoMethod
	public int qryFlowActionLstCount(LinkedHashMap<String, Object> paramMap);

	/**
	 *  查询执行动作列表
	 * @param
	 * @return List<FlowActionPo>
	 */
	@HiBoMethod
	public List<FlowActionPo> qryFlowActionLst(LinkedHashMap<String, Object> paramMap, Integer startRow,
			Integer offset);

	/**
	 * 更改流程动作部门或者岗位
	 * @param
	 * @return boolean
	 */
	@HiBoMethod
	public boolean updFlowAction(LinkedHashMap<String, Object> paramMap);

	/**
	 * 查询流程定义信息
	 * @param
	 * @return FlowInfoPo
	 */
	@HiBoMethod
	public FlowInfoPo queryFlowInfo(String flowNo);

	/**
	 * 查询流程动作信息
	 * @param
	 * @return FlowActionPo
	 */
	@HiBoMethod
	public FlowActionPo queryFlowAction(String actionNo, String nodeNo);

	/**
	 * 流程创建时添加唯一单据信息
	 * @param
	 * @return boolean
	 */
	@HiBoMethod
	public boolean addFlowOrd(FlowOrdPo flowOrdPo);

	/**
	 * 添加流程流水信息
	 * @param
	 * @return boolean
	 */
	@HiBoMethod
	public boolean addFlowJrn(FlowJrnPo flowJrnPo);

	/**
	 * 查询流程流水信息
	 * @param flowJrn流水号
	 * @return JrnNo
	 */
	public FlowJrnPo queryFlowJrn(String JrnNo);

	/**
	 * 更新流程下一步奏审核人
	 * @param reviewUsrNo审核人
	 * @param flowOrdNo流程单号
	 * @return boolean
	 */
	@HiBoMethod
	public boolean updateFlowOrdRecevUsr(String recevUsrNo, String flowOrdNo);

	/**
	 * 查询流程唯一单据信息
	 * @param flowOrdNo
	 * @return FlowOrdPo
	 */
    public FlowOrdPo queryFlowOrd(String flowOrdNo);

    /**
     * 查询流程唯一单据信息
     * @param ordNo 
     * @return FlowOrdPo
     */
	public FlowOrdPo queryFlowOrdByOrdNo(String ordNo, String ordSts);

	/**
	 * 查询流程流水信息
	 * @param jrnNo
	 * @param ordNo
	 * @param flowOrd
	 * @return List<FlowJrnPo>
	 */
	public List<FlowJrnPo> qryFlowJrnLst(String jrnNo, String ordNo, String flowOrd);

	/**
	 * 更新流程订单执行步奏
	 * @param nodeNo
	 * @param downStep
	 * @param flowOrdNo
	 */
	@HiBoMethod
	public boolean updFlowOrdStep(String actionStep, String downStep, String flowOrdNo);

	/**
	 * 更新流程流水状态
	 * @param flowJrnSts
	 * @param flowJrnNo
	 * @return boolean
	 */
	@HiBoMethod
	public boolean updFlowJrnSts(String flowJrnSts, String flowJrnNo);

	/**
	 * 更新流程单据状态
	 * @param flowOrdNo
	 * @param flowOrdSts
	 * @return boolean
	 */
	@HiBoMethod
	public boolean updFlowOrdSts(String flowOrdNo, String flowSts, boolean isEnd);

	
	
}
