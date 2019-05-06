package com.ycs.msg.dao;

import java.util.List;
import java.util.Map;

import com.ycs.msg.domain.po.MsgNotifyPo;

/**
 * 消息通知相关Dao接口
 * @author youcyousyunn
 * @date 2018年6月21日
 */
public interface MsgNotifyDao {

	/**
	 * 添加消息通知
	 * @param
	 * @return int
	 */
	int addMsgNotify(MsgNotifyPo msgNotifyPo);

	/**
	 * 读取消息列表条数
	 * @param
	 * @return int
	 */
	int qryMsgNotifyCount(Map<String, Object> paramMap);

	/**
	 * 读取消息列表
	 * @param
	 * @return List<MsgNotifyPo>
	 */
	List<MsgNotifyPo> qryMsgNotifyPage(Map<String, Object> paramMap);

	/**
	 * 根据MSGID查询单条消息记录
	 * @param
	 * @return MsgNotifyPo
	 */
	MsgNotifyPo qryMsgNotifyByMsgId(String msgId);

	/**
	 * 查询公告消息条数
	 * @param
	 * @return int
	 * @throws 
	 */
	int qryNotifyCount(Map<String, Object> paramMap);

	/**
	 * 分页查询公告消息
	 * @param
	 * @return List<MsgNotifyPo>
	 * @throws 
	 */
	List<MsgNotifyPo> qryNotifyPage(Map<String, Object> paramMap);
	
}
