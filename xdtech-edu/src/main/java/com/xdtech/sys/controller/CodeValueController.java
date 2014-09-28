package com.xdtech.sys.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xdtech.sys.service.DictionaryCodeService;

@Controller
@Scope("prototype")
@RequestMapping("/codeValue.do")
public class CodeValueController {
	
	
	private DictionaryCodeService dictionaryCodeService;
	
}
