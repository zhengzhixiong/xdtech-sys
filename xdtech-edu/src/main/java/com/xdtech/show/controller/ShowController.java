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
package com.xdtech.show.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.shop.service.CategoryService;
import com.xdtech.shop.vo.CategoryItem;
import com.xdtech.show.service.ArticleService;
import com.xdtech.web.model.Pagination;

/**
 * 
 * @author max.zheng
 * @create 2014-12-3下午9:34:47
 * @since 1.0
 * @see
 */
@Controller
@Scope("prototype")
@RequestMapping("/show.do")
public class ShowController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CategoryService categoryService;
	@RequestMapping(params = "skip")
	public ModelAndView skip(String url,HttpServletRequest request) {
//		List<ArticleItem> articles = articleService.loadArticleItems();
//		request.setAttribute("articles", articles);
		return new ModelAndView("show/"+url+"_ftl");
	}
	@RequestMapping(params = "index")
	public ModelAndView index(HttpServletRequest request) {
		Pagination pg = new Pagination();
		Map<String, Object> mapArticles = articleService.loadPageAndCondition(pg, null);
		request.setAttribute("articles", mapArticles.get("rows"));
		return new ModelAndView("show/index_ftl");
	}
	
	@RequestMapping(params = "indexShop")
	public ModelAndView index(String url) {
		return new ModelAndView("shop/index_ftl");
	}
	@RequestMapping(params = "to")
	public ModelAndView to(String url,HttpServletRequest request) {
//		List<CategoryItem> categoryItems = categoryService.loadCategoryItems();
//		request.setAttribute("categories", categoryItems);
		return new ModelAndView("shop/"+url+"_ftl");
	}
}
