package com.xdtech.shop.vo;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import com.xdtech.core.model.BaseCondition;
import com.xdtech.web.freemark.item.GridColumn;

/**
 * 
 * @author max.zheng
 * @create 2014-12-08 20:33:28
 * @since 1.0
 * @see
 */
public class OrderItem extends BaseCondition implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	@GridColumn(title="订单编号",width=130)
	private String orderNo;
//	@GridColumn(title="总计金额",width=100)
	private Integer amount;
//	@GridColumn(title="总数量",width=100)
	private Integer count;
	@GridColumn(title="状态",width=80,formatter={"-1=<span style=\"color:red;\">无效</span>","0=<span style=\"color:orange;\">待审核</span>","1=<span style=\"color:green;\">审核通过</span>"})
	private String status;
	@GridColumn(title="姓名")
	private String name;
	@GridColumn(title="手机")
	private String phone;
	@GridColumn(title="邮箱",width=120)
	private String email;
	@GridColumn(title="大小",width=50)
	private int size;
	@GridColumn(title="刺绣占比",width=50)
	private String embroidery;
	@GridColumn(title="数量",width=50)
	private int quantity;
	@GridColumn(title="下单时间",width=120)
	private Date createTime;
	@GridColumn(title="预定日期",width=120)
	private Date dateNeeded;
//	@GridColumn(title="图片")
	private String image;
	@GridColumn(title="留言")
	private String notes;
	@GridColumn(title="备注",width=100)
	private String remark;
	
	private String statusName;

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
		addToMap("orderNo",orderNo);
	}
	public String getOrderNo() {
		return orderNo;
	}
	
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getAmount() {
		return amount;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getCount() {
		return count;
	}
	
	public void setStatus(String status) {
		this.status = status;
		addToMap("status",status);
	}
	public String getStatus() {
		return status;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return remark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		addToMap("name",name);
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
		addToMap("phone",phone);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
		addToMap("email",email);
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getEmbroidery() {
		return embroidery;
	}
	public void setEmbroidery(String embroidery) {
		this.embroidery = embroidery;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getDateNeeded() {
		return dateNeeded;
	}
	public void setDateNeeded(Date dateNeeded) {
		this.dateNeeded = dateNeeded;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getStatusName() {
		if ("0".equals(status)) {
			this.statusName = "待审核";
		}else if ("1".equals(status)) {
			this.statusName = "审核通过";
		}else if ("-1".equals(status)) {
			this.statusName = "无效";
		}else {
			this.statusName = "";
		}
		return statusName;
	}
}
