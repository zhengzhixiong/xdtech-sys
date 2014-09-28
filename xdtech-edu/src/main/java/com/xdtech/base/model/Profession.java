package com.xdtech.base.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.xdtech.core.model.BaseModel;
@Entity
@Table(name="BASE_PROFESSION")
public class Profession extends BaseModel{
	@Id
    @Column(name = "PROFESSION_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**
	 * 专业代码
	 */
	@Column(name = "CODE")
	private String code;  
	/**
	 * 专业名称
	 */
	@Column(name = "NAME")
	private String name;
	@OneToMany(mappedBy="profession")
	private List<Grade> grades = new ArrayList<Grade>();
	
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
	public List<Grade> getGrades() {
		return grades;
	}
	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}  
	
	

}
