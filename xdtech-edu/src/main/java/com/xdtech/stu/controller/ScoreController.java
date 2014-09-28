package com.xdtech.stu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.common.utils.JsonUtil;
import com.xdtech.stu.conditions.ScoreCondition;
import com.xdtech.stu.service.ScoreService;
import com.xdtech.stu.vo.ScoreItem;
import com.xdtech.web.model.ResultMessage;

@Controller
@Scope("prototype")
@RequestMapping("/score.do")
public class ScoreController {
	@Autowired
	private ScoreService scoreService;
	@RequestMapping(params = "score")
	public ModelAndView student() {
		return new ModelAndView("stu/score/score");
	}
	
	@RequestMapping(params="loadList")
	@ResponseBody
	public List<ScoreItem> loadList(ScoreCondition condition,HttpServletRequest request) {
		Map map = request.getParameterMap();
		for (Object object : map.keySet()) {
			System.out.println(map.get(object).toString());
		}
		System.out.println(request.getParameterNames());
		System.out.println(request.getParameterMap());
		System.out.println(request.getParameter("sort"));
		System.out.println(request.getParameter("order"));
		
		return scoreService.loadEnteringInfo(condition);
	}
	
	/**
	 * 加载需要录入成绩的初始化信息
	 * @return
	 */
	@RequestMapping(params="loadEnteringInfo")
	@ResponseBody
	public List<ScoreItem> loadEnteringInfo(ScoreCondition condition) {

		return scoreService.loadEnteringInfo(condition);
	}
	
	@RequestMapping(params="saveOrUpdate")
	@ResponseBody
	public ResultMessage saveOrUpdate(String data) {
		ResultMessage rm = new ResultMessage();
		Map<String, List<ScoreItem>> effectRow = JsonUtil.getMapWithListFromJson(data, ScoreItem .class);
		scoreService.saveOrUpdateRecord(effectRow);
		return rm;
	}
}
