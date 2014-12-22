package com.xdtech.shop.vo;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import com.xdtech.web.freemark.item.GridColumn;

/**
 * 
 * @author max.zheng
 * @create 2014-12-08 20:01:36
 * @since 1.0
 * @see
 */
public class CategoryItem implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	@GridColumn(title="类别编码")
	private String code;
	@GridColumn(title="类别名称")
	private String name;
	@GridColumn(title="描述")
	private String description;
	@GridColumn(title="备注")
	private String remark;

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return remark;
	}
	
}
