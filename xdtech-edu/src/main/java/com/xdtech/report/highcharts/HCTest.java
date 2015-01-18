/*
 * Project Name: CZW_PRO
 * File Name: HCTest.java
 * Copyright: Copyright(C) 1985-2014 ZKTeco Inc. All rights reserved.
 */
package com.xdtech.report.highcharts;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

/**
 * TODO 一句话功能简述，请确保和下面的block tags之间保留一行空行
 * <p>
 * TODO 功能详细描述，若不需要请连同上面的p标签一起删除
 * 
 * @author <a href="max.zheng@zkteco.com">郑志雄</>
 * @version TODO 添加版本
 * @see 相关类或方法，不需要请删除此行
 * @since 2014-12-26 下午1:46:37
 */
public class HCTest
{

	/**
	 * 
	 * @author <a href="max.zheng@zkteco.com">郑志雄</>
	 * @since 2014-12-26 下午1:46:37
	 * @param args
	 */
	public static void main(String[] args)
	{
		JSONArray array = new JSONArray();
		array.add("1");
		System.out.println(array.toString());
		
		String[] test = {"1","2"};
		System.out.println(test.toString());
		
		Highcharts highcharts = new Highcharts();
		highcharts.setTitle(new HCTitle("测试", 20));
		
		HCXaxis xaxis = new HCXaxis();
		List<String> categories = new ArrayList<String>();
		categories.add("一月");
		categories.add("二月");
		xaxis.setCategories(categories);
		
//		highcharts.setaXaxis(xaxis);
		
//		System.out.println(GsonUtil.toJson(highcharts));
		

	}

}
