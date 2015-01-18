/*
 * Project Name: CZW_PRO
 * File Name: HCSeries.java
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
 * @since 2014-12-26 下午2:06:12
 */
public class HCSeries
{
	private String type;
	private String name;
	private List<Object> data = new ArrayList<Object>();
	public HCSeries()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	public HCSeries(String name, List<Object> data)
	{
		super();
		this.name = name;
		this.data = data;
	}

	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public List<Object> getData()
	{
		return data;
	}
	public void setData(List<Object> data)
	{
		this.data = data;
	}
	
}
