package com.ycs.sys.dao;

import java.util.List;
import java.util.Map;

import com.ycs.sys.domain.po.RolePo;

/**
 * 角色DAO接口
 * @author youcyousyunn
 * @date 2018年2月11日
 */
public interface IRoleDao {
	/**
     * 查询总数
     * @param requestMap
     * @return 数量
     */
    int getCount(Map<String, Object> requestMap);

    /**
     * 分页查询角色
     * @param requestMap
     * @return 角色集合
     */
    List<RolePo> pageQuery(Map<String, Object> requestMap);

    /**
     * 添加用户角色
     * @param requestList
     * @return 成功数量
     */
    int addUserRoles(List<Map<String, Object>> requestList);

    /**
     * 根据用户编号查询角色编号
     * @param requestMap
     * @return 角色编号集合
     */
    Map<String, Object> queryUserRoles(Map<String, Object> requestMap);

    /**
     * 添加角色
     * @param role 角色
     * @return 是否成功(1成功、0失败)
     */
    int addRole(RolePo role);

    /**
     * 查询角色是否存在
     * @param requestMap
     * @return 数量
     */
    int queryRoleExists(Map<String, Object> requestMap);

    /**
     * 根据角色查询是否有用户关联
     * @param requestList
     * @return 是否成功(1成功、0失败)
     */
    List<String> queryUserRole(List<Object> requestList);

    /**
     * 批量删除角色
     * @param requestList
     * @return 是否成功(1成功、0失败)
     */
    int deleteRoles(List<Object> requestList);

    /**
     * 更新角色信息
     * @param role 角色
     * @return 是否成功(1成功、0失败)
     */
    int updateRole(RolePo role);

    /**
     * 根据用户编号删除用户角色
     * @param userNo 用户编号
     * @return 是否成功(1成功、0失败)
     */
    int deleteUserRole(String usrNo);

    /**
     * 根据角色编号查询角色
     * @param roleNo 角色编号
     * @return 角色
     */
    RolePo queryRole(String roleNo);
    
    /**
     * 根据用户编号查询角色
     * @param userNo 用户编号
     * @return 角色集合
     */
    List<RolePo> queryRolesByUsrNo(Map<String, Object> requestMap);
    
    /**
     * 查询所有角色
     * @return 角色集合
     */
    List<RolePo> queryRoles(Map<String, Object> requestMap);
	
	
}
