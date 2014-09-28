package com.xdtech.stu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xdtech.base.model.Subject;
import com.xdtech.core.model.BaseModel;
@Entity
@Table(name="STU_SCORE")
public class Score extends BaseModel{
	
	@Id
    @Column(name = "SCORE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	/**
	 * 分数
	 */
	@Column(name="POINT")
	private Double point;
	/**
	 * 学年
	 */
	@Column(name="YEAR")
	private String year;
	/**
	 * 学期：01第一学期 02第二学期 03第四学期04第四学期
	 */
	@Column(name="TERM")
	private String term;
	/**
	 * 考试类型：01正常考试 02重修 03再修04免修
	 */
	@Column(name="EXAM_TYPE")
	private String examType;
	/**
	 * 分数类型：01百分制 02五级制 03等级制
	 */
	@Column(name="SCORE_TYPE")
	private String scoreType;
	
	@Column(name="SUBJECT_CODE")
	private String subjectCode;
	@Column(name="SUBJECT_NAME")
	private String subjectName;
	@ManyToOne
	@JoinColumn(name="SUBJECT_ID")
	private Subject subject;
	@Column(name="STUDENT_NO")
	private String studentNo;
	@Column(name="STUDENT_NAME")
	private String studentName;
	@ManyToOne
	@JoinColumn(name="STUDENT_ID")
	private Student student;
	
	
	

	public void setPoint(Double point) {
		this.point = point;
	}
	public Double getPoint() {
		return point;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
		this.subjectCode = subject.getCode();
		this.subjectName = subject.getName();
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
		this.studentName = student.getName();
		this.studentNo = student.getNo();
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	public String getScoreType() {
		return scoreType;
	}
	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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
	
	
	

}
