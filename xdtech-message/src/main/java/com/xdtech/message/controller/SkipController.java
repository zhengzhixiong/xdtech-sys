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

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 界面跳转测试类
 * @author max.zheng
 * @create 2014-10-23下午9:41:16
 * @since 1.0
 * @see
 */
@Controller
@Scope("prototype")
@RequestMapping("/skip.do")
public class SkipController {
	@RequestMapping(params = "main")
	public ModelAndView main(HttpServletRequest request) {
		return new ModelAndView("main_ftl");
	}
	
	@RequestMapping(params = "skip")
	public ModelAndView skip(String url) {
		return new ModelAndView(url+"_ftl");
	}
	
	@RequestMapping(params = "user")
	public ModelAndView user(HttpServletRequest request) {
		return new ModelAndView("user2_ftl");
	}
}
