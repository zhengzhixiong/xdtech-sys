package com.xdtech.base.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.base.model.Subject;
import com.xdtech.base.service.SubjectService;
import com.xdtech.base.vo.SubjectItem;
import com.xdtech.common.utils.JsonUtil;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.web.model.ComboBox;
import com.xdtech.web.model.Pagination;
import com.xdtech.web.model.ResultMessage;

@Controller
@Scope("prototype")
@RequestMapping("/subject.do")
public class SubjectController {
	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping(params = "subject")
	public ModelAndView subject() {
		return new ModelAndView("base/subject/subject");
	}
	
	@RequestMapping(params="loadList")
	@ResponseBody
	public List<SubjectItem> loadList(Pagination pg) {
		List<SubjectItem> items = BeanUtils.copyListProperties(SubjectItem.class, subjectService.getAll());
		return items;
	}
	
	@RequestMapping(params="saveOrUpdate")
	@ResponseBody
	public ResultMessage saveOrUpdate(String data) {
		ResultMessage rm = new ResultMessage();
		Map<String, List<SubjectItem>> effectRow = JsonUtil.getMapWithListFromJson(data, SubjectItem .class);
		subjectService.saveOrUpdateRecord(effectRow);
		return rm;
	}
	//http://localhost:8080/edu/grade.do?loadComboBox&initNullSelect=true
	@RequestMapping(params="loadComboBox")
	@ResponseBody
	public List<ComboBox> loadComboBox(boolean initNullSelect) {
		List<Subject> subjects = subjectService.getAll();
		
		List<ComboBox> boxs = new ArrayList<ComboBox>();
		ComboBox box = null;
		if (initNullSelect) {
			box = new ComboBox(initNullSelect);
			boxs.add(box);
		}
		for (Subject subject : subjects) {
			box = new ComboBox(false);
			box.setCode(subject.getId().toString());
			box.setText(subject.getName());
			boxs.add(box);
		}
		return boxs;
	}
}
