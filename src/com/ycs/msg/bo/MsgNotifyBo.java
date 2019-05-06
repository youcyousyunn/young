package com.ycs.msg.bo;

import java.util.List;
import java.util.Map;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.msg.domain.po.MsgNotifyPo;
import com.ycs.msg.domain.po.NotifyPo;

public interface MsgNotifyBo {

	/**
	 * 读取消息列表条数
	 * @param 
	 * @return int
	 */
	@HiBoMethod
	int qryMsgNotifyCount(Map<String, Object> paramMap);

	/**
	 * 读取消息列表
	 * @param 
	 * @return List<NotifyPo>
	 */
	@HiBoMethod
	List<NotifyPo> qryMsgNotifyPage(Map<String, Object> paramMap, Integer startRow, Integer offset);

	/**
	 * 根据MSGID查询单条消息记录
	 * @param
	 * @return MsgNotifyPo
	 */
	@HiBoMethod
	MsgNotifyPo qryMsgNotifyByMsgId(String msgId);

	/**
	 * 创建公告消息
	 * @param
	 * @return boolean
	 * @throws 
	 */
	@HiBoMethod
	boolean addMsgNotify(MsgNotifyPo msgNotifyPo);

	/**
	 * 查询公告消息条数
	 * @param
	 * @return boolean
	 * @throws 
	 */
	@HiBoMethod
	int qryNotifyCount(Map<String, Object> paramMap);

	/**
	 * 分页查询公告消息
	 * @param
	 * @return List<MsgNotifyPo>
	 * @throws 
	 */
	@HiBoMethod
	List<MsgNotifyPo> qryNotifyPage(Map<String, Object> paramMap, Integer startRow, Integer offset);
	
}
