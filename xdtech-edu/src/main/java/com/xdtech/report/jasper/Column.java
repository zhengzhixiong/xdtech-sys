/*
 * Project Name: CZW_PRO
 * File Name: Cloumn.java
 * Copyright: Copyright(C) 1985-2015 ZKTeco Inc. All rights reserved.
 */
package com.xdtech.report.jasper;

/**
 * TODO 一句话功能简述，请确保和下面的block tags之间保留一行空行
 * <p>
 * TODO 功能详细描述，若不需要请连同上面的p标签一起删除
 * 
 * @author <a href="max.zheng@zkteco.com">郑志雄</>
 * @version TODO 添加版本
 * @see 相关类或方法，不需要请删除此行
 * @since 2015-1-5 下午2:00:52
 */
public class Column
{
	private String key;
	private String name;
	private int width;
	
	public Column()
	{
		super();
	}
	
	public Column(String key, String name, int width)
	{
		super();
		this.key = key;
		this.name = name;
		this.width = width;
	}

	public String getKey()
	{
		return key;
	}
	public void setKey(String key)
	{
		this.key = key;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getWidth()
	{
		return width;
	}
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	
}
