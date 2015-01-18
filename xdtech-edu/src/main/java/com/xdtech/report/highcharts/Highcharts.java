/*
 * Project Name: CZW_PRO
 * File Name: Highcharts.java
 * Copyright: Copyright(C) 1985-2014 ZKTeco Inc. All rights reserved.
 */
package com.xdtech.report.highcharts;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 一句话功能简述，请确保和下面的block tags之间保留一行空行
 * <p>
 * TODO 功能详细描述，若不需要请连同上面的p标签一起删除
 * 
 * @author <a href="max.zheng@zkteco.com">郑志雄</>
 * @version TODO 添加版本
 * @see 相关类或方法，不需要请删除此行
 * @since 2014-12-26 下午1:35:07
 */
public class Highcharts
{
	private HCChart chart;
	private HCTitle title;
	private HCTooltip tooltip;
	private HCExporting exporting;
	private HCCredits credits;
	private HCXaxis xAxis;
	private HCYaxis yAxis;
	
	private HCPlotOptions plotOptions;
	
	private List<HCSeries> series = new ArrayList<HCSeries>();
	
	public HCChart getChart()
	{
		return chart;
	}
	public void setChart(HCChart chart)
	{
		this.chart = chart;
	}
	public HCTitle getTitle()
	{
		return title;
	}
	public void setTitle(HCTitle title)
	{
		this.title = title;
	}
	public HCTooltip getTooltip()
	{
		return tooltip;
	}
	public void setTooltip(HCTooltip tooltip)
	{
		this.tooltip = tooltip;
	}
	public HCExporting getExporting()
	{
		return exporting;
	}
	public void setExporting(HCExporting exporting)
	{
		this.exporting = exporting;
	}
	public HCCredits getCredits()
	{
		return credits;
	}
	public void setCredits(HCCredits credits)
	{
		this.credits = credits;
	}
	public HCPlotOptions getPlotOptions()
	{
		return plotOptions;
	}
	public void setPlotOptions(HCPlotOptions plotOptions)
	{
		this.plotOptions = plotOptions;
	}
	
	public HCXaxis getxAxis()
	{
		return xAxis;
	}
	public void setxAxis(HCXaxis xAxis)
	{
		this.xAxis = xAxis;
	}
	public List<HCSeries> getSeries()
	{
		return series;
	}
	public void setSeries(List<HCSeries> series)
	{
		this.series = series;
	}
	public HCYaxis getyAxis()
	{
		return yAxis;
	}
	public void setyAxis(HCYaxis yAxis)
	{
		this.yAxis = yAxis;
	}
	
	
	
}
