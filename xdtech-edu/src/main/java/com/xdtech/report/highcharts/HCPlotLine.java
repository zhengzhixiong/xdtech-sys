/*
 * Project Name: CZW_PRO
 * File Name: HCPlotLine.java
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
 * @since 2014-12-26 下午1:59:02
 */
public class HCPlotLine
{
	private int value;
	private int  width;
    private String color;
	public HCPlotLine()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	public HCPlotLine(int value, int width, String color)
	{
		super();
		this.value = value;
		this.width = width;
		this.color = color;
	}
	public int getValue()
	{
		return value;
	}
	public void setValue(int value)
	{
		this.value = value;
	}
	public int getWidth()
	{
		return width;
	}
	public void setWidth(int width)
	{
		this.width = width;
	}
	public String getColor()
	{
		return color;
	}
	public void setColor(String color)
	{
		this.color = color;
	}
    
}
