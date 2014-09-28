package com.xdtech.stu.vo;

import java.util.List;


public class StudentItem {
	private Long id;
	private String no;    //学生编号
	private String name; //学生姓名
	private List<Long> grade;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Long> getGrade() {
		return grade;
	}
	public void setGrade(List<Long> grade) {
		this.grade = grade;
	}
	
	
	
}
