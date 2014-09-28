package com.xdtech.sys.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.sys.service.LogService;
import com.xdtech.web.model.Pagination;

@Controller
@Scope("prototype")
@RequestMapping("/log.do")
public class LogController {
	@Autowired
	private LogService logService;
	
	@RequestMapping(params = "log")
	public ModelAndView log(HttpServletRequest request) {
		return new ModelAndView("sys/log/log_ftl");
	}
	//http://localhost:8080/edu/log.do?loadGridDatas&page=1&rows=30
	@RequestMapping(params="loadGridDatas")
	@ResponseBody
	public Map<String, Object> loadGridDatas(HttpServletRequest request,Pagination pg) {
		Map map = request.getParameterMap();
		for (Object object : map.keySet()) {
			System.out.println(object+"==="+map.get(object));
		}
		Map<String, Object> results = null;
		if (StringUtils.isEmpty(request.getParameter("page"))) {
			//不分页处理
			results = logService.loadPageAndCondition(null, null);
		}else {
			results = logService.loadPageAndCondition(pg, null);
		}
		
		return results;
	}
}
