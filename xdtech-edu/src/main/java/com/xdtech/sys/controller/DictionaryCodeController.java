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
package com.xdtech.sys.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.sys.service.DictionaryCodeService;
import com.xdtech.sys.vo.DictionaryCodeItem;
import com.xdtech.web.model.Pagination;
import com.xdtech.web.model.ResultMessage;

/**
 * 
 * @author max.zheng
 * @create 2014-9-28下午10:05:14
 * @since 1.0
 * @see
 */
@Controller
@Scope("prototype")
@RequestMapping("/dictionaryCode.do")
public class DictionaryCodeController {
	@Autowired
	private DictionaryCodeService dictionaryCodeService;
	@RequestMapping(params = "dictionary")
	public ModelAndView dictionary(HttpServletRequest request) {
		return new ModelAndView("sys/dictionary/dictionary_ftl");
	}
	
	@RequestMapping(params = "editDictionary")
	public ModelAndView editDictionary(HttpServletRequest request,Long dictionaryId) {
		if (dictionaryId!=null) {
//			request.setAttribute("userItem", userService.loadUserItem(userId));
		}
		return new ModelAndView("sys/dictionary/editDictionary_ftl");
	}
	
	@RequestMapping(params = "updateDictionary")
	@ResponseBody
	public ResultMessage updateDictionary(DictionaryCodeItem dictionaryCodeItem) {
		ResultMessage r = new ResultMessage();
//		if (userService.saveOrUpdateUser(userItem)) {
//			r.setSuccess(true);
//		}else {
//			r.setSuccess(false);
//		}
		return r;
	}
	
	@RequestMapping(params = "loadByCondition", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loadByCondition(Pagination pg) {
		Map<String, Object> result = dictionaryCodeService.loadPageAndCondition(pg, null);
		return result;
	}
}
