package com.xdtech.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.common.service.impl.BaseService;
import com.xdtech.core.dao.BaseDao;
import com.xdtech.core.model.BaseModel;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.sys.dao.RoleDao;
import com.xdtech.sys.dao.RolePermissionDao;
import com.xdtech.sys.dao.UserDao;
import com.xdtech.sys.dao.UserGroupDao;
import com.xdtech.sys.dao.UserRoleDao;
import com.xdtech.sys.model.Role;
import com.xdtech.sys.model.RolePermission;
import com.xdtech.sys.model.UserGroup;
import com.xdtech.sys.model.UserRole;
import com.xdtech.sys.service.RoleService;
import com.xdtech.sys.vo.RoleItem;
import com.xdtech.web.model.Pagination;

@Service
@Transactional
public class RoleServiceImpl  implements RoleService {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserGroupDao userGroupDao;
	@Autowired
	private BaseDao<BaseModel> baseDao;
	@Autowired
	private RolePermissionDao rolePermissionDao;
	@Autowired
	private UserRoleDao userRoleDao;

	
	public List<RoleItem> loadAllUserRoles(Long userId) {
		// 加载用户配置的所有角色集合，包含用户和用户组关联得到
		List<Role> roles = roleDao.getUserRoles(userId);
		List<RoleItem> roleItems = roleToItems(roles);
		return roleItems;
	}

	
	public List<RoleItem> loadAllNotUserRoles(Long userId) {
		// 加载非用户关联角色列表
		List<Role> roles = roleDao.getNotUserRoles(userId);
		List<RoleItem> roleItems = roleToItems(roles);
		return roleItems;
	}

	
	public List<RoleItem> loadUserRoles(Long userId) {
		// 加载用户配置的所有角色集合，只针对用户
		List<Role> roles = roleDao.findUserRole(userId);
		List<RoleItem> roleItems = roleToItems(roles);
		return roleItems;
	}

	
	public List<RoleItem> loadNotUserRoles(Long userId) {
		// 加载非用户配置的角色集合，只针对用户
//		List<Role> roles = roleDao.findNotUserRole(userId);
		//加载非用户的角色集合，排除了用户组和用户一起关联的
		List<Role> roles = roleDao.getNotUserRoles(userId);
		List<RoleItem> roleItems = roleToItems(roles);
		return roleItems;
	}

	
	public List<RoleItem> loadUsergroupRoles(Long userId) {
		// 加载用户组配置的角色集合，只针对用户组
		List<Role> roles = roleDao.findUsergroupRole(userId);
		List<RoleItem> roleItems = roleToItems(roles);
		return roleItems;
	}

	
	public List<RoleItem> loadNotUsergroupRoles(Long userId) {
		// 加载非用户组配置的角色集合，只针对用户组
		List<Role> roles = roleDao.findNotUserGroupRole(userId);
		List<RoleItem> roleItems = roleToItems(roles);
		return roleItems;
	}

	private List<RoleItem> roleToItems(List<Role> roles) {
		List<RoleItem> roleItems = new ArrayList<RoleItem>();
		for (Role role : roles) {
			roleItems.add(roleConvetor(role));
		}
		return roleItems;
	}

	private RoleItem roleConvetor(Role role) {
		RoleItem roleItem = new RoleItem();
		BeanUtils.copyProperties(roleItem, role);
		return roleItem;
	}

	
	public boolean saveUsergroupWithRoles(Long userGroupId,
			List<RoleItem> roleItems) {
		// 根据用户组id号，更新用户组-角色表
		// 先清空用户组关联角色
		userGroupDao.deleteUsergroupLinkRole(userGroupId);
		// 再保存
		UserGroup userGroup = userGroupDao.get(userGroupId);
//		List<UsergroupRole> usergroupRoles = new ArrayList<UsergroupRole>();
//		UsergroupRole usergroupRole = null;
		for (RoleItem roleItem : roleItems) {
			userGroup.getRoles().add(new Role(roleItem.getId()));
		}
		userGroupDao.save(userGroup);
		return true;
	}

	
	public List<RoleItem> loadAllRoles() {
		// 加载所有的角色列表
		List<Role> roles = roleDao.getAll();
		List<RoleItem> roleItems = new ArrayList<RoleItem>();
		for (Role role : roles) {
			roleItems.add(roleConvetor(role));
		}
		return roleItems;
	}

	
	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean updateRole(RoleItem roleItem) {
		Role role = null;
		if (roleItem.getId()==null) {
			role = new Role();
			BeanUtils.copyProperties(role, roleItem);
		}else {
			role = roleDao.get(roleItem.getId());
			BeanUtils.copyProperties(role, roleItem);
			
		}
		roleDao.save(role);
		return true;
	}


	public RoleItem getRoleItemById(Long id) {
		Role role = roleDao.get(id);
		RoleItem roleItem = new RoleItem();
		if (role!=null) {
			BeanUtils.copyProperties(roleItem, role);
		}
		return roleItem;
	}


	public boolean deleteRoles(List<Long> ids) {
		for (Long id : ids) {
			deleteRoleLinkAllInfo(id);
		}
		return true;
	}


	public void updateRolePermissions(Long roleId,List<RolePermission> rolePermissions) {
		//先删除角色对应的所有
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("roleId", roleId.toString());
		//先全部删除
		baseDao.excuteByNamedQuery("delOperationMenuByRoleId", parameters);
		//在保存
		rolePermissionDao.saveAll(rolePermissions);
	}


	public boolean saveUserWithRoles(Long userId, List<RoleItem> roleItems) {
		//根据用户id号，更新用户-角色表
		//先清空用户关联角色
		userDao.deleteUserLinkRole(userId);
		//再保存
		List<UserRole> userRoles = new ArrayList<UserRole>();
		UserRole userRole = null;
		for (RoleItem roleItem : roleItems) {
			userRole = new UserRole();
			userRole.setRoleId(roleItem.getId());
			userRole.setUserId(userId);
			userRoles.add(userRole);
		}
		userRoleDao.saveAll(userRoles);
		return true;
	}


	public void save(Role entity) {
		roleDao.save(entity);
	}


	public void delete(Role entity) {
		roleDao.delete(entity);
	}


	public void delete(Long id) {
		roleDao.delete(id);
	}


	public Role get(Long id) {
		return roleDao.get(id);
	}


	public List<Role> getAll() {
		return roleDao.getAll();
	}


	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-30下午5:56:08
	 * @modified by
	 * @param item
	 * @return
	 */
	public boolean saveOrUpdateRole(RoleItem item) {
		Role role = null;
		if (item.getId()==null) {
			role = new Role();
		}else {
			role = roleDao.get(item.getId());
		}
		//复制前台修改的属性
		BeanUtils.copyProperties(role, item);
		roleDao.save(role);
		return true;
	}


	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-30下午6:06:09
	 * @modified by
	 * @param roleIds
	 */
	public boolean deleteRoleInfo(List<Long> roleIds) {
		for (Long id : roleIds) {
			deleteRoleLinkAllInfo(id);
		}
		return true;
	}
	
	private void deleteRoleLinkAllInfo(Long id) {
		//删除跟菜单关联信息
		roleDao.excuteUpdateBySql("delete from sys_role_menu where role_id="+id);
		//删除角色关联权限组
		roleDao.excuteUpdateBySql("delete from sys_usergroup_role where role_id="+id);
		delete(id);
	}

}
