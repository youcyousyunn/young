package com.ycs.sys.dao;

import java.util.List;
import java.util.Map;

import com.ycs.sys.domain.po.PermissionPo;

/**
 * 菜单Dao接口
 * @author youcyousyunn
 * @date 2018年2月12日
 */
public interface IPermissionDao {
	
	/**
     * 根据角色获取菜单
     * @param roleNos 角色编号
     * @return 菜单集合
     */
    List<PermissionPo> queryMenu(List<String> roleNoList);

    /**
     * 获取权限
     * @param userNo 用户编号
     * @param type 类型
     * @param status 状态
     * @return 权限集合
     */
	List<PermissionPo> queryPermissions(Map<String, Object> requestMap);

	/**
     * 修改权限
     * @param permissionPo 请求参数
     * @return 修改结果(1成功0失败)
     */
	int updatePermission(PermissionPo permissionPo);

	/**
	 * 添加权限
	 * @param permissionPo 请求参数
	 * @return int 返回主键
	 */
	int addPermission(PermissionPo permissionPo);

	/**
     * 删除权限(根据权限编号) 
     * @param requestList
     * @return 是否成功(1成功、0失败)
     */
	int deletePermission(List<Object> requestList);

	/**
     * 根据角色编号查询权限编号
     * @param requestList 角色编号集合
     * @return 权限编号集合
     */
	List<Integer> queryPermissionsByRoles(List<Object> requestList);

	/**
     * 根据角色编号删除权限编号
     * @param requestList 角色编号集合
     * @return 是否成功(1成功、0失败)
     */
	int deleteRolePermission(List<Object> requestList);

	/**
     * 添加角色权限
     * @param requestList
     * @return 是否成功(1成功、0失败)
     */
	int addRolePermission(List<Map<String, Object>> requestList);
    
	
	
}
