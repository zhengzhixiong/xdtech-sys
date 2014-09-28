package com.xdtech.sys.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.xdtech.core.model.BaseModel;
@Entity
@Table(name="SYS_LOG")
public class Log extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "LOG_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	@Column(name = "OPERATR")
	private String operator;                    //操作人
	@Column(name="ONLY_KEY")
	private String onlyKey;                     //唯一键标记 如是用户就是用户ID，如是ip那就是ip地址
	@Column(name="IP")
	private String ip;                     //唯一键标记 如是用户就是用户ID，如是ip那就是ip地址
	@Column(name = "OPERATE_ACTION")
    private String            operateAction;    //操作动作
	@Column(name = "OPERATE_COMMENT")
    private String            operateComment;   //操作说明
	@Column(name = "REMARKS")
    private String            remarks;          //备注
	@Column(name = "OPERATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
    private Date              operateTime;      //操作时间
	@Column(name="METHOD")
	private String method;                      //具体代码执行位置
	
	public Log() {
		super();
	}
	public Log(String operator, String onlyKey, String operateAction,
			String operateComment, String remarks, Date operateTime) {
		super();
		this.operator = operator;
		this.onlyKey = onlyKey;
		this.operateAction = operateAction;
		this.operateComment = operateComment;
		this.remarks = remarks;
		this.operateTime = operateTime;
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOnlyKey() {
		return onlyKey;
	}
	public void setOnlyKey(String onlyKey) {
		this.onlyKey = onlyKey;
	}
	public String getOperateAction() {
		return operateAction;
	}
	public void setOperateAction(String operateAction) {
		this.operateAction = operateAction;
	}
	public String getOperateComment() {
		return operateComment;
	}
	public void setOperateComment(String operateComment) {
		this.operateComment = operateComment;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	

}
