package com.xdtech.edu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xdtech.core.model.BaseModel;

@Entity
@Table(name="EDU_TEACHER")
public class Teacher extends BaseModel{
	
	@Id
    @Column(name = "TEACHER_ID")
	private Long id;
	
	@Column(name="LOGIN_NAME")
	private String loginName;			//用户登录名
	@Column(name="PASSWORD")
	private String password;            //登录密码
	@Column(name="NAME")
	private String name;			//姓名
	@Column(name="SEX")
	private String sex;					//性别
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	

}
