package com.xdtech.sys.vo;

import java.util.HashSet;
import java.util.Set;

import com.xdtech.core.model.BaseItem;
import com.xdtech.web.freemark.item.GridColumn;

public class UserItem extends BaseItem{
	private Long id;
	@GridColumn(title="登录名")
	private String loginName;			//用户登录名
	@GridColumn(title="姓名")
	private String name;        //姓名
	@GridColumn(title="性别",formatter={"M=男","F=女"})
	private String sex;
	
	private String groupIds;
	
	private Set<Long> usergroupIds = new HashSet<Long>();
	
	public UserItem() {
		super();
		getQueryFields().put("id", "id");
		getQueryFields().put("sex", "sex");
		getQueryFields().put("name", "name");
		getQueryFields().put("loginName", "loginName");
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Set<Long> getUsergroupIds() {
		return usergroupIds;
	}

	public void setUsergroupIds(Set<Long> usergroupIds) {
		this.usergroupIds = usergroupIds;
	}

	public String getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
	
}
