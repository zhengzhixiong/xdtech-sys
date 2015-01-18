/*
 * Project Name: CZW_PRO
 * File Name: HCExporting.java
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
 * @since 2014-12-26 下午1:45:12
 */
public class HCExporting
{
	private String url;
	private boolean enabled;//:false
	

	public HCExporting()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	public HCExporting(String url,boolean enabled)
	{
		super();
		this.url = url;
		this.enabled = enabled;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
	
}
