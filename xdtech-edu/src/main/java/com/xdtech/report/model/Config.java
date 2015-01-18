package com.xdtech.report.model;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.xdtech.core.model.BaseModel;

/**
 * 
 * @author max.zheng
 * @create 2015-01-14 21:25:22
 * @since 1.0
 * @see 
 */
@Entity
@Table(name="REPORT_CONFIG")
public class Config extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="NAME")
	private String name;
	@Column(name="XML")
	@Lob
	private String xml;
	@Column(name="CONFIG_SQL")
	@Lob
	private String configSql;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	public String getConfigSql() {
		return configSql;
	}
	public void setConfigSql(String configSql) {
		this.configSql = configSql;
	}
	
	

	
}
