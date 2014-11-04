package com.xdtech.message.vo;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import com.xdtech.web.freemark.item.GridColumn;

/**
 * 
 * @author max.zheng
 * @create 2014-11-02 00:15:20
 * @since 1.0
 * @see
 */
public class PersonItem implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	@GridColumn(title="姓名")
	private String name;
	@GridColumn(title="生日")
	private String birthday;

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getBirthday() {
		return birthday;
	}
}
