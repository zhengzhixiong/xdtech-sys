package com.xdtech.sys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xdtech.core.model.BaseModel;

@Entity
@Table(name="SYS_MENU_FUNCTION")
public class Operation extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "MENU_FUNCTION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Column(name="ICON_URL")
	private String iconUrl;			    //功能按钮的图片路径
	@Column(name="ICON_NAME")
	private String iconName;			//功能图标名称
	@Column(name="LABEL_NAME")
	private String labelName;           //显示名称
	@Column(name="OPERATION_CODE")
	private String operationCode;       //按钮编码
	@Column(name="DESCRIPTION")
	private String description;			//按钮描述
	@Column(name="TYPE")
	private int type;				    //1按钮
	
	@ManyToOne
    @JoinColumn(name = "OPER_MENU_ID")
    private MenuFunction       menuFunction;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MenuFunction getMenuFunction() {
		return menuFunction;
	}
	public void setMenuFunction(MenuFunction menuFunction) {
		this.menuFunction = menuFunction;
	}
	public String getIconName() {
		return iconName;
	}
	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
		Operation other = (Operation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
