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
import javax.persistence.Transient;

import com.xdtech.common.fomat.TreeFormat;
import com.xdtech.core.model.BaseModel;



@Entity
@Table(name="SYS_MENU_FUNCTION")
public class MenuFunction extends BaseModel implements Serializable,TreeFormat<MenuFunction, Long>{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "MENU_FUNCTION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="NAME_CN")
	private String nameCN;				//菜单中文名称
	@Column(name="NAME_EN")
	private String nameEN;				//菜单英文名称
	@Column(name="IMAGE_URL")
	private String imageUrl;			//菜单的图片连接url
	@Column(name="ICON_NAME")
	private String iconName;            //菜单图标名称
	@Column(name="PAGE_URL")
	private String pageUrl;				//菜单连接的页面url
	@Column(name="IFRAME")
	private Boolean iframe = false;     //是否是iframe连接
	@Column(name="DESCRIPTION")
	private String description;			//菜单的描述信息
	@Column(name="ORDER_INDEX")
	private Integer orderIndex;			//菜单排序编号
	@Column(name="ENABLED")
	private Boolean enabled;			//菜单是否可用,0不可用，1可用
	@Column(name="TYPE")
	private int type;				   //菜单类型 	0菜单、1按钮
	@Column(name="OPERATION_CODE")
	private String operationCode;       //按钮编码
	
	
	
	//父菜单
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	private MenuFunction parent;
	
	//子菜单
	@OneToMany(fetch=FetchType.LAZY,
    		   mappedBy="parent")
    @OrderBy("orderIndex asc")
    private List<MenuFunction> childrenMenus = new ArrayList<MenuFunction>(); 
	@Transient
	private List<MenuFunction> children = new ArrayList<MenuFunction>(); 
	
	
	
	//关联角色
	@ManyToMany
	@JoinTable(
			name = "SYS_ROLE_MENU",
			joinColumns={@JoinColumn(
                    name="MENU_ID",
                    referencedColumnName="MENU_FUNCTION_ID"
                    )},
            inverseJoinColumns={@JoinColumn(
                            name="ROLE_ID",
                            referencedColumnName = "ROLE_ID")}
			)
	private List<Role> roles = new ArrayList<Role>();

	@OneToMany(mappedBy="menuFunction")  //,fetch=FetchType.EAGER
//    @OrderBy("orderIndex asc,name asc")
    private List<Operation> operations    = new ArrayList<Operation>();
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getNameCN() {
		return nameCN;
	}

	public void setNameCN(String nameCN) {
		this.nameCN = nameCN;
	}

	public String getNameEN() {
		return nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Boolean getIframe() {
		return iframe;
	}

	public void setIframe(Boolean iframe) {
		this.iframe = iframe;
	}

	public MenuFunction getParent() {
		return parent;
	}

	public void setParent(MenuFunction parent) {
		this.parent = parent;
	}

	

	public List<MenuFunction> getChildren() {
		return children;
	}

	public void setChildren(List<MenuFunction> children) {
		this.children = children;
	}

//	public List<Role> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(List<Role> roles) {
//		this.roles = roles;
//	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}


	public void addChildren(MenuFunction child) {
		if(this.children == null) {
            this.children = new ArrayList<MenuFunction>();
        }
        this.children.add(child);
		
	}
	
	

	public List<MenuFunction> getChildrenMenus() {
		return childrenMenus;
	}

	public void setChildrenMenus(List<MenuFunction> childrenMenus) {
		this.childrenMenus = childrenMenus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuFunction other = (MenuFunction) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
	
	

}
