package com.xdtech.sys.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xdtech.sys.model.User;

import freemarker.template.TemplateException;

@Controller
@Scope("prototype")
@RequestMapping("/test.do")
public class TestController {
	
	@RequestMapping(params = "datagrid")
	public String datagrid(HttpServletRequest request, ModelMap model) {
		model.put("title", "你好");
		return "testMvc";
	}
	 @RequestMapping(params = "testFtl")
	 public String testFtl(HttpServletRequest request,ModelMap model) throws IOException, TemplateException {
//	  User user = new User();
//	  user.setLoginName("张三");
//	  user.setNickName("小山");
//	  model.addAttribute("user", user);//request保存这个对象
	  model.addAttribute("t", "测试ftl跳转");
	  return "test_fm_ftl";
	 }
	 
	 @RequestMapping(params = "testJsp")
	 public String testJsp(HttpServletRequest request,ModelMap model) throws IOException, TemplateException {
//	  User user = new User();
//	  user.setLoginName("张三");
//	  user.setNickName("小山");
//	  model.addAttribute("user", user);//request保存这个对象
	  model.addAttribute("t", "测试jsp跳转");
	  return "test_jsp";
	 }
	 
	 @RequestMapping(params = "testDefault")
	 public String testDefault(HttpServletRequest request,ModelMap model) throws IOException, TemplateException {
//	  User user = new User();
//	  user.setLoginName("张三");
//	  user.setNickName("小山");
//	  model.addAttribute("user", user);//request保存这个对象
	  model.addAttribute("t", "测试默认跳转，默认跳转是jsp");
	  return "test";
	 }
	 
	@RequestMapping(params = "testJson")
	@ResponseBody
	public User testJson(HttpServletRequest request, ModelMap model) {
		User user = new User();
		user.setCreateTime(new Date());
		user.setId(1L);
		user.setLoginName("admin");
		user.setOrderIndex(1);
		user.setPassword("pwd");
		return user;
	}

}
