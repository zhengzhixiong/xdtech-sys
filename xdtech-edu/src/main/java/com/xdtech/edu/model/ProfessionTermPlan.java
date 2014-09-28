package com.xdtech.edu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xdtech.core.model.BaseModel;
@Entity
@Table(name="EDU_PROFESSION_TERM_PLAN")
public class ProfessionTermPlan extends BaseModel{
	@Id
    @Column(name = "PROFESSION_TERM_PLAN_ID")
	private Long id;
	/**
	 * 专业代码
	 */
	@Column(name="PROFESSION_CODE")
	private String professionCode;
	/**
	 * 实际开课学年
	 */
	@Column(name="USER_YEAR")
	private String userYear;
	/**
	 * 实际开课学期 01 02
	 */
	@Column(name="USER_TERM")
	private String userTerm;
	/**
	 * 课程代码
	 */
	@Column(name="SUBJECT_CODE")
	private String subjectCode;
	/**
	 * 描述
	 */
	@Column(name="COMMENT")
	private String comment;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProfessionCode() {
		return professionCode;
	}
	public void setProfessionCode(String professionCode) {
		this.professionCode = professionCode;
	}
	public String getUserYear() {
		return userYear;
	}
	public void setUserYear(String userYear) {
		this.userYear = userYear;
	}
	public String getUserTerm() {
		return userTerm;
	}
	public void setUserTerm(String userTerm) {
		this.userTerm = userTerm;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
	
}
