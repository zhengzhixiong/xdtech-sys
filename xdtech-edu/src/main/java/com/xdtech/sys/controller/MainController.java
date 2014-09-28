package com.xdtech.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.common.fomat.TreeBuilder;
import com.xdtech.sys.model.MenuFunction;
import com.xdtech.sys.model.User;
import com.xdtech.sys.service.MenuFunctionService;
import com.xdtech.sys.util.SessionContextUtil;
import com.xdtech.web.freemark.item.MenuButtonItem;
import com.xdtech.web.model.Menu;

@Controller
@Scope("prototype")
@RequestMapping("/main.do")
public class MainController {
	@Autowired
	private MenuFunctionService menuFunctionService;


	/**
	 * 菜单跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "home")
	public ModelAndView home(HttpServletRequest request) {
		User user = SessionContextUtil.getCurrentUser();
		Subject subject = SecurityUtils.getSubject();  
        System.out.println(subject.hasRole("manager"));
        System.out.println(subject.hasRole("admin2"));
        System.out.println(subject.hasRole("sys_manager"));
        System.out.println(subject.isPermitted("sys_manager"));
        System.out.println(subject.isPermitted("sys_manager2"));
		return new ModelAndView("home");
	}
	/**
	 * 菜单跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "main")
	public ModelAndView main(HttpServletRequest request) {
		User user = SessionContextUtil.getCurrentUser();
		Subject subject = SecurityUtils.getSubject();
		List<MenuButtonItem> menuButtonItems = menuFunctionService.loadAllMenuButtonItems();
		List<MenuButtonItem> hasMenus = new ArrayList<MenuButtonItem>();
		for (MenuButtonItem m : menuButtonItems) {
			if (SecurityUtils.getSubject().isPermitted(m.getCode())) {
				hasMenus.add(m);
			}
		}
		
		request.setAttribute("menus", TreeBuilder.newTreeBuilder(MenuButtonItem.class, Long.class).buildToTreeList(hasMenus));
		return new ModelAndView("main_ftl");
	}
	/**
	 * 首页顶部
	 * 
	 * @return
	 */
	@RequestMapping(params = "top")
	public ModelAndView top(HttpServletRequest request) {
//		List<MenuFunction> menus = menuFunctionService.loadAllFunctions();
//		List<MenuFunction> hasMenus = new ArrayList<MenuFunction>();
//		for (MenuFunction mf : menus) {
//			checkHasPermitMenu(mf, hasMenus);
//		}
		List<Menu> menus = menuFunctionService.loadAllMenus();
		List<Menu> hasMenus = new ArrayList<Menu>();
		for (Menu m : menus) {
			if (SecurityUtils.getSubject().isPermitted(m.getCode())) {
				hasMenus.add(m);
			}
		}

		request.setAttribute("menus", TreeBuilder.newTreeBuilder(Menu.class, Long.class).buildToTreeList(hasMenus));
		return new ModelAndView("top");
	}
	
//	private void checkHasPermitMenu(Menu cMf,List<Menu> resultMfs) {
//		if (SecurityUtils.getSubject().isPermitted(cMf.getOperationCode())) {
//			resultMfs.add(cMf);
//		}
//	}
	
	@RequestMapping(params = "defaultPage")
	public ModelAndView defaultPage(HttpServletRequest request) {
		return new ModelAndView("defaultPage_ftl");
	}

}
