/*
 * Project Name: CZW_PRO
 * File Name: IReportLoader.java
 * Copyright: Copyright(C) 1985-2015 ZKTeco Inc. All rights reserved.
 */
package com.xdtech.report.util;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JRSection;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.StretchTypeEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.xdtech.core.config.PropertiesConfigurer;
import com.xdtech.report.jasper.Column;



/**
 * ireport配置的xml文件加载
 * 
 * @author <a href="max.zheng@zkteco.com">郑志雄</>
 * @version TODO 添加版本
 * @see 相关类或方法，不需要请删除此行
 * @since 2015-1-5 上午11:23:53
 */
@SuppressWarnings("deprecation")
public class IReportLoader {
	private static Logger log = Logger.getLogger(IReportLoader.class);
	public final static String EXCEL_TYPE = "excel";
	public final static String PDF_TYPE = "pdf";
	public final static String HTML_TYPE = "html";
	public final static String CVS_TYPE = "cvs";
	public final static String TXT_TYPE = "txt";

	public static void handleIReport(Element ireportElement, String type,
			OutputStream outputStream) {
		// 标题
		String title = ireportElement.elementTextTrim("title");
		// sql
		String sql = ireportElement.elementTextTrim("sql");
		// String begin = "0000";
		// String end = "1111";
		//
		// String sqlColumn = sql.substring(sql.indexOf(begin)+
		// begin.length()+1, sql.indexOf(end));
		// String[] sqlColumns = sqlColumn.split(",");
		// Map sqlClMap = new ListOrderedMap();
		// for (String sc : sqlColumns)
		// {
		// if (StringUtils.isNotBlank(sc))
		// {
		// sqlClMap.put(sc.trim(), sc.trim());
		// }
		// }
		// 列
		Element columnsElement = ireportElement.element("columns");
		List<Element> columnList = columnsElement.elements();
		List<Column> columns = new ArrayList<com.xdtech.report.jasper.Column>();
		// 单列的宽度
		int columnWidth = 100;

		int pageWidth = 0;
		for (Element column : columnList) {
			String key = column.attributeValue("field");
			String name = column.attributeValue("title");
			String width = column.attributeValue("width");
			if (null == width) {
				width = columnWidth + "";
			}
			columns.add(new Column(key, name, Integer.valueOf(width)));
			pageWidth += Integer.valueOf(width);
			// if (sqlClMap.containsKey(key))
			// {
			// //sql里包含列，才添加
			// columnsMap.put(key, column.attributeValue("name"));
			// sqlClMap.remove(key);
			// }
		}
		if (pageWidth<595) {
			pageWidth = 595;
		}
		// 将sql里没定义的列直接添加完整；是否考虑这种处理 max
		// columnsMap.putAll(sqlClMap);
		try {
			JasperPrint jasperPrint = createJasperreport(title, columns, sql,
					pageWidth, type);
			// JasperExportManager.exportReportToPdfFile(jasperPrint,
			// "F:/jasperreport/jasperresults/reportTest.pdf");
			if (EXCEL_TYPE.equals(type)) {
				exportExcel(jasperPrint, outputStream);
			} else if (PDF_TYPE.equals(type)) {
				exportPDF(jasperPrint, outputStream);
			} else if (CVS_TYPE.equals(type)) {
				exportCsv(jasperPrint, outputStream);
			} else if (TXT_TYPE.equals(type)) {
				exportTxt(jasperPrint, outputStream);
			} else if (HTML_TYPE.equals(type)) {
				exportHtml(jasperPrint, outputStream);
			}
		} catch (Exception e) {
			log.error("ireport动态生成报表有误", e);
		}
	}

