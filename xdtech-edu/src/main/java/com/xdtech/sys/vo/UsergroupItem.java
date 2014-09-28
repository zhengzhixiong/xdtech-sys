
/**
 * 学堂教育
 * UsergroupItem.java
 * 2013年12月8日-上午1:36:46
 */

package com.xdtech.sys.vo;


/**
 * 描述
 * @author Zheng Zhi Xiong
 * @create 2013年12月8日-上午1:36:46
 * @version 1.0.0
 */
public class UsergroupItem {
	private Long pGroupId;
	private String pGroupName;
	private Long id;
	private String name;
	private String description;
	public Long getpGroupId() {
		return pGroupId;
	}
	public void setpGroupId(Long pGroupId) {
		this.pGroupId = pGroupId;
	}
	public String getpGroupName() {
		return pGroupName;
	}
	public void setpGroupName(String pGroupName) {
		this.pGroupName = pGroupName;
	}
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
	
	
}
