/*
 * Project Name: CZW_PRO
 * File Name: HighChartTempLoader.java
 * Copyright: Copyright(C) 1985-2015 ZKTeco Inc. All rights reserved.
 */
package com.xdtech.report.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Element;

import com.xdtech.common.service.impl.BaseService;
import com.xdtech.common.utils.ApplicationContextUtil;
import com.xdtech.common.utils.DateUtil;
import com.xdtech.common.utils.DateUtil.DateStyle;

/**
 * highcharts模板加载解析
 * 
 * @author <a href="max.zheng@zkteco.com">郑志雄</>
 * @version TODO 添加版本
 * @see 相关类或方法，不需要请删除此行
 * @since 2015-1-5 下午4:14:43
 */
public class HighChartTempLoader {
	private static Logger log = Logger.getLogger(ReportTemplateLoader.class);
	private static BaseService baseService = ApplicationContextUtil.getContext().getBean(BaseService.class);
	// 携带x轴传参Map
	private static Map<String, String> categoriesParamsMap = new HashMap<String, String>();

	/**
	 * highcharts配置相关解析
	 * 
	 * @author <a href="max.zheng@zkteco.com">郑志雄</>
	 * @since 2015-1-5 上午10:55:15
	 * @param highChartsElement
	 * @return
	 */
	public static String handleHighCharts(Element highChartsElement) {
		Iterator iter = highChartsElement.elementIterator(); // 获取根节点下的子节点head
		// 遍历highcharts节点
		JSONObject jsonObject = new JSONObject();
		while (iter.hasNext()) {
			Element node = (Element) iter.next();
			String nodeName = node.getName();
			if ("chart".equals(nodeName)) {
				createChart(node, jsonObject);
				continue;
			} else if ("title".equals(nodeName)) {
				createTitle(node, jsonObject);
				continue;
			} else if ("exporting".equals(nodeName)) {
				createExporting(node, jsonObject);
				continue;
			} else if ("credits".equals(nodeName)) {
				createCredits(node, jsonObject);
				continue;
			} else if ("tooltip".equals(nodeName)) {
				createTooltip(node, jsonObject);
				continue;
			} else if ("plotOptions".equals(nodeName)) {
				createPlotOptions(node, jsonObject);
				continue;
			} else if ("xAxis".equals(nodeName)) {
				createXAxis(node, jsonObject);
				continue;
			} else if ("yAxis".equals(nodeName)) {
				createYAxis(node, jsonObject);
				continue;
			} else if ("series".equals(nodeName)) {
				createSeries(node, jsonObject);
				continue;
			}
		}
		log.info("图表json数据：" + jsonObject.toString());
		return jsonObject.toString();
	}

	/**
	 * 
	 * @author <a href="max.zheng@zkteco.com">郑志雄</>
	 * @since 2015-1-4 下午2:07:05
	 * @param node
	 * @param jsonObject
	 */
	private static void createPlotOptions(Element node, JSONObject jsonObject) {
		JSONObject plotOptionsJson = createNodeToJson(node);
		jsonObject.put(node.getName(),
				plotOptionsJson.getJSONObject(node.getName()));
	}

	/**
	 * 将每个节点转成对应json数据格式
	 * 
	 * @author <a href="max.zheng@zkteco.com">郑志雄</>
	 * @since 2015-1-5 上午9:19:10
	 * @param node
	 * @return
	 */
	private static JSONObject createNodeToJson(Element node) {
		Iterator iter = node.elementIterator();
		JSONObject rsJson = new JSONObject();
		JSONObject subJson = new JSONObject();
		while (iter.hasNext()) {
			Element subNode = (Element) iter.next();
			if (subNode.elements().size() == 0) {
				subJson.put(subNode.getName(),
						changeTextToObj(subNode.getTextTrim()));
			} else {
				subJson.put(subNode.getName(), createNodeToJson(subNode)
						.getJSONObject(subNode.getName()));
			}
		}
		rsJson.put(node.getName(), subJson);
		return rsJson;
	}

	/**
	 * toolTip解析
	 * 
	 * @author <a href="max.zheng@zkteco.com">郑志雄</>
	 * @since 2015-1-4 下午2:07:03
	 * @param node
	 * @param jsonObject
	 */
	private static void createTooltip(Element node, JSONObject jsonObject) {
		JSONObject tooltipJson = createNodeToJson(node);
		jsonObject.put(node.getName(),
				tooltipJson.getJSONObject(node.getName()));
	}

