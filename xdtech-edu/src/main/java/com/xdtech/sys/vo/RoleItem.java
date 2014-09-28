package com.xdtech.sys.vo;

public class RoleItem {
	
	private Long id;
	private String name;
	private boolean enable;
	private String code; 
	private String description;
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
