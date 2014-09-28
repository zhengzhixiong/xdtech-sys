package com.xdtech.edu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xdtech.core.model.BaseModel;
@Entity
@Table(name="EDU_PROFESSION_PLAN")
public class ProfessionPlan extends BaseModel{
	@Id
    @Column(name = "PROFESSION_PLAN_ID")
	private Long id;
	
	/**
	 * 专业代码
	 */
	@Column(name="PROFESSION_CODE")
	private String professionCode;
	@Column(name="ENTRY_YEAR")
	private String entryYear;
	/**
	 * 学制
	 */
	@Column(name="PROF_LENGTH")
	private Short profLength;
	/**
	 * 总学时
	 */
	@Column(name="GROSS_PERIOD")
	private Integer grossPeriod;
	/**
	 * 批准时间
	 */
	@Column(name="APPROVE_TIME")
	private Date approveTime;
	/**
	 * 是否批准
	 */
	@Column(name="IS_APPROVE")
	private boolean isApprove;

}
