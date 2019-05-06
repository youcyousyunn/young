package com.ycs.sys.dao;

import java.util.List;
import java.util.Map;

import com.ycs.sys.domain.po.UserInfoPo;

/**
 * 用户信息DAO接口
 * @author youcyousyunn
 * @date 2018年1月28日
 */
public interface IUserInfoDao {

	/**
	 * 添加用户信息
	 * @param
	 * @return int
	 */
	int addUserInfo(UserInfoPo userInfo);

	/**
	 * 根据登录名查询用户信息
	 * @param loginName 登录名
	 * @return UserInfoPo 用户信息
	 */
	UserInfoPo queryByLoginName(String loginNm);

	/**
     * 根据用户编号查询用户
     * @param userNo 用户编号
     * @return 用户信息
     */
	UserInfoPo queryUserInfo(String usrNo);

	/**
     * 根据条件获取数据数量
     * @param parameter 条件
     * @return 数量
     */
	int getCount(Map<String, Object> parameter);

	/**
     * 分页查询用户信息
     * @param parameter 条件
     * @return
     */
	List<UserInfoPo> queryPage(Map<String, Object> parameter);

	/**
     * 更新用户信息
     * @param userInfo 用户信息
     * @return 是否成功(1成功、0失败)
     */
	int updateUserInfo(UserInfoPo userInfo);

	/**
     * 查询手机号是否存在
     * @param mobile 手机号
     * @return 是否占用(0未占用、1已占用)
     */
	int mobileExists(String mobileNo);

	/**
     * 根据用户编号删除用户信息
     * @param usrNos 主键集合
     * @return 是否成功(1成功、0失败)
     */
	int deleteUserInfo(List<Object> usrNos);

	/**
     * 更新用户信息的部门名称
     * @param requestMap
     * @return
     */
	int updateDeptmentByNo(Map<String, Object> requestMap);

	/**
     * 查询所有用户
     * @param requestMap 搜索条件
     * @return 用户集合
     */
	List<UserInfoPo> queryUserInfos(Map<String, Object> requestMap);

	/**
     * 更新用户部门信息
     * @param requestMap 
     * @return 是否成功(-1失败、其余成功)
     */
	int updateUserDeptment(Map<String, Object> requestMap);

	/**
     * 更新当前登录用户信息
     * @param userInfo 用户信息
     * @return 是否成功(1成功、0失败)
     */
	int updateSelfInfo(UserInfoPo userInfo);

	/**
	 * 根据父级部门查询所有归属用户
	 * @param
	 * @return List<UserInfoPo>
	 */
	List<UserInfoPo> qryAllUserByFDept(Map<String, Object> requestMap);

	/**
     * 更新当前用户密码
     * @param userInfo
     * @return int
     */
	int updateSelfPwd(UserInfoPo userInfo);

	/**
	 * 注册
	 * @param userInfo
	 * @return int
	 */
	int register(UserInfoPo userInfo);

	
}
