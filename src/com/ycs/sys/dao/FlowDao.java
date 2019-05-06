package com.ycs.sys.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ycs.sys.domain.po.FlowActionPo;
import com.ycs.work.domain.po.FlowJrnPo;
import com.ycs.work.domain.po.FlowOrdPo;

public interface FlowDao {

	int qryFlowActionLstCount(LinkedHashMap<String, Object> paramMap);

	List<FlowActionPo> qryFlowActionLst(LinkedHashMap<String, Object> paramMap);

	int updFlowAction(LinkedHashMap<String, Object> paramMap);

	List<Map<String, Object>> queryFlowInfo(String flowNo);

	FlowActionPo queryFlowAction(Map<String, Object> paramMap);

	int addFlowOrd(Map<String, Object> paramMap);

	int addFlowJrn(Map<String, Object> paramMap);

	List<Map<String, Object>> queryFlowJrn(Map<String, Object> paramMap);

	int updateFlowOrdRecevUsr(Map<String, Object> paramMap);

	FlowOrdPo queryFlowOrd(String flowOrdNo);

	FlowOrdPo queryFlowOrdByOrdNo(Map<String, Object> requestMap);

	List<FlowJrnPo> qryFlowJrnLst(Map<String, Object> paramMap);

	int updFlowOrdStep(HashMap<String, Object> paramMap);

	int updFlowJrnSts(HashMap<String, Object> paramMap);

	int updFlowOrdSts(HashMap<String, Object> paramMap);

	
	
}
