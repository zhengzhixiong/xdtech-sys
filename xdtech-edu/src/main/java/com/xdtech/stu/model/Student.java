package com.xdtech.stu.model;

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
@Entity
@Table(name="STU_STUDENT")
public class Student extends BaseModel{
	@Id
    @Column(name = "STUDENT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Column(name="NO")
	private String no;    //学生编号
	

	@Column(name="LOGIN_NAME")
	private String loginName;			//用户登录名
	@Column(name="PASSWORD")
	private String password;            //登录密码
	@Column(name="NAME")
	private String name;			//姓名
	@Column(name="SEX")
	private String sex;					//性别
	
	
	@OneToMany(fetch=FetchType.LAZY,
 		   mappedBy="student")
	private List<Score> scores = new ArrayList<Score>();
	
	@OneToMany(fetch=FetchType.LAZY,
	 		   mappedBy="student")
	private List<StudentGrade> studentGrades = new ArrayList<StudentGrade>();


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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public List<Score> getScores() {
		return scores;
	}


	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
	public List<StudentGrade> getStudentGrades() {
		return studentGrades;
	}
	public void setStudentGrades(List<StudentGrade> studentGrades) {
		this.studentGrades = studentGrades;
	}
	
	
	
	
	
	
	
	
}

