package com.xdtech.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.common.fomat.TreeBuilder;
import com.xdtech.common.utils.JsonUtil;
import com.xdtech.sys.model.MenuFunction;
import com.xdtech.sys.service.MenuFunctionService;
import com.xdtech.sys.vo.MenuItem;
import com.xdtech.web.model.ResultMessage;
import com.xdtech.web.model.TreeItem;

@Controller
@Scope("prototype")
@RequestMapping("/menuFunction.do")
public class MenuFunctionController {

	@Autowired
	private MenuFunctionService menuFunctionService;
	
	@RequestMapping(params = "sysFunction")
	public ModelAndView sysFunction(HttpServletRequest request) {
		
		return new ModelAndView("sys/menu/system_function");
	}
	@RequestMapping(params = "menuFuncTree")
	@ResponseBody
	public List<TreeItem> menuFuncTree(Long id,HttpServletRequest request) {
		List<TreeItem> items = new ArrayList<TreeItem>();
		List<MenuFunction> allMenus = menuFunctionService.loadAllMenus(false);
		TreeItem item = null;
		TreeItem pItem = null;
		for (MenuFunction menu : allMenus) {
			item = new TreeItem();
			item.setId(menu.getId());
			item.setText(menu.getNameCN());
			item.setContent(menu.getDescription());
			pItem = null;
			if (menu.getParent()!=null) {
				pItem = new TreeItem(menu.getParent().getId());
			}else if(menu.getId()!=-1L) {
				pItem = new TreeItem(-1L);
			}
//			item.setChecked(false);
			item.setParent(pItem);
			items.add(item);
			
		}
		return TreeBuilder.newTreeBuilder(TreeItem.class, Long.class).buildToTreeList(items);
	}
	@RequestMapping(params = "loadMenuInfo")
	@ResponseBody
	public MenuItem loadMenuInfo(String data) {
		MenuItem item = (MenuItem) JsonUtil.getDtoFromJsonObjStr(data, MenuItem.class);
		return menuFunctionService.loadItem(item);
	}
	
	@RequestMapping(params = "saveMenuInfo")
	@ResponseBody
	public ResultMessage saveMenuInfo(MenuItem data, HttpServletRequest req) {
		return menuFunctionService.saveMenuItem(data);
	}
	@RequestMapping(params = "deleteMenuById")
	@ResponseBody
	public ResultMessage deleteMenuById(Long menuId) {
		ResultMessage rm = new ResultMessage();
		if (menuFunctionService.deleteMenuInfo(menuId)) {
			
		} else {
			rm.setSuccess(false);
		}
		return rm;
	}
}
