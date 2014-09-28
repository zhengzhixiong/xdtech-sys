
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

import com.xdtech.core.annotation.Comment;
import com.xdtech.core.model.BaseModel;


@Entity
@Table(name="SYS_ROLE")
public class Role extends BaseModel implements Serializable{
	private static final long serialVersionUID = 2735526530277515853L;
	@Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	@Column(name="CODE")
	private String code;                    //角色编码
	@Column(name="NAME")
	private String name;					//角色名称
	@Column(name="DESCRIPTION")
	private String description;				//角色描述
	@Column(name="ENABLE")
	private boolean enable = true;                 //是否可用
	@Column(name="IS_SUPERUSER")
	@Comment("是否超级管理员角色")
	private boolean isSuperuser = false;        	//是否超级管理员角色
	
	
	
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Role(Long id) {
		this.id = id;
	}


	/************关联关系********************/
	
	@ManyToMany
	@JoinTable(name="SYS_USER_ROLE", 
			   joinColumns={@JoinColumn(name="ROLE_ID",
			                            referencedColumnName="ROLE_ID")},
			   inverseJoinColumns={@JoinColumn(name="USER_ID",
                                    referencedColumnName="USER_ID")}
    )
//	@OrderBy("orderIndex asc")
	private List<User> users = new ArrayList<User>();
	
	@ManyToMany(
			fetch = FetchType.LAZY,
			targetEntity = com.xdtech.sys.model.UserGroup.class
			)
    @JoinTable(name="SYS_USERGROUP_ROLE", joinColumns={
        @JoinColumn(name="ROLE_ID",referencedColumnName="ROLE_ID")
    }, inverseJoinColumns={
        @JoinColumn(name="USERGROUP_ID",referencedColumnName="USERGROUP_ID")
    })
//    @OrderBy("orderIndex asc")
    private List<UserGroup> userGroups = new ArrayList<UserGroup>();
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	


	public boolean isSuperuser() {
		return isSuperuser;
	}


	public void setSuperuser(boolean isSuperuser) {
		this.isSuperuser = isSuperuser;
	}


	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
//	public List<MenuFunction> getMenuFunctions() {
//		return menuFunctions;
//	}
//	public void setMenuFunctions(List<MenuFunction> menuFunctions) {
//		this.menuFunctions = menuFunctions;
//	}
	public List<UserGroup> getUserGroups() {
		return userGroups;
	}
	public void setUserGroups(List<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}
//	public List<RolePermission> getRolePermissions() {
//		return rolePermissions;
//	}
//	public void setRolePermissions(List<RolePermission> rolePermissions) {
//		this.rolePermissions = rolePermissions;
//	}
}
