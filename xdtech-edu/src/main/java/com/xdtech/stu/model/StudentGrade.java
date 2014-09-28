package com.xdtech.stu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xdtech.base.model.Grade;
import com.xdtech.core.model.BaseModel;
@Entity
@Table(name="STU_STUDENT_GRADE")
public class StudentGrade extends BaseModel implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "STUDENT_GRADE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Column(name="IS_CURRENT_IN")
	private boolean isCurrentIn;  //是否学员当前所在班级
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GRADE_ID")
	private Grade grade;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STUDENT_ID")
	private Student student;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isCurrentIn() {
		return isCurrentIn;
	}
	public void setCurrentIn(boolean isCurrentIn) {
		this.isCurrentIn = isCurrentIn;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
	
	
}
