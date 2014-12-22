package com.xdtech.sys.vo;

import com.xdtech.web.freemark.item.GridColumn;


public class OperationItem {
	
	private Long id;
	
	private String iconUrl;			    //功能按钮的图片路径
	
	private String iconName;			    //功能图标名称
	
	@GridColumn(title="编码",width=150)
	private String operationCode;       //按钮编码
	@GridColumn(title="名称")
	private String labelName;           //显示名称
	@GridColumn(title="描述",width=250)
	private String description;			//按钮描述

	private int type;				   //1按钮
	
	private boolean checked = false;
	//所属菜单id
	private Long menuFunId;

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMenuFunId() {
		return menuFunId;
	}

	public void setMenuFunId(Long menuFunId) {
		this.menuFunId = menuFunId;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
}
