package com.xdtech.stu.conditions;

import com.xdtech.core.model.BaseCondition;

public class StudentCondition extends BaseCondition{
	
	private String name;
	
	private String grade;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		addToMap("name",name);
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
		addToMap("gradeId",grade);
	}
	
	
}
