package com.xdtech.sys.vo;

import com.xdtech.web.freemark.item.GridColumn;

public class RoleItem {
	
	private Long id;
	@GridColumn(title="角色名称")
	private String name;
	@GridColumn(title="是否启用")
	private boolean enable;
	@GridColumn(title="角色编码")
	private String code; 
	@GridColumn(title="角色描述",width=200)
	private String description;
	@GridColumn(title="是否超级管理员")
	private boolean superuser;  
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isSuperuser() {
		return superuser;
	}
	public void setSuperuser(boolean superuser) {
		this.superuser = superuser;
	}
	

	
	

}
