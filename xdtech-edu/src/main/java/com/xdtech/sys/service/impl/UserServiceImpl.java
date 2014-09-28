package com.xdtech.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.common.service.BaseService;
import com.xdtech.common.utils.EmptyUtil;
import com.xdtech.common.utils.EncryptUtil;
import com.xdtech.core.dao.BaseDao;
import com.xdtech.core.orm.Page;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.sys.dao.UserDao;
import com.xdtech.sys.model.MenuFunction;
import com.xdtech.sys.model.User;
import com.xdtech.sys.model.UserGroup;
import com.xdtech.sys.searchers.UserCondition;
import com.xdtech.sys.service.MenuFunctionService;
import com.xdtech.sys.service.RoleService;
import com.xdtech.sys.service.UserGroupService;
import com.xdtech.sys.service.UserService;
import com.xdtech.sys.vo.RoleItem;
import com.xdtech.sys.vo.UserItem;
import com.xdtech.web.model.Pagination;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private UserGroupService userGroupService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuFunctionService menuFunctionService;
	

	public User getUserByName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

	
	public List<MenuFunction> loadUserFunctions(Long userId, List<Long> roleIds) {
		if (EmptyUtil.isEmpty(roleIds)) {
			List<RoleItem> roles = roleService.loadAllUserRoles(userId);
			roleIds = new ArrayList<Long>();
			for (RoleItem role : roles) {
				roleIds.add(role.getId());
			}
		}
		return menuFunctionService.loadMenuFunctionsByRoles(roleIds);
	}

	
	public List<RoleItem> loadUserRoles(Long userId) {
		List<RoleItem> roles = roleService.loadAllUserRoles(userId);
		return roles;
	}

	
	public Map<String, Object> loadPageDatas(Pagination pg, Long groupId) {
		Map<String, Object> results = new HashMap<String, Object>();
		if (groupId!=null&&groupId==1) {
			//如果用户组id=1,默认是学堂平台组
			groupId = null;
		}
		Page<User> page = userDao.loadPageByGroup(pg, groupId);
		List<Object> userDetails = BeanUtils.copyListProperties(
				UserItem.class, page.getResult());
		results.put("total", page.getTotalItems());
		results.put("rows", userDetails);
		return results;
	}

	
	public boolean deleteUserLinkInfo(long id) {
		// 删除用户跟用户组关联关系
		userDao.deleteUserLinkGroup(id);
		// 删除用户跟角色关联关系
		userDao.deleteUserLinkRole(id);
		userDao.delete(id);
		return true;
	}
	
	public boolean saveOrUpdateUser(UserItem user) {
		User u = null;
		List<Long> selectGroupIds = new ArrayList<Long>();
		if (StringUtils.isNotEmpty(user.getGroupIds())) {
			String[] groupIds = user.getGroupIds().split(",");
			for (String gid : groupIds) {
				selectGroupIds.add(Long.valueOf(gid));
			}
		}
		
		if (user.getId()==null) {
			u = new User();
			//初始化密码123456
			String entryPwd = EncryptUtil.encodePassword("123456");
			u.setPassword(entryPwd);
		}else {
			u = userDao.get(user.getId());
		}
		//复制前台修改的属性
		BeanUtils.copyProperties(u, user);
		List<UserGroup> userGroups = userGroupService.loadUserGroupsByIds(selectGroupIds);
		u.setUserGroups(userGroups);
		baseDao.save(u);
		return true;
	}

	
	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<User> loadByCondition(Map<String, String> conditions) {
//		baseDao.save(ne)
		return baseDao.findByNamedQuery("user.getByCondition", conditions);
	}


	public UserItem loadUserItem(long id) {
		User user = userDao.get(id);
		UserItem userItem = new UserItem();
		BeanUtils.copyProperties(userItem, user);
		return userItem;
	}


	public Map<String, Object> loadDataByCondition(Pagination pg,
			UserCondition userCondition) {
		Map<String, Object> results = new HashMap<String, Object>();
		Page<UserItem> page = baseDao.findPageByNamedQuery(pg, "findUserByCondition",userCondition);
		results.put("total", page.getTotalItems());
		results.put("rows", page.getResult());
		return results;
	}


	public boolean updateUserPwd(Long userId, String newPwd) {
		String entryPwd = EncryptUtil.encodePassword(newPwd);
		int rs = userDao.batchExecute("update User u set u.password=? where u.id=?)",entryPwd, userId);
		return rs>0;
	}


	public void save(User entity) {
		userDao.save(entity);
	}


	public void delete(User entity) {
		userDao.delete(entity);
	}


	public void delete(Long id) {
		userDao.delete(id);
	}


	public User get(Long id) {
		return userDao.get(id);
	}


	public List<User> getAll() {
		return userDao.getAll();
	}
}
