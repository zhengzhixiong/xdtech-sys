/*
 * Project Name: CZW_PRO
 * File Name: HCYaxis.java
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
 * @since 2014-12-26 下午1:55:55
 */
public class HCYaxis
{
	private HCTitle title;
	private List<HCPlotLine> plotLines = new ArrayList<HCPlotLine>();
	public HCTitle getTitle()
	{
		return title;
	}
	public void setTitle(HCTitle title)
	{
		this.title = title;
	}
	public List<HCPlotLine> getPlotLines()
	{
		return plotLines;
	}
	public void setPlotLines(List<HCPlotLine> plotLines)
	{
		this.plotLines = plotLines;
	}
	
	
}
