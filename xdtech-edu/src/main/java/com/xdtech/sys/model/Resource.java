package com.xdtech.sys.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.xdtech.core.model.BaseModel;
@Entity
@Table(name="SYS_RESOURCE")
public class Resource extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "RESOURCE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Column(name = "RESOURCE_TYPE", nullable = false)
	private Integer type;
	@Column(name = "RESOURCE_KEY", nullable = false)
	private String key;

	@Transient
	private String name;

	@Column(name = "ROLE_ID", nullable = false)
	private Long roleId;

	@Transient
	private List<Resource> children = new ArrayList<Resource>();

	@Transient
	private String parentKey;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}

	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}
	
	

}
