package com.xdtech.sys.vo;

import java.util.ArrayList;
import java.util.List;

public class UserDetail {
	private Long id;
	
	private String loginName;			//用户登录名
	
	private String password;
	
	
	private Boolean isSuperuser;        //是否超级管理员
	
	private List<Long> usergroupIds = new ArrayList<Long>();
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Boolean getIsSuperuser() {
		return isSuperuser;
	}
	public void setIsSuperuser(Boolean isSuperuser) {
		this.isSuperuser = isSuperuser;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Long> getUsergroupIds() {
		return usergroupIds;
	}
	public void setUsergroupIds(List<Long> usergroupIds) {
		this.usergroupIds = usergroupIds;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
