/*
 * Project Name: CZW_PRO
 * File Name: HCSubtitle.java
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
 * @since 2014-12-26 下午1:38:55
 */
public class HCSubtitle
{
	private String text;
	private int x;
	public HCSubtitle()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	public HCSubtitle(String text, int x)
	{
		super();
		this.text = text;
		this.x = x;
	}
	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		this.text = text;
	}
	public int getX()
	{
		return x;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	
}
