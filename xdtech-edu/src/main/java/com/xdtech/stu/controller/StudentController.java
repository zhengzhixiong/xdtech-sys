package com.xdtech.stu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.stu.conditions.StudentCondition;
import com.xdtech.stu.service.StudentService;
import com.xdtech.stu.vo.StudentItem;
import com.xdtech.web.model.Pagination;
import com.xdtech.web.model.ResultMessage;

@Controller
@Scope("prototype")
@RequestMapping("/student.do")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(params = "student")
	public ModelAndView student() {
		return new ModelAndView("stu/student/student");
	}
	
	@RequestMapping(params="loadGridDatas")
	@ResponseBody
	public Map<String, Object> loadGridDatas(Pagination pg,StudentCondition condition) {
		Map<String, Object> results = studentService.loadPageAndCondition(pg, condition.getQueryConditions());
		return results;
	}
	
	@RequestMapping(params = "getGrades")
	@ResponseBody
	public List<Long> getGrades(StudentItem item) {
//		ResultMessage r = new ResultMessage();
		
//		if (studentService.saveOrUpdate(item)) {
//			r.setSuccess(true);
//		}else {
//			r.setSuccess(false);
//		}
		List<Long> gradeIds = studentService.getGradeIds(item);
		return gradeIds;
	}
	
	@RequestMapping(params = "editStudent")
	@ResponseBody
	public ResultMessage editStudent(StudentItem item) {
		ResultMessage r = new ResultMessage();
		
		if (studentService.saveOrUpdate(item)) {
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
}
