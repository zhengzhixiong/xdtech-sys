package com.xdtech.sys.service;

import java.util.List;

import com.xdtech.common.service.IBaseService;
import com.xdtech.sys.model.Role;
import com.xdtech.sys.model.RolePermission;
import com.xdtech.sys.vo.RoleItem;

public interface RoleService extends IBaseService<Role>{
	/**
	 * 加载用户对应的所有关联角色
	 * @param userId
	 * @return
	 */
	List<RoleItem> loadAllUserRoles(Long userId);
	/**
	 * 加载用户对应的所有非关联角色
	 * @param userId
	 * @return
	 */
	List<RoleItem> loadAllNotUserRoles(Long userId);
	
	/**
	 * 
	 * 方法作用：加载用户的配置的角色关联集合
	 * @param userId
	 * @return 
	 * List<RoleItem>
	 * 2013年12月7日-下午11:13:26
	 * @author Zheng Zhi Xiong
	 * @exception 
	 * @since  1.0.0
	 */
	public List<RoleItem> loadUserRoles(Long userId);
	/**
	 * 加载非用户关联角色
	 * @param userId
	 * @return
	 */
	public List<RoleItem> loadNotUserRoles(Long userId);
	/**
	 * 加载用户组关联角色
	 * @param userId
	 * @return
	 */
	public List<RoleItem> loadUsergroupRoles(Long userId);
	/**
	 * 加载非用户组关联角色
	 * @param userId
	 * @return
	 */
	public List<RoleItem> loadNotUsergroupRoles(Long userId);
	
	/**
	 * 
	 * 方法作用：保存用户组关联角色配置
	 * @param usergroupId
	 * @param roleItems
	 * @return 
	 * boolean
	 * 2013年12月8日-下午9:33:14
	 * @author Zheng Zhi Xiong
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean saveUsergroupWithRoles(Long usergroupId,List<RoleItem> roleItems);
	
	public List<RoleItem> loadAllRoles();
	
	/**
	 * 更新角色
	 * @param roleItem
	 * @return
	 */
	public boolean updateRole(RoleItem roleItem);
	
	public RoleItem getRoleItemById(Long id);
	
	public boolean deleteRoles(List<Long> ids);
	
	public void updateRolePermissions(Long roleId,List<RolePermission> rolePermissions);
	
	public boolean saveUserWithRoles(Long userId, List<RoleItem> roleItems);
	/**
	 * 
	 * @author max.zheng
	 * @create 2014-11-30下午5:55:52
	 * @modified by
	 * @param item
	 * @return
	 */
	boolean saveOrUpdateRole(RoleItem item);
	/**
	 * 
	 * @author max.zheng
	 * @create 2014-11-30下午6:05:54
	 * @modified by
	 * @param roleIds
	 */
	boolean deleteRoleInfo(List<Long> roleIds);

}
