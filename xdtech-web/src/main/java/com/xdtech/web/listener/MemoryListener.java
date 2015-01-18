/*
 * Copyright 2013-2014 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xdtech.web.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.xdtech.common.utils.ApplicationContextUtil;
import com.xdtech.shop.service.CategoryService;
import com.xdtech.shop.vo.CategoryItem;

/**
 * 
 * @author max.zheng
 * @create 2014-12-26下午9:11:24
 * @since 1.0
 * @see
 */
public class MemoryListener implements ServletContextListener {

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-26下午9:11:45
	 * @modified by
	 * @param arg0
	 */
	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		System.out.println("application destroying");
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-26下午9:11:45
	 * @modified by
	 * @param arg0
	 */
	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		ServletContext application = contextEvent.getServletContext();
		ApplicationContextUtil.setApplication(application);
		System.out.println("application initing");
		CategoryService categoryService = (CategoryService) ApplicationContextUtil.getContext().getBean(CategoryService.class);
		List<CategoryItem> categoryItems = categoryService.loadCategoryItems();
		application.setAttribute("categories", categoryItems);
		System.out.println("initing over");
	}

}
