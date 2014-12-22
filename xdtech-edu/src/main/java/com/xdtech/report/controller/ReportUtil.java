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
//import java.io.ByteArrayOutputStream;
//
//import java.io.File;
//
//import java.io.FileOutputStream;
//
//import java.io.OutputStream;
//
//import java.util.HashMap;
//
//import java.util.Map;
//
//import net.sf.jasperreports.engine.JRAbstractExporter;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperReport;
//
//import net.sf.jasperreports.engine.JREmptyDataSource;
//
//import net.sf.jasperreports.engine.JRExporterParameter;
//
//import net.sf.jasperreports.engine.JasperFillManager;
//
//import net.sf.jasperreports.engine.JasperPrint;
//
//import net.sf.jasperreports.engine.JasperRunManager;
//
//import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
//
//import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
//
//import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
//
//public class ReportUtil {
//
//	public static String jasperFilePath = "F:\\document\\report\\report1.jasper";// 模版文件
//
//	public static String pdfFilePath = "F:\\document\\report\\report1.pdf";// 生成pdf文件
//
//	public static String docxFilePath = "F:\\document\\report\\report1.docx";// 生成wod文件
//
//	public static String xlsxFilePath = "F:\\document\\report\\report1.xlsx";// 生成excel文件
//
//	/**
//	 * 生成文件pdf
//	 * 
//	 * @throws Exception
//	 */
//
//	public static void addPdf() throws Exception {
//
//		// 第一步：装载jasper文件
//
//		File jasperFileName = new File(jasperFilePath);
//
//		// 第二步：设置参数值
//
//		/* 设置参数 */
//
//		HashMap<String, Object> params = new HashMap<String, Object>();// 建立参数表
//
//		params.put("name", "我们的产品"); // 设置参数值
//
//		// 第三步：利用JasperRunManager生成PDF文件
//
//		JasperRunManager.runReportToPdfFile(jasperFileName.getPath(),
//
//		params, new JREmptyDataSource());
//	}
//
//	public static void addDocx() throws Exception {
//
//		File jasperFileName = new File(jasperFilePath);
//
//		Map<String, Object> params = new HashMap<String, Object>();
//
//		params.put("name", "我们的产品");
//
//		JasperPrint jasperPrint = JasperFillManager.fillReport(
//
//		jasperFileName.getPath(), params, new JREmptyDataSource());
//
//		ByteArrayOutputStream oStream = new ByteArrayOutputStream();
//
//		JRAbstractExporter exporter = new JRDocxExporter(); // 可以替换成不同的文件类型
//
//		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//
//		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oStream);
//
//		exporter.exportReport();
//
//		byte[] bytes = oStream.toByteArray();
//
//		if (bytes != null && bytes.length > 0) {
//
//			OutputStream ouputStream = new FileOutputStream(new File(
//
//			docxFilePath));
//
//			ouputStream.write(bytes, 0, bytes.length);
//
//			ouputStream.flush();
//
//			ouputStream.close();
//
//		} else {
//
//		}
//	}
//
//	public static void addXls() throws Exception {
//
//		File jasperFileName = new File(jasperFilePath);
//
//		Map<String, Object> params = new HashMap<String, Object>();
//
//		params.put("name", "我们的产品");
//
//		JasperPrint jasperPrint = JasperFillManager.fillReport(
//
//		jasperFileName.getPath(), params, new JREmptyDataSource());
//
//		ByteArrayOutputStream oStream = new ByteArrayOutputStream();
//
//		JRAbstractExporter exporter = new JRXlsxExporter();// 可以替换成不同的文件类型
//
//		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//
//		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oStream);
//
//		exporter.setParameter(
//
//		JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
//
//		Boolean.TRUE); // 删除记录最下面的空行
//
//		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
//
//		Boolean.FALSE);// 删除多余的ColumnHeader
//
//		exporter.setParameter(
//
//		JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
//
//		Boolean.FALSE);// 显示边框
//
//		exporter.exportReport();
//
//		byte[] bytes = oStream.toByteArray();
//
//		if (bytes != null && bytes.length > 0) {
//
//			OutputStream ouputStream = new FileOutputStream(new File(
//
//			xlsxFilePath));
//
//			ouputStream.write(bytes, 0, bytes.length);
//
//			ouputStream.flush();
//
//			ouputStream.close();
//
//		} else {
//
//		}
//	}
//
//	public static void main(String[] args) throws Exception {
//
//		// 生成pdf文件
//
//		 ReportUtil.addPdf();
//
//		// 生成word文件
//
//		 ReportUtil.addDocx();
//
//		// 生成excel文件
//
//		 ReportUtil.addXls();
//
//		JasperReport jasperReport;
//		JasperPrint jasperPrint;
//		try {
//			jasperReport = JasperCompileManager
//					.compileReport("F:/GitHub/xdtech-sys/xdtech-edu/src/main/java/com/xdtech/report/controller/test.jrxml");
//			jasperPrint = JasperFillManager.fillReport(jasperReport,
//					new HashMap(), new JREmptyDataSource());
//			JasperExportManager
//					.exportReportToPdfFile(
//							jasperPrint,
//							"F:/GitHub/xdtech-sys/xdtech-edu/src/main/java/com/xdtech/report/controller/simple_report.pdf");
//		} catch (JRException e) {
//			e.printStackTrace();
//		}
//
//	}
//}
