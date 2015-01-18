/*
 * Project Name: CZW_PRO
 * File Name: HCPlotOptions.java
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
 * @since 2014-12-26 下午5:04:57
 */
public class HCPlotOptions
{
	private Pie pie;
	
	private Line line;

	public HCPlotOptions()
	{
		super();
	}
	
	public HCPlotOptions(String type)
	{
		super();
		if ("pie".equals(type))
		{
			pie = new Pie();
		}else if ("line".equals(type))
		{
			line = new Line();
		}
	}

	public Pie getPie()
	{
		return pie;
	}

	public void setPie(Pie pie)
	{
		this.pie = pie;
	}

	public Line getLine()
	{
		return line;
	}

	public void setLine(Line line)
	{
		this.line = line;
	}
	
}

class Line {
	private DataLabels dataLabels;
	private boolean enableMouseTracking = false;
	public Line()
	{
		super();
		dataLabels = new DataLabels();
		dataLabels.setEnabled(true);
	}
	public DataLabels getDataLabels()
	{
		return dataLabels;
	}
	public void setDataLabels(DataLabels dataLabels)
	{
		this.dataLabels = dataLabels;
	}
	public boolean isEnableMouseTracking()
	{
		return enableMouseTracking;
	}
	public void setEnableMouseTracking(boolean enableMouseTracking)
	{
		this.enableMouseTracking = enableMouseTracking;
	}
	
	
}

class Pie {
	private boolean allowPointSelect = true;
    private String cursor = "pointer";
    private DataLabels dataLabels = new DataLabels();
	public Pie()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean isAllowPointSelect()
	{
		return allowPointSelect;
	}
	public void setAllowPointSelect(boolean allowPointSelect)
	{
		this.allowPointSelect = allowPointSelect;
	}
	public String getCursor()
	{
		return cursor;
	}
	public void setCursor(String cursor)
	{
		this.cursor = cursor;
	}
	public DataLabels getDataLabels()
	{
		return dataLabels;
	}
	public void setDataLabels(DataLabels dataLabels)
	{
		this.dataLabels = dataLabels;
	}
    
}
class DataLabels {
	private boolean enabled = true;
    private String color =  "#000000";
    private String connectorColor = "#000000";
	private String format = "{point.name}: {point.percentage:.1f} %";
	public DataLabels()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean isEnabled()
	{
		return enabled;
	}
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
	public String getColor()
	{
		return color;
	}
	public void setColor(String color)
	{
		this.color = color;
	}
	public String getConnectorColor()
	{
		return connectorColor;
	}
	public void setConnectorColor(String connectorColor)
	{
		this.connectorColor = connectorColor;
	}
	public String getFormat()
	{
		return format;
	}
	public void setFormat(String format)
	{
		this.format = format;
	}
    
}
