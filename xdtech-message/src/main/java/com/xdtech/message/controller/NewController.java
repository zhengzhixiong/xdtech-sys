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
package com.xdtech.message.controller;

import java.util.ArrayList;
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

import com.xdtech.message.service.NewService;
import com.xdtech.message.vo.NewItem;
import com.xdtech.web.model.Pagination;
import com.xdtech.web.model.ResultMessage;

/**
 * 
 * @author max.zheng
 * @create 2014-10-26下午8:45:16
 * @since 1.0
 * @see
 */
@Controller
@Scope("prototype")
@RequestMapping("/new.do")
public class NewController {
	@Autowired
	private NewService newService;
	@RequestMapping(params = "new")
	public ModelAndView skipNew(HttpServletRequest request) {
		return new ModelAndView("message/new/new_ftl");
	}
	
	@RequestMapping(params="loadList")
	@ResponseBody
	public Map<String, Object> loadList(HttpServletRequest request,Pagination pg) {
		Map<String, Object> results = null;
		if (StringUtils.isEmpty(request.getParameter("page"))) {
			//不分页处理
			results = newService.loadPageAndCondition(null, null);
		}else {
			results = newService.loadPageAndCondition(pg, null);
		}
		
		return results;
	}
	
	@RequestMapping(params = "editNew")
	public ModelAndView editNew(HttpServletRequest request,Long id) {
		if (id!=null) {
			request.setAttribute("newItem", newService.loadNewItem(id));
		}
		return new ModelAndView("message/new/editNew_ftl");
	}
	
	@RequestMapping(params = "saveNew")
	@ResponseBody
	public ResultMessage saveNew(NewItem item) {
		ResultMessage r = new ResultMessage();
		if (newService.saveOrUpdateNew(item)) {
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	
	@RequestMapping(params = "deleteNew")
	@ResponseBody
	public ResultMessage deleteNew(long id) {
		ResultMessage r = new ResultMessage();
		if (newService.deleteNewInfo(id)) {
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	
	@RequestMapping(params = "deleteNewItems")
	@ResponseBody
	public ResultMessage deleteNewItems(String ids) {
		ResultMessage r = new ResultMessage();
		if (StringUtils.isNotEmpty(ids)) {
			String[] tempIds = ids.split(",");
			List<Long> newIds = new ArrayList<Long>();
			for (String id : tempIds) {
				newIds.add(Long.valueOf(id));
			}
			newService.deleteNewInfo(newIds);
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}

}