	/**
	 * 导出txt
	 * 
	 * @author <a href="max.zheng@zkteco.com">郑志雄</>
	 * @since 2015-1-5 下午5:51:44
	 * @param jasperPrint
	 * @param outputStream
	 * @throws IOException
	 * @throws JRException
	 */
	@SuppressWarnings("deprecation")
	public static void exportTxt(JasperPrint jasperPrint,
			OutputStream outputStream) throws IOException, JRException {
		JRTextExporter exporter = new JRTextExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		// exporter.setParameter(JRTextExporterParameter.BETWEEN_PAGES_TEXT,
		// "\\f");
		exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH,
				new Float(6.55));// 6.55
		exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT,
				new Float(11.9)); // 11//10
		exporter.setParameter(
				JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
				Boolean.FALSE);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		exporter.exportReport();
		outputStream.flush();
		outputStream.close();
	}

	/**
	 * 导出excel
	 * 
	 * @author <a href="max.zheng@zkteco.com">郑志雄</>
	 * @since 2015-1-5 下午5:51:34
	 * @param jasperPrint
	 * @param outputStream
	 * @throws IOException
	 * @throws JRException
	 */
	@SuppressWarnings("deprecation")
	public static void exportExcel(JasperPrint jasperPrint,
			OutputStream outputStream) throws IOException, JRException {
		// response.setContentType("application/vnd.ms-excel");
		// String filename = System.currentTimeMillis()+".xls";
		// response.setHeader("Content-disposition", "attachment;filename=" +
		// filename);
		// ServletOutputStream ouputStream = response.getOutputStream();
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		exporter.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				Boolean.FALSE);
		exporter.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
				Boolean.FALSE);
		exporter.exportReport();
		outputStream.flush();
		outputStream.close();
	}

	/**
	 * 导出cvs
	 * 
	 * @author <a href="max.zheng@zkteco.com">郑志雄</>
	 * @since 2015-1-5 下午5:51:25
	 * @param jasperPrint
	 * @param outputStream
	 * @throws Exception
	 */
	public static void exportCsv(JasperPrint jasperPrint,
			OutputStream outputStream) throws Exception {
		// response.setContentType("application/vnd.ms-csv");
		// String filename = System.currentTimeMillis()+".csv";
		// response.setHeader("Content-disposition", "attachment;filename=" +
		// filename);
		// ServletOutputStream ouputStream = response.getOutputStream();
		JRCsvExporter exporter = new JRCsvExporter();
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "gbk");
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		exporter.exportReport();
		outputStream.flush();
		outputStream.close();
	}

	/**
	 * 导出html
	 * 
	 * @author <a href="max.zheng@zkteco.com">郑志雄</>
	 * @since 2015-1-5 下午5:51:18
	 * @param jasperPrint
	 * @param outputStream
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static void exportHtml(JasperPrint jasperPrint,
			OutputStream outputStream) throws Exception {
		// response.setContentType("application/html");
		// String filename = "未命名.html";
		// response.setHeader("Content-disposition", "attachment;filename=" +
		// filename);
		// ServletOutputStream ouputStream = response.getOutputStream();
		JRHtmlExporter exporter = new JRHtmlExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		exporter.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				Boolean.FALSE);
		exporter.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
				Boolean.FALSE);
		exporter.exportReport();
		outputStream.flush();
		outputStream.close();
	}

	/**
	 * 导出pdf
	 * 
	 * @author <a href="max.zheng@zkteco.com">郑志雄</>
	 * @since 2015-1-5 下午5:51:10
	 * @param jasperPrint
	 * @param outputStream
	 * @throws Exception
	 */
	public static void exportPDF(JasperPrint jasperPrint,
			OutputStream outputStream) throws Exception {
		// response.setContentType("application/pdf");
		// String filename = "未命名.pdf";
		// response.setHeader("Content-disposition", "attachment;filename=" +
		// filename);

		// ServletOutputStream ouputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		outputStream.flush();
		outputStream.close();
	}

	/**
	 * 创建报表
	 * 
	 * @author <a href="max.zheng@zkteco.com">郑志雄</>
	 * @since 2015-1-5 上午11:37:07
	 * @param title
	 * @param columnsMap
	 * @param sql
	 * @throws Exception
	 */
	private static JasperPrint createJasperreport(String titleText,
			List<Column> columns, String sql, int pageWidth, String exportType)
			throws Exception {
		String path = IReportLoader.class.getResource("/")
				+ "templateReport.jrxml";
		path = path.substring(6).replaceAll("%20", " ");
		// 加载模板文件
		JasperDesign jasperDesign = JRXmlLoader.load(new File(path));
		jasperDesign.setPageWidth(pageWidth+40);
		// jasperDesign.setColumnWidth(130);
		// jasperDesign.setLeftMargin(0);
		// jasperDesign.setPageHeight(842);
		int _START_X_ = 20;// x轴的起始位置
		int startX = _START_X_; // x轴的起始位置
		// 获取模板文件title区域
		JRDesignBand title = (JRDesignBand) jasperDesign.getTitle();
		JRDesignTextField titleField = (JRDesignTextField) title
				.getElementByKey("commonTextField");
		// 设置title值
		titleField.setExpression(new JRDesignExpression("'" + titleText + "'"));
		titleField.setX(pageWidth / 2);
		// 获取page header区域
		// JRDesignBand pageHeader = (JRDesignBand)
		// jasperDesign.getPageHeader();

		// 获取column header
		JRDesignBand columnHeader = (JRDesignBand) jasperDesign
				.getColumnHeader();
		// 获取列detail
		JRSection detail = jasperDesign.getDetailSection();

		// JRDesignBand detail = jasperDesign.getdet

		JRDesignTextField tempField = null;
		for (Column column : columns) {
			int columnWidth = column.getWidth();
			// 设置报表字段
			JRDesignField field = new JRDesignField();
			field.setName(column.getKey());
			field.setValueClass(java.lang.String.class);
			jasperDesign.addField(field);
			// 设置结束
			tempField = (JRDesignTextField) jasperDesign.getTitle()
					.getElementByKey("commonTextField").clone();
			tempField.setBlankWhenNull(true);
			tempField.setStretchWithOverflow(true);
			tempField.setPrintRepeatedValues(true);
			tempField
					.setStretchType(StretchTypeEnum.RELATIVE_TO_TALLEST_OBJECT);
			tempField.setX(startX);
			tempField.setExpression(new JRDesignExpression("'"
					+ column.getName() + "'"));
			tempField.setWidth(columnWidth);
			tempField.setHeight(20);
			// tempField.setFontSize(14.0f);
			tempField.setStyle(new JRBaseStyle("table_TH"));
			tempField.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
			tempField.setVerticalAlignment(VerticalAlignEnum.MIDDLE);
			tempField.setPdfEncoding("UniGB-UCS2-H");
			tempField.setPdfFontName("STSong-Light");
			tempField.setPdfEmbedded(true);
			tempField.setBackcolor(new Color(230, 230, 230));
			columnHeader.addElement(tempField);

			JRDesignBand detailBand = (JRDesignBand) detail.getBands()[0];
			tempField = new JRDesignTextField();
			tempField.setBlankWhenNull(true);
			tempField.setStretchWithOverflow(true);
			tempField.setPrintRepeatedValues(true);
			tempField
					.setStretchType(StretchTypeEnum.RELATIVE_TO_TALLEST_OBJECT);
			tempField.setX(startX);
			tempField.setHeight(20);

			tempField.setExpression(new JRDesignExpression("$F{"
					+ column.getKey() + "}"));
			tempField.setWidth(columnWidth);
			tempField.setStyle(new JRBaseStyle("table_TD"));
			tempField.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
			tempField.setVerticalAlignment(VerticalAlignEnum.MIDDLE);
			tempField.setPdfEncoding("UniGB-UCS2-H");
			tempField.setPdfFontName("STSong-Light");
			// tempField.setFontSize(14.0f);
			tempField.setPdfEmbedded(true);
			detailBand.addElement(tempField);
			startX += columnWidth;
		}

		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperDesign);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (!PDF_TYPE.equals(exportType)) {
			// 不是pdf，忽略分页
			paramMap.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
		}
		// DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		// Connection con =
		// DriverManager.getConnection("jdbc:mysql://localhost:3306/hz_test?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf-8&useFastDateParsing=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull",
		// "root", "root");
		java.sql.Driver driver = (Driver) Class.forName(
				PropertiesConfigurer.getValue("jdbc.driver"))
				.newInstance();
		DriverManager.registerDriver(driver);
		Connection con = DriverManager.getConnection(
				PropertiesConfigurer.getValue("jdbc.url"),
				PropertiesConfigurer.getValue("jdbc.username"),
				PropertiesConfigurer.getValue("jdbc.password"));
		PreparedStatement statement = con.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		JRDataSource jrDataSource = new JRResultSetDataSource(rs);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				paramMap, jrDataSource);
		return jasperPrint;
	}

	public static void main(String[] args) {
		Document doc = null;
		try {
			// SAXReader就是一个管道，用一个流的方式，把xml文件读出来
			SAXReader reader = new SAXReader(); // User.hbm.xml表示你要解析的xml文档
			doc = reader.read(new File(
					"F:/svn/CZW/CZW_PRO/report/template/exsampleTemp.xml"));
			// 下面的是通过解析xml字符串的
			// doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElement = doc.getRootElement(); // 获取根节点
			// 获取配置类型
			String type = rootElement.attributeValue("type");

			if ("highcharts".equals(type)) {
			} else if ("ireport".equals(type)) {
				Element ireportElement = rootElement.element("ireport");
				handleIReport(ireportElement, null, null);
			}
		} catch (Exception e) {
			log.error("配置文件加载有误", e);
		}
	}

}
