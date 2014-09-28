package com.xdtech.base.model;

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
@Table(name="BASE_GRADE")
public class Grade extends BaseModel{
	@Id
    @Column(name = "GRADE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "CODE")
	private String code;  //年级编码
	@Column(name = "NAME")
	private String name;  //年级名称
	@ManyToOne
	@JoinColumn(name = "PROFESSION_ID")
	private Profession profession;
	
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
	public Profession getProfession() {
		return profession;
	}
	public void setProfession(Profession profession) {
		this.profession = profession;
	}
	
	
	
	
}
