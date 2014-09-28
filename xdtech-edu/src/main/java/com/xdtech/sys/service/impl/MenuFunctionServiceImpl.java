package com.xdtech.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.common.fomat.TreeBuilder;
import com.xdtech.common.service.BaseService;
import com.xdtech.common.utils.EmptyUtil;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.sys.dao.PermissionDao;
import com.xdtech.sys.model.MenuFunction;
import com.xdtech.sys.service.MenuFunctionService;
import com.xdtech.sys.vo.MenuItem;
import com.xdtech.web.freemark.item.MenuButtonItem;
import com.xdtech.web.model.Menu;
import com.xdtech.web.model.Pagination;
import com.xdtech.web.model.ResultMessage;
@Service
@Transactional
public class MenuFunctionServiceImpl implements MenuFunctionService {
	@Autowired
	private PermissionDao permissionDao;
	public List<MenuFunction> loadMenuFunctionsByRoles(List<Long> roleIds) {
		if (EmptyUtil.isEmpty(roleIds)) {
			return null;
		}
		return permissionDao.getFunctionsByRoleIds(roleIds);
	}
	public List<MenuFunction> loadAllTreeFunctions() {
		List<MenuFunction> menus  = permissionDao.getAllFunctions();
		//格式菜单树
		return TreeBuilder.newTreeBuilder(MenuFunction.class, Long.class).buildToTreeList(menus);
	}
	
	public List<MenuFunction> loadAllFunctions() {
		List<MenuFunction> menus  = permissionDao.getAllFunctions();
		return menus;
	}
	
	public List<Menu> loadAllMenus() {
		List<MenuFunction> menuFunctions  = permissionDao.getAllFunctions();
		List<Menu> menus = new ArrayList<Menu>();
		Menu menu = null;
		for (MenuFunction menuFunction : menuFunctions) {
			menu = new Menu();
			menu.setId(menuFunction.getId());
			menu.setCode(menuFunction.getOperationCode());
			menu.setIcon(menuFunction.getIconName());
			menu.setName(menuFunction.getNameCN());
			if (menuFunction.getParent()!=null) {
				menu.setParent(new Menu(menuFunction.getParent().getId()));
			}
			menu.setUrl(menuFunction.getPageUrl());
			menus.add(menu);
		}
		
		
		return menus;
	}
	
	
	
	public List<MenuFunction> loadAllMenus(boolean needRoot) {
		// 加载所有菜单
		List<MenuFunction> allMenus =  permissionDao.getAllFunctions();
		if (needRoot) {
			MenuFunction mf = new MenuFunction();
			mf.setId(-1L);
			mf.setNameCN("功能菜单");
			allMenus.add(mf);
		}
		return allMenus;
	}
	
	public MenuItem loadItem(MenuItem item) {
		// 加载菜单信息
		MenuFunction mf = permissionDao.get(item.getId());
		BeanUtils.copyProperties(item, mf);
		return item;
	}
	
	public ResultMessage saveMenuItem(MenuItem item) {
		ResultMessage rm = new ResultMessage();
		// 保存菜单
		MenuFunction mf = new MenuFunction();
		BeanUtils.copyProperties(mf, item);
		if (null!=item.getpMenuId()&&item.getpMenuId()!=-1) {
			MenuFunction pMf = new MenuFunction();
			pMf.setId(item.getpMenuId());
			mf.setParent(pMf);
		}else {
			mf.setParent(null);
		}
		if (null==item.getId()) {
			//id 为空的话保存新记录
			mf.setId(null);
			permissionDao.save(mf);
		}else {
			//更新记录
			MenuFunction mmf = permissionDao.get(item.getId());
			BeanUtils.copyProperties(mmf, item);
			permissionDao.save(mmf);
		}
		
		return rm;
	}
	
	public List<MenuFunction> loadByRoleId(Long roleId) {
		return permissionDao.getMenuFunctionsByRoleId(roleId);
	}
	
	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		// TODO Auto-generated method stub
		return null;
	}
	public void save(MenuFunction entity) {
		permissionDao.save(entity);
	}
	public void delete(MenuFunction entity) {
		permissionDao.delete(entity);
	}
	public void delete(Long id) {
		permissionDao.delete(id);
	}
	public MenuFunction get(Long id) {
		return permissionDao.get(id);
	}
	public List<MenuFunction> getAll() {
		return permissionDao.getAll();
	}
	public List<MenuButtonItem> loadAllMenuButtonItems() {
		List<MenuFunction> menuFunctions  = permissionDao.getAllFunctions();
		List<MenuButtonItem> menuButtonItems = new ArrayList<MenuButtonItem>();
		MenuButtonItem menuButtonItem = null;
		for (MenuFunction menuFunction : menuFunctions) {
			menuButtonItem = new MenuButtonItem();
			menuButtonItem.setId(menuFunction.getId());
			menuButtonItem.setCode(menuFunction.getOperationCode());
			menuButtonItem.setIcon(menuFunction.getIconName());
			menuButtonItem.setName(menuFunction.getNameCN());
			menuButtonItem.setIframe(menuFunction.getIframe());
			if (menuFunction.getParent()!=null) {
				menuButtonItem.setParent(new MenuButtonItem(menuFunction.getParent().getId()));
			}
			menuButtonItem.setUrl(menuFunction.getPageUrl());
			menuButtonItems.add(menuButtonItem);
		}
		return menuButtonItems;
	}

}
