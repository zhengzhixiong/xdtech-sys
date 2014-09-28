package com.xdtech.sys.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xdtech.common.utils.JsonUtil;
import com.xdtech.sys.service.OperationService;
import com.xdtech.sys.vo.OperationItem;
import com.xdtech.web.model.DataGrid;
import com.xdtech.web.model.ResultMessage;
@Controller
@Scope("prototype")
@RequestMapping("/operation.do")
public class OperationController {
	@Autowired
	private OperationService operationService;
	
	@RequestMapping(params="loadByMenuId")
	@ResponseBody
	public List<OperationItem> loadByMenuId(Long menuId,DataGrid dataGrid,HttpServletRequest request,HttpServletResponse response) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String a = request.getParameter("a");
		String b = request.getParameter("b");
//		Map<String, Object> result = new HashMap<String, Object>(2); 
//		long count = userService.loadCountByUsergroupId(usergroupId);
//		result.put("total", count);
//		result.put("rows", users);
			
		
		return operationService.loadOperationsByMenuId(menuId);
	}
	@RequestMapping(params="saveOrUpdate")
	@ResponseBody
	public ResultMessage saveOrUpdate(String data) {
		ResultMessage rm = new ResultMessage();
		Map<String, List<OperationItem>> effectRow = JsonUtil.getMapWithListFromJson(data, OperationItem.class);
		operationService.saveOrUpdateRecord(effectRow);
		return rm;
	}
	/**
	 * 
	 * @param menuId
	 * @param roleId
	 * @param dataGrid
	 * @param request
	 * @param response
	 * @return 角色按钮列表
	 */
	@RequestMapping(params="loadByMenuIdAndRoleId")
	@ResponseBody
	public List<OperationItem> loadByMenuIdAndRoleId(Long menuId,Long roleId,DataGrid dataGrid,HttpServletRequest request,HttpServletResponse response) {
		List<OperationItem> roleButtons =  operationService.loadRoleOperations(menuId,roleId);
		return roleButtons;
	}
	/**
	 * 
	 * 保存角色对应的功能菜单集合
	 * @param roleId
	 * @param menuId
	 * @param data
	 * @return 
	 * ResultMessage
	 * 2013年12月7日-下午8:45:48
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(params="saveRoleButtons")
	@ResponseBody
	public ResultMessage saveRoleButtons(Long roleId,Long menuId,String data) {
		ResultMessage rm = new ResultMessage();
		List<OperationItem> items = JsonUtil.getDTOList(data, OperationItem.class);
		operationService.updateRoleOperations(items, roleId, menuId);
		return rm;
	}
	
	
	
}
