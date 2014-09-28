package com.xdtech.edu.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xdtech.edu.service.TeacherService;

@Controller
@Scope("prototype")
@RequestMapping("/teacher.do")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(params = "teacher")
	public ModelAndView teacher(HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		return new ModelAndView("edu/teacher/teacher");
	}
}
