package com.xdtech.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.common.fomat.TreeBuilder;
import com.xdtech.common.utils.JsonUtil;
import com.xdtech.sys.model.UserGroup;
import com.xdtech.sys.service.UserGroupService;
import com.xdtech.sys.vo.UsergroupItem;
import com.xdtech.web.model.ResultMessage;
import com.xdtech.web.model.TreeItem;

@Controller
@Scope("prototype")
@RequestMapping("/group.do")
public class UserGroupController {
	@Autowired
	private UserGroupService userGroupService;
	
	@RequestMapping(params = "group")
	public ModelAndView group(HttpServletRequest request) {
		return new ModelAndView("sys/group/group");
	}
	
	@RequestMapping(params = "usergroupTree")
	@ResponseBody
	public List<TreeItem> usergroupTree(HttpServletRequest request) {
		List<TreeItem> items = new ArrayList<TreeItem>();
		Iterable<UserGroup> usergroups = userGroupService.loadUserGroups();
		TreeItem item = null;
		TreeItem pItem = null;
		//如果前台有传userId，就要设置用户当前用户组结点为选中状态
		List<Long> userSelectGroupIds = new ArrayList<Long>();
		if (StringUtils.isNotEmpty(request.getParameter("userId"))) {
			userSelectGroupIds = userGroupService.loadGroupIdsByUserId(Long.valueOf(request.getParameter("userId")));
		}
		
		for (UserGroup userGroup : usergroups) {
			item = new TreeItem();
			item.setId(userGroup.getId());
			item.setText(userGroup.getName());
			item.setContent(userGroup.getDescription());
			if (userGroup.getParent()!=null) {
				pItem = new TreeItem(userGroup.getParent().getId());
			}
			item.setParent(pItem);
			if (userSelectGroupIds.contains(userGroup.getId())) {
				//已有用户组设置选中状态
				item.setChecked(true);
			}
			items.add(item);
		}
		return TreeBuilder.newTreeBuilder(TreeItem.class, Long.class).buildToTreeList(items);
	}
	
	@RequestMapping(params = "loadUsergroupInfo")
	@ResponseBody
	public UsergroupItem loadUsergroupInfo(String data) {
		UsergroupItem item = (UsergroupItem) JsonUtil.getDtoFromJsonObjStr(data, UsergroupItem.class);
		return userGroupService.loadUserGroupItem(item);
	}
	
	/**
	 * 
	 * @param data
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "editUsergroup")
	@ResponseBody
	public ResultMessage editUsergroup(UsergroupItem data, HttpServletRequest req) {
		return userGroupService.saveUsergroupItem(data);
	}
	/**
	 * 
	 * @description 删除用户组
	 * @author
	 * @create 2014-8-4下午9:35:19
	 * @modified by
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "deleteUsergroup")
	@ResponseBody
	public ResultMessage deleteUsergroup(long id) {
		
		ResultMessage r = new ResultMessage();
		if (userGroupService.deleteGroupLinkInfo(id)) {
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	
	/**
	 * 加载用户组-角色界面
	 * @param usergroupId
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "toUsergroupRole")
	public ModelAndView toUsergroupRole(Long usergroupId,HttpServletRequest request) {
		request.setAttribute("usergroupId", usergroupId);
		return new ModelAndView("sys/group/group_role");
	}
	
	

}
