/*
 * Project Name: CZW_PRO
 * File Name: ReportTemplateLoader.java
 * Copyright: Copyright(C) 1985-2014 ZKTeco Inc. All rights reserved.
 */
package com.xdtech.report.util;

import java.io.File;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 图表配置加载类
 * 
 * @author <a href="max.zheng@zkteco.com">郑志雄</>
 * @version TODO 添加版本
 * @see 相关类或方法，不需要请删除此行
 * @since 2014-12-31 上午8:52:46
 */
public class ReportTemplateLoader {
	private static Logger log = Logger.getLogger(ReportTemplateLoader.class);

	public static void main(String[] args) {
		// System.out.println(System.getProperty("user.dir"));
		// // System.out.println(System.getProperty("java.class.path"));
		// System.out.println(ReportTemplateLoader.class.getResource(""));
		// String path = ReportTemplateLoader.class.getResource("/") +
		// "highChartsTemplate.xml";
		// System.out.println(path);
		// System.out.println(StringUtils.isNotBlank("  "));
		readStringXml("F:/svn/CZW/CZW_PRO/report/template/exsampleTemp.xml",
				null, null);
	}

	/**
	 * 
	 * 
	 * @author <a href="max.zheng@zkteco.com">郑志雄</>
	 * @since 2015-1-5 下午4:18:41
	 * @param xmlPath
	 *            配置模板路径
	 * @param type
	 *            导出文件类型
	 * @param outputStream
	 *            输出流 是ireport类型，输出流必须填写,输出给界面供下载
	 * @return
	 */
	private static String readStringXml(String xml, String exportType,
			OutputStream outputStream) {
		Document doc = null;
		try {
			// 读取并解析XML文档
			// SAXReader就是一个管道，用一个流的方式，把xml文件读出来
			SAXReader reader = new SAXReader(); // User.hbm.xml表示你要解析的xml文档
//			doc = reader.read(new File(xmlPath));
			// 下面的是通过解析xml字符串的
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElement = doc.getRootElement(); // 获取根节点
			// 获取配置类型
			String type = rootElement.attributeValue("type");

			if ("highcharts".equals(type)) {
				Element highChartsElement = rootElement.element("highcharts");
				return HighChartTempLoader.handleHighCharts(highChartsElement);
			} else if ("ireport".equals(type)) {
				Element ireportElement = rootElement.element("datagrid");
				IReportLoader.handleIReport(ireportElement, exportType,
						outputStream);
			}
		} catch (Exception e) {
			log.error("配置文件加载有误", e);
		}
		return "";
	}

	/**
	 * highcharts加载
	 * 
	 * @author <a href="max.zheng@zkteco.com">郑志雄</>
	 * @since 2015-1-5 下午4:45:12
	 * @param xmlPath
	 * @return
	 */
	public static void configIReportXml(String xml, String type,
			OutputStream outputStream) {
		Document doc = null;
		try {
			// 读取并解析XML文档
			// SAXReader就是一个管道，用一个流的方式，把xml文件读出来
			SAXReader reader = new SAXReader();
//			doc = reader.read(new File(xmlPath));
			// 下面的是通过解析xml字符串的
			 doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElement = doc.getRootElement(); // 获取根节点
			Element subElement = rootElement.element("datagrid");
			IReportLoader.handleIReport(subElement, type, outputStream);
		} catch (Exception e) {
			log.error("配置文件HighCharts加载有误", e);
		}
	}

	/**
	 * highcharts加载
	 * 
	 * @author <a href="max.zheng@zkteco.com">郑志雄</>
	 * @since 2015-1-5 下午4:45:12
	 * @param xmlPath
	 * @return
	 */
	public static String configHighChartsXml(String xmlPath) {
		Document doc = null;
		try {
			// 读取并解析XML文档
			// SAXReader就是一个管道，用一个流的方式，把xml文件读出来
			SAXReader reader = new SAXReader(); // User.hbm.xml表示你要解析的xml文档
			doc = reader.read(new File(xmlPath));
			// 下面的是通过解析xml字符串的
			// doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElement = doc.getRootElement(); // 获取根节点
			Element highChartsElement = rootElement.element("highcharts");
			return HighChartTempLoader.handleHighCharts(highChartsElement);
		} catch (Exception e) {
			log.error("配置文件HighCharts加载有误", e);
		}
		return "";
	}

}
