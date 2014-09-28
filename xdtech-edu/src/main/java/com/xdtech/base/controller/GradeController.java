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

import com.xdtech.base.model.Grade;
import com.xdtech.base.service.GradeService;
import com.xdtech.base.vo.GradeItem;
import com.xdtech.common.utils.JsonUtil;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.web.model.ComboBox;
import com.xdtech.web.model.Pagination;
import com.xdtech.web.model.ResultMessage;

@Controller
@Scope("prototype")
@RequestMapping("/grade.do")
public class GradeController {
	@Autowired
	private GradeService gradeService;
	@RequestMapping(params = "grade")
	public ModelAndView grade() {
		return new ModelAndView("base/grade/grade");
	}
	
	@RequestMapping(params="loadList")
	@ResponseBody
	public List<GradeItem> loadList(Long usergroupId, Pagination pg) {
		List<GradeItem> items = BeanUtils.copyListProperties(GradeItem.class, gradeService.getAll());
		return items;
	}
	
	@RequestMapping(params="saveOrUpdate")
	@ResponseBody
	public ResultMessage saveOrUpdate(String data) {
		ResultMessage rm = new ResultMessage();
		Map<String, List<GradeItem>> effectRow = JsonUtil.getMapWithListFromJson(data, GradeItem .class);
		gradeService.saveOrUpdateRecord(effectRow);
		return rm;
	}
	//http://localhost:8080/edu/grade.do?loadComboBox&initNullSelect=true
	@RequestMapping(params="loadComboBox")
	@ResponseBody
	public List<ComboBox> loadComboBox(boolean initNullSelect) {
		List<Grade> grades = gradeService.getAll();
		
		List<ComboBox> boxs = new ArrayList<ComboBox>();
		ComboBox box = null;
		if (initNullSelect) {
			box = new ComboBox(initNullSelect);
			boxs.add(box);
		}
		for (Grade grade : grades) {
			box = new ComboBox(false);
			box.setCode(grade.getId().toString());
			box.setText(grade.getName());
			boxs.add(box);
		}
		return boxs;
	}
}
