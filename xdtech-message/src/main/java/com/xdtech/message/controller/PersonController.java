package com.xdtech.message.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.message.service.PersonService;
import com.xdtech.message.vo.PersonItem;
import com.xdtech.web.model.Pagination;
import com.xdtech.web.model.ResultMessage;

/**
 * 
 * @author max.zheng
 * @create 2014-11-02 00:15:20
 * @since 1.0
 * @see
 */
@Controller
@Scope("prototype")
@RequestMapping("/person.do")
public class PersonController {
	@Autowired
	private PersonService personService;

	@RequestMapping(params = "person")
	public ModelAndView skipPerson() {
		return new ModelAndView("message/person/person_ftl");
	}
	
	@RequestMapping(params="loadList")
	@ResponseBody
	public Map<String, Object> loadList(HttpServletRequest request,Pagination pg) {
		Map<String, Object> results = null;
		if (StringUtils.isEmpty(request.getParameter("page"))) {
			//不分页处理
			results = personService.loadPageAndCondition(null, null);
		}else {
			results = personService.loadPageAndCondition(pg, null);
		}
		
		return results;
	}
	
	@RequestMapping(params = "editPerson")
	public ModelAndView editPerson(HttpServletRequest request,Long personId) {
		if (personId!=null) {
			request.setAttribute("personItem", personService.loadPersonItem(personId));
		}
		return new ModelAndView("message/person/editPerson_ftl");
	}
	
	@RequestMapping(params = "savePerson")
	@ResponseBody
	public ResultMessage savePerson(@ModelAttribute PersonItem item) {
		ResultMessage r = new ResultMessage();
		if (personService.saveOrUpdatePerson(item)) {
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	
	@RequestMapping(params = "deletePerson")
	@ResponseBody
	public ResultMessage deletePerson(long id) {
		ResultMessage r = new ResultMessage();
		if (personService.deletePersonInfo(id)) {
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}

}
