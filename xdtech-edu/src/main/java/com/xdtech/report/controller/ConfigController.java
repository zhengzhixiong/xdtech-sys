package com.xdtech.report.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.common.fomat.TreeBuilder;
import com.xdtech.report.model.Config;
import com.xdtech.report.service.ConfigService;
import com.xdtech.report.vo.ConfigItem;
import com.xdtech.web.model.Pagination;
import com.xdtech.web.model.ResultMessage;
import com.xdtech.web.model.TreeItem;

/**
 * 
 * @author max.zheng
 * @create 2015-01-14 21:25:22
 * @since 1.0
 * @see
 */
@Controller
@Scope("prototype")
@RequestMapping("/config.do")
public class ConfigController {
	@Autowired
	private ConfigService configService;
	@RequestMapping(params = "config")
	public ModelAndView skipConfig() {
		return new ModelAndView("report/config/config_ftl");
	}
	
	@RequestMapping(params="loadList")
	@ResponseBody
	public Map<String, Object> loadList(HttpServletRequest request,Pagination pg) {
		Map<String, Object> results = null;
		if (StringUtils.isEmpty(request.getParameter("page"))) {
			//不分页处理
			results = configService.loadPageAndCondition(null, null);
		}else {
			results = configService.loadPageAndCondition(pg, null);
		}
		
		return results;
	}
	
	@RequestMapping(params = "editConfig")
	public ModelAndView editConfig(HttpServletRequest request,Long configId) {
		if (configId!=null) {
			request.setAttribute("configItem", configService.loadConfigItem(configId));
		}
		return new ModelAndView("report/config/editConfig_ftl");
	}
	
	@RequestMapping(params = "saveConfig")
	@ResponseBody
	public ResultMessage saveConfig(ConfigItem item) {
		ResultMessage r = new ResultMessage();
		if (configService.saveOrUpdateConfig(item)) {
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	
	@RequestMapping(params = "deleteConfigItems")
	@ResponseBody
	public ResultMessage deleteConfigItems(String ids) {
		ResultMessage r = new ResultMessage();
		if (StringUtils.isNotEmpty(ids)) {
			String[] tempIds = ids.split(",");
			List<Long> configIds = new ArrayList<Long>();
			for (String id : tempIds) {
				configIds.add(Long.valueOf(id));
			}
			configService.deleteConfigInfo(configIds);
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	
	@RequestMapping(params = "reportTree")
	@ResponseBody
	public List<TreeItem> reportTree(HttpServletRequest request) {
		List<TreeItem> items = new ArrayList<TreeItem>();
		TreeItem item = null;
		TreeItem pItem = null;
		List<Config> configs = configService.getAll();
		for (Config config : configs) {
			item = new TreeItem();
			item.setId(config.getId());
			item.setText(config.getName());
			item.setParent(pItem);
			items.add(item);
		}
		return TreeBuilder.newTreeBuilder(TreeItem.class, Long.class).buildToTreeList(items);
	}
	
	

}
