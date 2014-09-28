package com.xdtech.base.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.xdtech.core.model.BaseModel;
import com.xdtech.stu.model.Score;
@Entity
@Table(name="BASE_SUBJECT")
public class Subject extends BaseModel{
	@Id
    @Column(name = "SUBJECT_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "CODE")
	private String code;  //科目编码
	@Column(name = "NAME")
	private String name;  //科目名称
	
	
	@OneToMany(fetch=FetchType.LAZY,
	 		   mappedBy="subject")
	private List<Score> scores = new ArrayList<Score>();
	
	
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
	public List<Score> getScores() {
		return scores;
	}
	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
 
	
	
}
