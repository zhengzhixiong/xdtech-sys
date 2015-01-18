/*
 * Copyright 2013-2014 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xdtech.report.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.report.service.ConfigService;
import com.xdtech.report.util.IReportLoader;
import com.xdtech.report.util.ReportTemplateLoader;
import com.xdtech.report.vo.ConfigItem;
import com.xdtech.web.model.Pagination;

/**
 * 
 * @author max.zheng
 * @create 2015-1-15下午9:40:55
 * @since 1.0
 * @see
 */
@Controller
@Scope("prototype")
@RequestMapping("/readReport.do")
public class ReadReportController {
	@Autowired
	private ConfigService configService;
	@RequestMapping(params = "read")
	public ModelAndView skipConfig() {
		return new ModelAndView("report/read/readReport_ftl");
	}
	
	@RequestMapping(params = "configReportPage")
	public ModelAndView configReportPage(HttpServletRequest request,Long configId) throws Exception {
		String title = "";
		if (configId!=null) {
			ConfigItem configItem = configService.loadConfigItem(configId);
			title = configItem.getName();
			request.setAttribute("configItem",configItem );
			SAXReader reader = new SAXReader(); // User.hbm.xml表示你要解析的xml文档 
//			Document doc = reader.read(new File("G:/my-dev/git/xdtech-shop/src/test/java/pageTemplate.xml"));
			// 下面的是通过解析xml字符串的 
			Document doc = DocumentHelper.parseText(configItem.getXml()); // 将字符串转为XML 
			Element rootElement = doc.getRootElement(); 
			Element subGridDataElement = rootElement.element("datagrid");// 获取根节点datagrid
			//是否配置title
			if (null!=subGridDataElement.elementText("title")) {
				title = subGridDataElement.elementText("title");
			}
			//获取配置列
			Element columnsElement = subGridDataElement.element("columns");
			if (null != columnsElement&&!columnsElement.isRootElement())
			{
				List<Element> columnList = columnsElement.elements();
				JSONArray columnsArray = new JSONArray();
				JSONObject columnJson = null;
				for (Element element : columnList)
				{
					columnJson = new JSONObject();
					columnJson.put("field", element.attributeValue("field"));
					columnJson.put("title", element.attributeValue("title"));
					columnsArray.add(columnJson);
				}
//				System.out.println(columnsArray.toString());
				request.setAttribute("columns", columnsArray.toString());
			}
		}
		request.setAttribute("showReportGridId", UUID.randomUUID().toString()+"_grid");
		request.setAttribute("title", title); 
		return new ModelAndView("report/read/showReportGrid_ftl");
	}
	
	@RequestMapping(params="loadConfigReportData")
	@ResponseBody
	public Map<String, Object> loadConfigReportData(HttpServletRequest request,Pagination pg,Long reportId) {
		Map<String, Object> results = configService.loadConfigReportPage(pg, reportId);	
		return results;
	}
	@RequestMapping(params = "exportWin")
	public ModelAndView exportWin(HttpServletRequest request,Long configId) {
		if (configId!=null) {
			request.setAttribute("configItem", configService.loadConfigItem(configId));
		}
		return new ModelAndView("report/read/exportReportWin_ftl");
	}
	@RequestMapping(params="exportReport")
	public void exportReport(HttpServletRequest request,HttpServletResponse response,Long configId,String exportType) {
		try {
			ConfigItem configItem = configService.loadConfigItem(configId);
			if (StringUtils.isNotBlank(configItem.getXml()))
			{
				response.setCharacterEncoding("UTF-8");
				if (IReportLoader.PDF_TYPE.equals(exportType))
				{
					response.setContentType("application/pdf");
					String filename = System.currentTimeMillis()+".pdf";
					response.setHeader("Content-disposition", "attachment;filename=" + filename);
				}
				else if (IReportLoader.EXCEL_TYPE.equals(exportType))
				{
					response.setContentType("application/vnd.ms-excel");
					String filename = System.currentTimeMillis()+".xls";
					response.setHeader("Content-disposition", "attachment;filename=" + filename);
				}
				else if (IReportLoader.CVS_TYPE.equals(exportType))
				{
					response.setContentType("application/vnd.ms-csv");
					String filename = System.currentTimeMillis()+".csv";
					response.setHeader("Content-disposition", "attachment;filename=" + filename);
				}
				else if (IReportLoader.TXT_TYPE.equals(exportType))
				{
					response.setContentType("text/plain");
					String filename = System.currentTimeMillis()+".txt";
					response.setHeader("Content-disposition", "attachment;filename=" + filename);
				}
				else if (IReportLoader.HTML_TYPE.equals(exportType))
				{
					response.setContentType("text/html");
					String filename = System.currentTimeMillis()+".html";
					response.setHeader("Content-disposition", "attachment;filename=" + filename);
				}
				else
				{
					response.setContentType("application/pdf");
					String filename = System.currentTimeMillis()+".pdf";
					response.setHeader("Content-disposition", "attachment;filename=" + filename);
				}
				
				ServletOutputStream ouputStream = response.getOutputStream();
				ReportTemplateLoader.configIReportXml(configItem.getXml(),exportType, ouputStream);
			}
			else
			{
				response.getWriter().write("模版参数不对，如：http://localhost:81/xdtech-web/readReport.do?exportReport&configId=2&exportType=excel");
			}
		} catch (Exception e) {
			System.out.println("daochu ");
		}
		
	}
}
