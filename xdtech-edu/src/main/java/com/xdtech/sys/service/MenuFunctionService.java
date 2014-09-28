package com.xdtech.sys.service;

import java.util.List;

import com.xdtech.common.service.impl.IBaseService;
import com.xdtech.sys.model.MenuFunction;
import com.xdtech.sys.vo.MenuItem;
import com.xdtech.web.freemark.item.MenuButtonItem;
import com.xdtech.web.model.Menu;
import com.xdtech.web.model.ResultMessage;

public interface MenuFunctionService extends IBaseService<MenuFunction> {
	List<MenuFunction> loadMenuFunctionsByRoles(List<Long> roleIds);
	
	public List<MenuFunction> loadAllTreeFunctions();
	
	public List<MenuFunction> loadAllFunctions();
	
	public List<Menu> loadAllMenus();
	
	public List<MenuButtonItem> loadAllMenuButtonItems();
	
	public List<MenuFunction> loadAllMenus(boolean needRoot);
	
	public MenuItem loadItem(MenuItem item);
	
	public ResultMessage saveMenuItem(MenuItem item);
	
	public List<MenuFunction> loadByRoleId(Long roleId);

}
