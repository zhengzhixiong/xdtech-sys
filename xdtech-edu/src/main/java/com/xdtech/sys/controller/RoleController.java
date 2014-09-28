package com.xdtech.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.common.fomat.TreeBuilder;
import com.xdtech.common.utils.EmptyUtil;
import com.xdtech.common.utils.JsonUtil;
import com.xdtech.sys.model.MenuFunction;
import com.xdtech.sys.model.RolePermission;
import com.xdtech.sys.service.MenuFunctionService;
import com.xdtech.sys.service.RoleService;
import com.xdtech.sys.vo.RoleItem;
import com.xdtech.web.model.ResultMessage;
import com.xdtech.web.model.TreeItem;
import com.xdtech.web.vo.IdItem;

@Controller
@Scope("prototype")
@RequestMapping("/role.do")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuFunctionService menuFunctionService;
	@RequestMapping(params = "role")
	public ModelAndView role(HttpServletRequest request) {
		request.setAttribute("tableId", "role_table");
		return new ModelAndView("sys/role/role");
	}
	
	@RequestMapping(params = "addRole")
	public ModelAndView addRole(HttpServletRequest request,Long id) {
		RoleItem role = null;
		if (id!=null) {
			//编辑
			role = getRoleById(id);
		}else {
			role = new RoleItem();
		}
		request.setAttribute("role", role);
		return new ModelAndView("sys/role/addRole");
	}
	
	@RequestMapping(params = "roleAuth")
	public ModelAndView roleAuth(HttpServletRequest request,Long roleId) {
		request.setAttribute("roleId", roleId);
		return new ModelAndView("sys/role/roleAuth");
	}
	
	@RequestMapping(params = "updateRole")
	@ResponseBody
	public ResultMessage updateRole(RoleItem roleItem) {
		ResultMessage rm = new ResultMessage(); 
		if (!roleService.updateRole(roleItem)) {
			rm = ResultMessage.getFailMessage();
		}
		return rm;
	}
	
	@RequestMapping(params = "getRoleById")
	@ResponseBody
	public RoleItem getRoleById(Long id) {
		return roleService.getRoleItemById(id);
	}
	
	@RequestMapping(params = "deleteRole")
	@ResponseBody
	public ResultMessage deleteRole(String ids) {
		ResultMessage rm = new ResultMessage(); 
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArray = ids.split(",");
			
			List<Long> roleIds = new ArrayList<Long>();
			for (String id : idArray) {
				roleIds.add(Long.valueOf(id));
			}
			if (!roleService.deleteRoles(roleIds)) {
				rm = ResultMessage.getFailMessage();
			}
		}else {
			rm = ResultMessage.getFailMessage();
		}
		
		return rm;
	}
	
	
	/**
	 * 
	 * 方法作用：加载用户所有的角色列表，包含通过用户和用户组关联得到
	 * @param userId
	 * @return 
	 * List<RoleItem>
	 * 2013年12月7日-下午11:12:29
	 * @author Zheng Zhi Xiong
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(params = "loadAllSelectRoles")
	@ResponseBody
	public List<RoleItem> loadAllSelectRoles(Long userId) {
		List<RoleItem> roles = roleService.loadAllUserRoles(userId);
		return roles;
	}
	/**
	 * 加载非用户所有的角色
	 * @param userId
	 * @return
	 */
	@RequestMapping(params = "loadAllUnSelectRoles")
	@ResponseBody
	public List<RoleItem> loadAllUnSelectRoles(Long userId) {
		List<RoleItem> roles = roleService.loadAllNotUserRoles(userId);
		return roles;
	}
	/**
	 * 
	 * 方法作用：加载用户配置的角色列表，只通过用户关联得到
	 * @param userId
	 * @return 
	 * List<RoleItem>
	 * 2013年12月7日-下午11:11:35
	 * @author Zheng Zhi Xiong
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(params = "loadUserSelectRoles")
	@ResponseBody
	public List<RoleItem> loadUserSelectRoles(Long userId) {
		List<RoleItem> roles = roleService.loadUserRoles(userId);
		return roles;
	}
	/**
	 * 加载非用户关联角色
	 * @param userId
	 * @return
	 */
	@RequestMapping(params = "loadUserUnSelectRoles")
	@ResponseBody
	public List<RoleItem> loadUserUnSelectRoles(Long userId) {
		List<RoleItem> roles = roleService.loadNotUserRoles(userId);
		return roles;
	}
	/**
	 * 加载用户组关联角色
	 * @param userId
	 * @return
	 */
	@RequestMapping(params = "loadGroupSelectRoles")
	@ResponseBody
	public List<RoleItem> loadGroupSelectRoles(Long usergroupId) {
		List<RoleItem> roles = roleService.loadUsergroupRoles(usergroupId);
		return roles;
	}
	/**
	 * 加载非用户组关联角色
	 * @param userId
	 * @return
	 */
	@RequestMapping(params = "loadGroupUnSelectRoles")
	@ResponseBody
	public List<RoleItem> loadGroupUnSelectRoles(Long usergroupId) {
		List<RoleItem> roles = roleService.loadNotUsergroupRoles(usergroupId);
		return roles;
	}
	
	/**
	 * 
	 * 方法作用：保存用户组关联角色配置
	 * @param usergroupId
	 * @param data
	 * @return 
	 * ResultMessage
	 * 2013年12月8日-下午9:32:02
	 * @author Zheng Zhi Xiong
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(params = "saveUsergroupSelectRoles")
	@ResponseBody
	public ResultMessage saveUsergroupSelectRoles(Long usergroupId,String data) {
		ResultMessage rm = new ResultMessage(); 
		List<RoleItem> roles = JsonUtil.getDTOList(data, RoleItem.class);
		roleService.saveUsergroupWithRoles(usergroupId, roles);
		return rm;
	}
	@RequestMapping(params = "loadAllRoles")
	@ResponseBody
	public List<RoleItem> loadAllRoles() {
		List<RoleItem> roles  = roleService.loadAllRoles();
		return roles;
	}
	
	@RequestMapping(params = "roleOperation")
	public ModelAndView roleOperation(Long roleId,HttpServletRequest request) {
		System.out.println(roleId);
		request.setAttribute("roleTreeId", "operationTree"+roleId);
		request.setAttribute("roleId", roleId);
		request.setAttribute("grid", "buttonOperationsList"+roleId);
		request.setAttribute("toolbar", "buttonOperationsList_toolbar"+roleId);
		request.setAttribute("roleOperationTree", "roleOperationTree"+roleId);
		return new ModelAndView("sys/role/role_operation");
	}
	
	/**
	 * 
	 * 根据用户的所属角色ID号，查询对应角色菜单分配信息
	 * @param roleId
	 * @param request
	 * @return 
	 * List<TreeItem>
	 * 2013年12月7日-下午2:24:59
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(params = "menuTree")
	@ResponseBody
	public List<TreeItem> menuTree(Long roleId,HttpServletRequest request) {
		List<TreeItem> items = new ArrayList<TreeItem>();
		//系统功能菜单项
		List<MenuFunction> allMenus = menuFunctionService.loadAllMenus(true);
		//角色拥有的菜单项
		List<MenuFunction> menuFunctions = menuFunctionService.loadByRoleId(roleId);
		TreeItem item = null;
		TreeItem pItem = null;
		for (MenuFunction menu : allMenus) {
			if (menu.getId()!=-1L) {
				item = new TreeItem();
				item.setId(menu.getId());
				item.setText(menu.getNameCN());
				item.setContent(menu.getDescription());
				pItem = null;
				if (menu.getParent()!=null) {
					pItem = new TreeItem(menu.getParent().getId());
				}
				item.setAttributes(pItem);
				item.setParent(pItem);
				items.add(item);
			}
		}
		List<TreeItem> roleItems = new ArrayList<TreeItem>();
		for (MenuFunction menu : menuFunctions) {
			if (menu.getId()!=-1L) {
				item = new TreeItem();
				item.setId(menu.getId());
				item.setText(menu.getNameCN());
				item.setContent(menu.getDescription());
				pItem = null;
				if (menu.getParent()!=null) {
					pItem = new TreeItem(menu.getParent().getId());
				}
				item.setParent(pItem);
				roleItems.add(item);
			}
		}
		//权限树
		List<TreeItem> allFormatTrees =  TreeBuilder.newTreeBuilder(TreeItem.class, Long.class).buildToTreeList(items);
		List<TreeItem> roleFormatTrees =  TreeBuilder.newTreeBuilder(TreeItem.class, Long.class).buildToTreeList(roleItems);
		//所有maptree
		Map<String, List<TreeItem>> allMapTree = new HashMap<String, List<TreeItem>>();
		//存放角色map节点 
		Map<String, List<TreeItem>> roleMapTree = new HashMap<String, List<TreeItem>>();
		createMapTree(allFormatTrees, allMapTree);
		createMapTree(roleFormatTrees, roleMapTree);
		
		for(String treeId:allMapTree.keySet()) {
			List<TreeItem> values = allMapTree.get(treeId);
			List<TreeItem> roleValues = roleMapTree.get(treeId);
			boolean flag = true;
			if (null==roleValues) {
				flag = false;
			}else {
				if (values.size()==roleValues.size()) {
					flag = true;
				}else {
					flag = false;
				}
			}
			//设置树节点是否选中，值传影响到allFormatTrees
			for(TreeItem i:items) {
				if (i.getId().toString().equals(treeId)) {
					i.setChecked(flag);
					break;
				}
			}
		}
		
		return  allFormatTrees;
		
	}
	
	private void createMapTree(List<TreeItem> childTrees,Map<String, List<TreeItem>> mapTree) {
		if (EmptyUtil.isNotEmpty(childTrees)) {
			for (TreeItem treeItem2 : childTrees) {
				mapTree.put(treeItem2.getId().toString(), treeItem2.getChildren());
				createMapTree(treeItem2.getChildren(), mapTree);
			}
		}
	}
	/**
	 * 
	 * 方法作用：更新角色权限分配信息
	 * @param roleId
	 * @param data
	 * @param request
	 * @return 
	 * ResultMessage
	 * 2013年12月7日-下午9:22:05
	 * @author Zheng Zhi Xiong
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(params = "updateRoleOperations")
	@ResponseBody
	public ResultMessage updateRoleOperations(Long roleId,String data,HttpServletRequest request) {
		ResultMessage rm = new ResultMessage();
		List<IdItem> selectMenus = JsonUtil.getDTOList(data, IdItem.class);
		List<RolePermission> nowPermissions = new ArrayList<RolePermission>();
		RolePermission rp = null;
		for (IdItem menuId : selectMenus) {
			rp = new RolePermission();
			rp.setRoleId(roleId);
			rp.setMenuId(menuId.getId());
			nowPermissions.add(rp);
			if (menuId.getPid()!=-1) {
				//将含有上级权限菜单的父菜单一起添加
				rp = new RolePermission();
				rp.setRoleId(roleId);
				rp.setMenuId(menuId.getPid());
				if (!nowPermissions.contains(rp)) {
					nowPermissions.add(rp);
				}
			}
		}
		roleService.updateRolePermissions(roleId,nowPermissions);
		return rm;
		
	}
	/**
	 * 
	 * 方法作用：保存用户关联角色配置
	 * @param userId
	 * @param data
	 * @return 
	 * ResultMessage
	 * 2013年12月8日-下午9:31:29
	 * @author Zheng Zhi Xiong
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(params = "saveUserRoles")
	@ResponseBody
	public ResultMessage saveUserRoles(Long userId,String data) {
		ResultMessage rm = new ResultMessage(); 
		List<RoleItem> roles = JsonUtil.getDTOList(data, RoleItem.class);
		roleService.saveUserWithRoles(userId, roles);
		return rm;
	}

}