	/**
	 * 底部签名credits解析
	 * 
	 * @author <a href="max.zheng@zkteco.com">郑志雄</>
	 * @since 2015-1-4 下午1:41:19
	 * @param node
	 * @param jsonObject
	 */
	private static void createCredits(Element node, JSONObject jsonObject) {
		JSONObject creditsJson = createNodeToJson(node);
		jsonObject.put(node.getName(),
				creditsJson.getJSONObject(node.getName()));
	}

	/**
	 * 过滤xml配置的值，对应值转成对应java类型
	 * 
	 * @author <a href="max.zheng@zkteco.com">郑志雄</>
	 * @since 2015-1-5 上午9:18:01
	 * @param text
	 * @return
	 */
	private static Object changeTextToObj(String text) {
		if ("true".equals(text) || "false".equals(text)) {
			return Boolean.valueOf(text);
		} else if (StringUtils.isNotEmpty(text) && StringUtils.isNumeric(text)) {
			return Long.valueOf(text);
		} else {
			return text;
		}
	}

	/**
	 * 解析导出按钮配置
	 * 
	 * @author <a href="max.zheng@zkteco.com">郑志雄</>
	 * @since 2014-12-31 下午2:46:21
	 * @param node
	 * @param jsonObject
	 */
	private static void createExporting(Element node, JSONObject jsonObject) {
		JSONObject exportingJson = createNodeToJson(node);
		jsonObject.put(node.getName(),
				exportingJson.getJSONObject(node.getName()));
	}

	/**
	 * 解析数据源配置信息
	 * 
	 * @author max.zheng
	 * @create 2014-12-30下午10:01:46
	 * @modified by
	 * @param node
	 * @param jsonObject
	 */
	private static void createSeries(Element node, JSONObject jsonObject) {
		JSONArray seriesArray = new JSONArray();
		Element serieNode = node.element("serie");
		String type = serieNode.attributeValue("type");
		// sql取值的时候，是否产生x轴值，扩展sql一步统计功能；
		String xAxis = serieNode.attributeValue("xAxis");
		if (null != serieNode && "sql".equals(type)) {
			JSONObject serieJson = new JSONObject();
			String sql = serieNode.elementTextTrim("sql");
			// 数据
			JSONArray dataArray = new JSONArray();
			if (null != xAxis && "true".equals(xAxis)) {
				// 清除x轴值，重新创建
				jsonObject.remove("xAxis");
				JSONObject categoriesJson = new JSONObject();
				JSONArray categoryArray = new JSONArray();
				// 根据sql设置x轴信息，目前只能支持两列查询
				List<Object[]> rs = (List<Object[]>) baseService.executeSql(sql);
				for (Object[] obj : rs) {
					JSONArray dataJsonArray = new JSONArray();
					dataJsonArray.add(obj[1]);
					dataJsonArray.add(obj[0]);
					dataArray.add(dataJsonArray);
					// x轴值添加
					categoryArray.add(obj[1]);
				}
				categoriesJson.put("categories", categoryArray);
				jsonObject.put("xAxis", categoriesJson);
			} else {
				// series标签里没定义新的x轴值的，才把xAxis标签里的值采用，否则覆盖
				if (jsonObject.has("xAxis")) {
					// 在xml里配置的xAxis标签必须在series标签之前，在执行值统计时候，有配置xAxis，就被作为x轴值
					JSONObject categoriesJson = jsonObject
							.getJSONObject("xAxis");
					JSONArray categoryArray = categoriesJson
							.getJSONArray("categories");
					for (Object object : categoryArray) {
						String excuteSql = "";
						if (null != categoriesParamsMap.get(object)) {
							String params = categoriesParamsMap.get(object);
							excuteSql = String.format(sql, params.split("#"));
						} else {
							excuteSql = sql;
						}
						List t1 = (List) baseService.executeSql(excuteSql);
						JSONArray dataJsonArray = new JSONArray();
						dataJsonArray.add(object);
						dataJsonArray.add(t1.get(0));
						dataArray.add(dataJsonArray);
					}

				}
			}

			if ("pie".equals(serieNode.elementTextTrim("type"))) {
				// 饼状图去除，x轴
				jsonObject.remove("xAxis");
				serieJson.put("type", "pie");
			}
			serieJson.put("data", dataArray);
			Element nameElement = serieNode.element("name");
			serieJson.put(nameElement.getName(), nameElement.getTextTrim());
			seriesArray.add(serieJson);
		} else {
			Iterator<Element> iter = node.elementIterator();
			while (iter.hasNext()) {
				JSONObject serieJson = new JSONObject();
				Element element = (Element) iter.next();
				Element nameElement = element.element("name");
				serieJson.put(nameElement.getName(), nameElement.getTextTrim());
				Element dataElement = element.element("data");
				serieJson.put("data", createArray(dataElement));
				seriesArray.add(serieJson);
			}
		}
		jsonObject.put(node.getName(), seriesArray);
	}

