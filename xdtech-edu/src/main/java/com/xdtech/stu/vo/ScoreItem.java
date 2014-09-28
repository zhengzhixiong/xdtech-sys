package com.xdtech.stu.vo;

import com.xdtech.core.model.BaseItem;


public class ScoreItem extends BaseItem{
	private Long id;
	private Long studentId;    //学生id
	private String studentNo;    //学生编号
	private String studentName;  //学生姓名
	
	private Long gradeId;
	private String gradeName;
	private Long subjectId;
	private String subjectName;
	
	private Double point;
	
	
	
	public ScoreItem() {
		super();
		getQueryFields().put("studentId", "studentId");
		getQueryFields().put("studentNo", "studentNo");
		getQueryFields().put("studentName", "studentName");
		getQueryFields().put("point", "point");
		getQueryFields().put("id", "id");
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	
	
	public Double getPoint() {
		return point;
	}
	public void setPoint(Double point) {
		this.point = point;
	}
	
	
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Long getGradeId() {
		return gradeId;
	}
	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	
}
