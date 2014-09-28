package com.xdtech.sys.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.xdtech.core.model.BaseModel;

@Entity
@Table(name="SYS_USER")
@XmlRootElement
public class User extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Column(name="LOGIN_NAME")
	private String loginName;			//用户登录名
	@Column(name="PASSWORD")
	private String password;            //登录密码
	@Column(name="SEX")
	private String sex;					//性别
	@Column(name="ORDER_INDEX")
	private Integer orderIndex;			//排序索引
	@Column(name="NAME")
	private String name;			//姓名
	
	
	
	
	
	
	/************关联关系********************/
	//关联角色
	@ManyToMany(
			fetch = FetchType.LAZY,
			targetEntity = com.xdtech.sys.model.Role.class
			)
	@JoinTable(
			name = "SYS_USER_ROLE",
			joinColumns={@JoinColumn(
                    name="USER_ID",
                    referencedColumnName="USER_ID"
                    )},
            inverseJoinColumns={@JoinColumn(
                            name="ROLE_ID",
                            referencedColumnName = "ROLE_ID")}
			)
	private List<Role> roles = new ArrayList<Role>();
	@ManyToMany(
			fetch = FetchType.LAZY,
			targetEntity = com.xdtech.sys.model.UserGroup.class
			)
	@JoinTable(
			name = "SYS_USER_USERGROUP",
			joinColumns={@JoinColumn(
                    name="USER_ID",
                    referencedColumnName="USER_ID"
                    )},
            inverseJoinColumns={@JoinColumn(
                            name="USERGROUP_ID",
                            referencedColumnName = "USERGROUP_ID")}
			)
	private List<UserGroup> userGroups = new ArrayList<UserGroup>();
	
	
	public String getLoginName() {
		return loginName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}
	
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public List<UserGroup> getUserGroups() {
		return userGroups;
	}
	public void setUserGroups(List<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
