package com.xdtech.sys.service;

import java.util.List;
import java.util.Map;

import com.xdtech.common.service.impl.IBaseService;
import com.xdtech.sys.model.MenuFunction;
import com.xdtech.sys.model.User;
import com.xdtech.sys.searchers.UserCondition;
import com.xdtech.sys.vo.RoleItem;
import com.xdtech.sys.vo.UserItem;
import com.xdtech.web.model.Pagination;

public interface UserService extends IBaseService<User> {
	
	public User getUserByName(String loginName);
	/**
	 * 加载用户权限信息
	 * @param userId
	 * @return
	 */
	public List<MenuFunction> loadUserFunctions(Long userId,List<Long> roleIds);
	/**
	 * 加载用户对应的角色
	 * @param userId
	 * @return
	 */
	public List<RoleItem> loadUserRoles(Long userId);
	/**
	 * 加载分页数据
	 * @param pg
	 * @param groupId
	 * @return
	 */
	public Map<String, Object> loadPageDatas(Pagination pg,Long groupId);
	
	public boolean deleteUserLinkInfo(long id);
	
	public boolean saveOrUpdateUser(UserItem user);
	
	public UserItem loadUserItem(long id);
	
	public List<User> loadByCondition(Map<String, String> conditions);
	
	/**
	 * 
	 * @param pg
	 * @param userCondition
	 * @return
	 */
	public Map<String, Object> loadDataByCondition(Pagination pg,UserCondition userCondition);
	/**
	 * 
	 * @description 更新用户密码
	 * @author
	 * @create 2014-8-6下午10:12:47
	 * @modified by
	 * @param userId
	 * @param newPwd
	 * @return
	 */
	public boolean updateUserPwd(Long userId,String newPwd);
}
