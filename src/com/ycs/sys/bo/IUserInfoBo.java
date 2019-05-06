package com.ycs.sys.bo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ycs.base.annotation.HiBoMethod;
import com.ycs.sys.domain.dto.RegisterRequestDto;
import com.ycs.sys.domain.po.UserInfoPo;

/**
 * 用户信息BO接口
 * @author youcyousyunn
 * @date 2018年1月28日
 */
public interface IUserInfoBo {

	/**
	 * 添加用户信息
	 * @param userInfo 添加请求
	 * @return 返回结果
	 */
	@HiBoMethod
    int addUserInfo(UserInfoPo userInfo);

	/**
	 * 根据登录名查询用户信息
	 * @param loginName 请求
	 * @return UserInfoPo 返回结果
	 */
	@HiBoMethod
	UserInfoPo queryByLoginName(String loginNm);

	/**
     * 根据用户编号查询用户
     * @param userNo 用户编号
     * @return 用户信息
     */
    @HiBoMethod
	UserInfoPo queryUserInfo(String usrNo);

    /**
     * 根据条件获取数据数量
     * @param parameter 条件
     * @param sort 排序列
     * @param sortName 排序方式(DESC、ASC)默认DESC
     * @return 数量
     */
    @HiBoMethod
	int getCount(Map<String, Object> parameter, String sort, String sortName);

    /**
     * 分页查询用户信息
     * @param parameter 条件
     * @param sort 排序列
     * @param sortName 排序方式(DESC、ASC)默认DESC
     * @param row 起始行数
     * @param offset 页大小(偏移量)
     * @return 用户信息集合
     */
    @HiBoMethod
	List<UserInfoPo> queryPage(Map<String, Object> parameter, String sort, String sortName, Integer startRow,
			Integer offset);

    /**
     * 更新用户信息
     * @param userInfo 用户信息
     * @return 是否成功(1成功、0失败)
     */
    @HiBoMethod
	int updateUserInfo(UserInfoPo userInfo);

    /**
     * 查询手机号是否存在
     * @param mobile 手机号
     * @return 是否占用(0未占用、1已占用)
     */
    @HiBoMethod
	int mobileExists(String mobileNo);

    /**
     * 根据用户编号删除用户信息
     * @param usrNos 主键集合
     * @return 是否成功(1成功、0失败)
     */
    @HiBoMethod
	int deleteUserInfo(Set<String> usrNos);

    /**
     * 更新用户信息的部门名称
     * @param deptmentNo　部门号
     * @param deptmentName　部门名称
     * @return
     */
    @HiBoMethod
	boolean updateDeptmentByNo(Integer deptmentNo, String deptmentName);

    /**
     * 查询所有用户
     * @param search 搜索条件
     * @return 用户集合
     */
    @HiBoMethod
	List<UserInfoPo> queryUserInfos(String usrNo, String usrNm);

    /**
     * 更新用户部门信息
     * @param deptmentNo 部门编号
     * @param deptmentName 部门名称
     * @return 是否成功(-1失败、其余成功)
     */
    @HiBoMethod
	boolean updateUserDeptment(Integer deparNo, String deparNm, String mngerUsrNo);

    /**
     * 更新当前登录用户信息
     * @param userInfo 用户信息
     * @return 是否成功(1成功、0失败)
     */
    @HiBoMethod
	int updateSelfInfo(UserInfoPo userInfo);
    
    /**
     * 根据父级部门查询所有归属用户
     * @param
     * @return List<UserInfoPo>
     */
    @HiBoMethod
	List<UserInfoPo> qryAllUserByFDept(String deparNo);

    /**
     * 更新当前用户密码
     * @param userInfo
     * @return boolean
     */
    @HiBoMethod
    boolean updateSelfPwd(UserInfoPo userInfo);

    /**
     * 注册
     * @param
     * @return boolean
     */
    @HiBoMethod
	boolean register(UserInfoPo userInfo);
    
}