	/**
	 * 
	 * @author <a href="max.zheng@zkteco.com">郑志雄</>
	 * @since 2014-12-31 上午9:58:48
	 * @param dataElement
	 * @return
	 */
	private static JSONArray createArray(Element dataElement) {
		Iterator<Element> iter = dataElement.elementIterator();
		JSONArray dataArray = new JSONArray();
		while (iter.hasNext()) {
			Element element = (Element) iter.next();
			dataArray.add(Double.valueOf(element.getTextTrim()));
		}
		return dataArray;
	}

	/**
	 * 解析Y轴配置信息
	 * 
	 * @author max.zheng
	 * @create 2014-12-30下午10:01:31
	 * @modified by
	 * @param node
	 * @param jsonObject
	 */
	private static void createYAxis(Element node, JSONObject jsonObject) {
		JSONObject yaxisJson = new JSONObject();
		Iterator<Element> iter = node.elementIterator();
		while (iter.hasNext()) {
			Element element = (Element) iter.next();
			String nodeName = element.getName();
			if ("title".equals(nodeName)) {
				createTitle(element, yaxisJson);
				continue;
			}
		}

		jsonObject.put(node.getName(), yaxisJson);
	}

	/**
	 * 解析X轴配置信息
	 * 
	 * @author max.zheng
	 * @create 2014-12-30下午10:01:01
	 * @modified by
	 * @param node
	 * @param jsonObject
	 */
	private static void createXAxis(Element node, JSONObject jsonObject) {
		JSONObject categoriesJson = new JSONObject();
		JSONArray categoryArray = new JSONArray();
		Element categoriesNode = node.element("categories");
		String type = categoriesNode.attributeValue("type");
		if (null != categoriesNode && "sql".equals(type)) {
			String sql = categoriesNode.elementTextTrim("sql");
			List<String> ct = (List) baseService.executeSql(sql);
			for (String ob : ct) {
				categoryArray.add(ob);
				categoriesParamsMap.put(ob, ob);
			}
		} else {
			Iterator<Element> iter = categoriesNode.elementIterator();
			while (iter.hasNext()) {
				Element categoryNode = iter.next();
				// System.out.println(categoryNode.getName()+"="+categoryNode.getTextTrim());
				categoryArray.add(categoryNode.getTextTrim());
				String params = categoryNode.attributeValue("params");
				if (null != params) {
					params = params.replaceAll("date", DateUtil.dateToString(
							new Date(), DateStyle.YYYY_MM_DD));
					categoriesParamsMap.put(categoryNode.getTextTrim(), params);
				}

			}
		}
		categoriesJson.put(categoriesNode.getName(), categoryArray);
		jsonObject.put(node.getName(), categoriesJson);
	}

	/**
	 * 解析标题配置信息
	 * 
	 * @author max.zheng
	 * @create 2014-12-30下午9:59:51
	 * @modified by
	 * @param node
	 * @param jsonObject
	 */
	private static void createTitle(Element node, JSONObject jsonObject) {
		JSONObject titleJson = createNodeToJson(node);
		jsonObject.put(node.getName(), titleJson.getJSONObject(node.getName()));
	}

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-30下午9:49:56
	 * @modified by
	 * @param node
	 * @param jsonObject
	 */
	private static void createChart(Element node, JSONObject jsonObject) {
		JSONObject chartJson = createNodeToJson(node);
		jsonObject.put(node.getName(), chartJson.getJSONObject(node.getName()));
	}
}
