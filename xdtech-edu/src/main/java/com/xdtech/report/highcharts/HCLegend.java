/*
 * Project Name: CZW_PRO
 * File Name: HCLegend.java
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
 * @since 2014-12-26 下午2:04:22
 */
public class HCLegend
{
	private String layout; //: 'vertical',
	private String align;//: 'right',
	private String verticalAlign;//: 'middle',
	private int borderWidth;//: 0
	public String getLayout()
	{
		return layout;
	}
	public void setLayout(String layout)
	{
		this.layout = layout;
	}
	public String getAlign()
	{
		return align;
	}
	public void setAlign(String align)
	{
		this.align = align;
	}
	public String getVerticalAlign()
	{
		return verticalAlign;
	}
	public void setVerticalAlign(String verticalAlign)
	{
		this.verticalAlign = verticalAlign;
	}
	public int getBorderWidth()
	{
		return borderWidth;
	}
	public void setBorderWidth(int borderWidth)
	{
		this.borderWidth = borderWidth;
	}
	
}
