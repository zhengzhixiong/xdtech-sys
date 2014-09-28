package com.xdtech.sys.searchers;

import org.apache.commons.lang.StringUtils;

import com.xdtech.core.model.BaseCondition;
import com.xdtech.sys.vo.UserItem;

public class UserCondition extends BaseCondition{
	private Long usergroupId;
	private String name;
	
	
	
	public UserCondition() {
		super();
		setBaseItem(new UserItem());
	}
	public Long getUsergroupId() {
		return usergroupId;
	}
	public void setUsergroupId(Long usergroupId) {
		this.usergroupId = usergroupId;
		queryConditions.put("usergroupId", usergroupId.toString());
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		if (StringUtils.isNotEmpty(name)) {
			queryConditions.put("name", name);
		}
		

	}
	
	

}
