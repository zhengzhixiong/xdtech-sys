package com.xdtech.sys.vo;

import java.util.Date;

import com.xdtech.web.freemark.item.GridColumn;

public class LogItem {
	private Long id;
	@GridColumn(title="创建时间")
	private Date createTime;
	@GridColumn(title="操作人")
	private String operator; // 操作人
	private String onlyKey; // 唯一键标记 如是用户就是用户ID，如是ip那就是ip地址
	@GridColumn(title="IP地址")
	private String ip; // 唯一键标记 如是用户就是用户ID，如是ip那就是ip地址
	@GridColumn(title="操作动作")
	private String operateAction; // 操作动作
	@GridColumn(title="操作说明")
	private String operateComment; // 操作说明
	
	private String remarks; // 备注
	@GridColumn(title="操作时间")
	private Date operateTime; // 操作时间
	@GridColumn(title="执行方法")
	private String method; // 具体代码执行位置
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
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
	
	

}


