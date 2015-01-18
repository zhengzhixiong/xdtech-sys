package com.xdtech.sys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.common.utils.EncryptUtil;
import com.xdtech.sys.aspect.SystemControllerLog;
import com.xdtech.sys.model.User;
import com.xdtech.sys.searchers.UserCondition;
import com.xdtech.sys.service.UserService;
import com.xdtech.sys.util.SessionContextUtil;
import com.xdtech.sys.vo.UserItem;
import com.xdtech.web.model.Pagination;
import com.xdtech.web.model.ResultMessage;

@Controller
@Scope("prototype")
@RequestMapping("/user.do")
public class UserController{
	@Autowired
	private UserService userService;

	@RequestMapping(params = "user")
	public ModelAndView skipUser(HttpServletRequest request) {
		return new ModelAndView("sys/user/user_ftl");
	}
	
	@RequestMapping(params = "editUser")
	@SystemControllerLog(description = "编辑用户")  
	public ModelAndView editUser(HttpServletRequest request,Long userId) {
//		request.setAttribute("usergroupId", usergroupId);
		if (userId!=null) {
			request.setAttribute("userItem", userService.loadUserItem(userId));
		}
		return new ModelAndView("sys/user/editUser_ftl");
	}
	@RequestMapping(params = "saveUser")
	@ResponseBody
	@SystemControllerLog(description = "更新用户")
	public ResultMessage saveUser(UserItem item) {
		ResultMessage r = new ResultMessage();
		if (userService.saveOrUpdateUser(item)) {
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	
	@RequestMapping(params = "addUser")
	public ModelAndView addUser(HttpServletRequest request,Long userId) {
//		request.setAttribute("usergroupId", usergroupId);
		if (userId!=null) {
			request.setAttribute("userItem", userService.loadUserItem(userId));
		}
		return new ModelAndView("sys/user/addUser");
	}

	@RequestMapping(params = "loadGridDatas")
	@ResponseBody
	public Map<String, Object> loadGridDatas(Long usergroupId, Pagination pg,
			HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String a = request.getParameter("a");
		String b = request.getParameter("b");
		Map<String, Object> result = userService.loadPageDatas(pg, null);
		return result;
	}

	@RequestMapping(params = "loadByUsergroupId", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loadByUsergroupId(Long usergroupId,Pagination pg) {
		Map<String, Object> result = userService.loadPageDatas(pg, usergroupId);
		return result;
	}
	@RequestMapping(params = "loadByCondition", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loadByCondition(UserCondition userCondition,Pagination pg) {
		Map<String, Object> result = userService.loadDataByCondition(pg, userCondition);
		return result;
	}
	/**
	 * 
	 * 方法作用：加载用户-角色关联情况tab页
	 * @param userId
	 * @param request
	 * @return 
	 * ModelAndView
	 * 2013年12月7日-下午10:41:28
	 * @author Zheng Zhi Xiong
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(params = "userRole")
	public ModelAndView userRole(Long userId,HttpServletRequest request) {
		request.setAttribute("userId", userId);
		return new ModelAndView("sys/user/user_role");
	}
	/**
	 * 
	 * 方法作用：加载用户拥有的角色列表
	 * @param userId
	 * @param request
	 * @return 
	 * ModelAndView
	 * 2013年12月7日-下午10:42:55
	 * @author Zheng Zhi Xiong
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(params = "userRoleList")
	public ModelAndView userRoleList(Long userId,HttpServletRequest request) {
		request.setAttribute("url", "role.do?loadAllSelectRoles&userId="+userId);
		return new ModelAndView("sys/user/user_role_list");
	}
	/**
	 * 
	 * 方法作用：加载用户对应的用户组配置的角色列表
	 * @param userId
	 * @param request
	 * @return 
	 * ModelAndView
	 * 2013年12月7日-下午11:35:49
	 * @author Zheng Zhi Xiong
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(params = "usergroupRoleList")
	public ModelAndView usergroupRoleList(Long userId,HttpServletRequest request) {
		request.setAttribute("url", "role.do?loadGroupSelectRoles&userId="+userId);
		return new ModelAndView("sys/user/user_usergroup_role_list");
	}
	
	@RequestMapping(params = "updateUser")
	@ResponseBody
	public ResultMessage updateUser(UserItem userItem) {
		ResultMessage r = new ResultMessage();
		if (userService.saveOrUpdateUser(userItem)) {
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	
	@RequestMapping(params = "deleteUser")
	@ResponseBody
	public ResultMessage deleteUser(long id) {
		ResultMessage r = new ResultMessage();
		if (userService.deleteUserLinkInfo(id)) {
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	@RequestMapping(params = "deleteUserItems")
	@ResponseBody
	public ResultMessage deleteUserItems(String ids) {
		ResultMessage r = new ResultMessage();
		if (StringUtils.isNotEmpty(ids)) {
			String[] tempIds = ids.split(",");
			List<Long> userIds = new ArrayList<Long>();
			for (String id : tempIds) {
				userIds.add(Long.valueOf(id));
			}
			userService.deleteUserInfo(userIds);
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	/**
	 * 
	 * @description 跳转用户角色相关信息
	 * @author
	 * @create 2014-8-3下午8:28:21
	 * @modified by
	 * @param request
	 * @param userId
	 * @return
	 */
	@RequestMapping(params = "userLinkRoles")
	public ModelAndView userLinkRoles(HttpServletRequest request,Long userId) {
		if (userId!=null) {
			request.setAttribute("userId", userId);
			request.setAttribute("userItem", userService.loadUserItem(userId));
		}
		return new ModelAndView("sys/user/userLinkRole_ftl");
	}
	
	

	@RequestMapping(params = "toUpdatePwd")
	public ModelAndView toUpdatePwd() {
		return new ModelAndView("sys/user/updatePwd");
	}
	@RequestMapping(params = "modifiedPwd")
	@ResponseBody
	public ResultMessage modifiedPwd(String oldPwd,String newPwd) {
		ResultMessage r = new ResultMessage();
		String entryPwd = EncryptUtil.encodePassword(oldPwd);
		User currentUser = SessionContextUtil.getCurrentUser();
		if (!currentUser.getPassword().equals(entryPwd)) {
			r.setMsg("旧密码验证错误.");
		}else {
			userService.updateUserPwd(currentUser.getId(), newPwd);
		}
		return r;
	}

}
