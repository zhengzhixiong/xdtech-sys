package com.xdtech.sys.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xdtech.sys.service.CodeValueService;
import com.xdtech.web.model.Pagination;

@Controller
@Scope("prototype")
@RequestMapping("/codeValue.do")
public class CodeValueController {
	@Autowired
	private CodeValueService codeValueService;
	
	
	@RequestMapping(params = "loadByCondition", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loadByCondition(Pagination pg,Long dictionaryId) {
		Map<String, Object> result = codeValueService.loadByDictionaryId(pg, dictionaryId);
		return result;
	}
	
}
