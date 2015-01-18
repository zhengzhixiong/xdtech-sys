/*
 * Project Name: CZW_PRO
 * File Name: HCChart.java
 * Copyright: Copyright(C) 1985-2014 ZKTeco Inc. All rights reserved.
 */
package com.xdtech.report.highcharts;

/**
 * TODO 一句话功能简述，请确保和下面的block tags之间保留一行空行
 * <p>
 * TODO 功能详细描述，若不需要请连同上面的p标签一起删除
 * 
 * @author <a href="max.zheng@zkteco.com">郑志雄</>
 * @version TODO 添加版本
 * @see 相关类或方法，不需要请删除此行
 * @since 2014-12-26 下午2:08:38
 */
public class HCChart
{
	private String type;//: 'column';
	private String plotBackgroundColor = null;//: null,
	private String plotBorderWidth = null;//: null,
	private boolean plotShadow = false;//: false

	
	public HCChart()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public HCChart(String type)
	{
		super();
		this.type = type;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getPlotBackgroundColor()
	{
		return plotBackgroundColor;
	}

	public void setPlotBackgroundColor(String plotBackgroundColor)
	{
		this.plotBackgroundColor = plotBackgroundColor;
	}

	public String getPlotBorderWidth()
	{
		return plotBorderWidth;
	}

	public void setPlotBorderWidth(String plotBorderWidth)
	{
		this.plotBorderWidth = plotBorderWidth;
	}

	public boolean isPlotShadow()
	{
		return plotShadow;
	}

	public void setPlotShadow(boolean plotShadow)
	{
		this.plotShadow = plotShadow;
	}
	
}
