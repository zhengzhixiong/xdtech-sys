///*
// * Copyright 2013-2014 the original author or authors.
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.xdtech.report.controller;
//
//import java.io.OutputStream;
//import java.net.URLEncoder;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import net.sf.jasperreports.engine.JRDataSource;
//import net.sf.jasperreports.engine.JREmptyDataSource;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JRExporterParameter;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.export.JRPdfExporter;
//
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
///**
// * 
// * @author max.zheng
// * @create 2014-11-16下午1:50:14
// * @since 1.0
// * @see
// */
//@Controller
//@Scope("prototype")
//@RequestMapping("/jr.do")
//public class TestJasperController {
//
//
//	@RequestMapping(params = "jr")
//	public ModelAndView toJr() {
//		// Retrieve our data from a mock data provider
//		MockDataFactory dataprovider = new MockDataFactory();
//		
//		// Assign the datasource to an instance of JRDataSource
//		// JRDataSource is the datasource that Jasper understands
//		// This is basically a wrapper to Java's collection classes
//		JRDataSource categoryData  = dataprovider.getCategoriesData();
//
//		// parameterMap is the Model of our application
//		Map<String,Object> parameterMap = new HashMap<String,Object>();
//		
//		// must have the empty data source!!!
//		JREmptyDataSource emptyData = new JREmptyDataSource();
//		parameterMap.put("datasource", emptyData);
//		parameterMap.put("JasperfishSubReportDatasource", categoryData);
//		parameterMap.put("JasperfishSummaryInfo", dataprovider.getSummaryInfo());
//		
//		// pdfReport is the View of our application
//		// This is declared inside the /WEB-INF/jasper-views.xml
//		ModelAndView modelAndView = new ModelAndView("pdfReport_jrxml", parameterMap);
//		return modelAndView;
//	}
//	
//	@RequestMapping(params = "export")
//	public void export(HttpServletRequest request,HttpServletResponse response) {
////		JasperReport jasperReport;
////		JasperPrint jasperPrint;
//		try {
////			jasperReport = JasperCompileManager
////					.compileReport("F:/GitHub/xdtech-sys/xdtech-edu/src/main/java/com/xdtech/report/controller/test.jrxml");
////			jasperPrint = JasperFillManager.fillReport(jasperReport,
////					new HashMap(), new JREmptyDataSource());
////			JasperExportManager.exportReportToPdfFile(jasperPrint,
////					"F:/GitHub/xdtech-sys/xdtech-edu/src/main/java/com/xdtech/report/controller/simple_report.pdf");
//		
////			String jrxmlPath = getServletContext().getRealPath(  
////	                "/jasper/sovoResume.jrxml");  
////	        List resumeInfo = getServMgr().getUserService().getResumeForDataSource(  
////	                getLoginUserId());  
////	        Map<String, String> parameters = new HashMap<String, String>();  
////	        parameters.put("SUBREPORT_DIR", getServletContext().getRealPath(  
////	                "/jasper"));  
////	        JRDataSource dataSource = new JRBeanCollectionDataSource(resumeInfo);  
//	        JasperReport report = JasperCompileManager.compileReport("F:/GitHub/xdtech-sys/xdtech-edu/src/main/java/com/xdtech/report/controller/test.jrxml");  
//	        JasperPrint jasperPrint = JasperFillManager.fillReport(report,  
//	        		new HashMap(), new JREmptyDataSource());  
////	        HttpServletResponse response = ServletActionContext.getResponse();  
//	        OutputStream ouputStream = response.getOutputStream();  
//	        // 设置相应参数，以附件形式保存PDF  
//	        response.setContentType("application/pdf");  
//	        response.setCharacterEncoding("UTF-8");  
//	        response.setHeader("Content-Disposition", "attachment; filename=\""  
//	                + URLEncoder.encode("的个人简历", "UTF-8")  
//	                + ".pdf\"");  
//	        // 使用JRPdfExproter导出器导出pdf  
//	        JRPdfExporter exporter = new JRPdfExporter();  
//	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);  
//	        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);  
//	        exporter.exportReport();// 导出  
//	        ouputStream.close();// 关闭流  
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
