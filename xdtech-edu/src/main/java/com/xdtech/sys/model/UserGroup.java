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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.xdtech.core.model.BaseModel;

@Entity
@Table(name="SYS_USERGROUP")
public class UserGroup extends BaseModel implements Serializable {
	private static final long serialVersionUID = -6627393698246029558L;
	
	@Id
    @Column(name = "USERGROUP_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	@Column(name="NAME")
	private String name;				//用户组名称
	@Column(name = "DESCRIPTION")
    private String description;			//用户组描述
	@Column(name="ORDER_INDEX")
	private Integer orderIndex;         //组别排序索引
	
	
	/************关联关系********************/
	@ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private UserGroup       parent;
	@OneToMany(mappedBy="parent",orphanRemoval=true,fetch=FetchType.LAZY)  //,fetch=FetchType.EAGER
    @OrderBy("orderIndex asc,name asc")
    private List<UserGroup> children    = new ArrayList<UserGroup>();
	
	
	@ManyToMany
	@JoinTable(name="SYS_USER_USERGROUP", 
			   joinColumns={@JoinColumn(name="USERGROUP_ID",
			                            referencedColumnName="USERGROUP_ID")},
			   inverseJoinColumns={@JoinColumn(name="USER_ID",
                                    referencedColumnName="USER_ID")}
    )
	@OrderBy("orderIndex asc")
	private List<User> users = new ArrayList<User>();
	
	@ManyToMany(
			fetch = FetchType.LAZY,
			targetEntity = com.xdtech.sys.model.Role.class
			)
    @JoinTable(name="SYS_USERGROUP_ROLE",
        joinColumns = @JoinColumn(name = "USERGROUP_ID", referencedColumnName="USERGROUP_ID"),
        inverseJoinColumns=@JoinColumn(name = "ROLE_ID", referencedColumnName="ROLE_ID"))
    private List<Role>  roles = new ArrayList<Role>();

	
	
	public UserGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public UserGroup(Long id) {
		super();
		this.id = id;
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
	public Integer getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}
	public UserGroup getParent() {
		return parent;
	}
	public void setParent(UserGroup parent) {
		this.parent = parent;
	}
	public List<UserGroup> getChildren() {
		return children;
	}
	public void setChildren(List<UserGroup> children) {
		this.children = children;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
}
