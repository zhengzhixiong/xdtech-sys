package com.xdtech.report.vo;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import com.xdtech.web.freemark.item.GridColumn;

/**
 * 
 * @author max.zheng
 * @create 2015-01-14 21:25:22
 * @since 1.0
 * @see
 */
public class ConfigItem implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	@GridColumn(title="报表名称",width=100)
	private String name;
	@GridColumn(title="xml配置",width=100)
	private String xml;
	@GridColumn(title="sql配置",width=100)
	private String configSql;
	

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setXml(String xml) {
		this.xml = xml;
	}
	public String getXml() {
		return xml;
	}
	public String getConfigSql() {
		return configSql;
	}
	public void setConfigSql(String configSql) {
		this.configSql = configSql;
	}
	
	
	
}
