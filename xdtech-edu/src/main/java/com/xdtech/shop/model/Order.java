package com.xdtech.shop.model;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xdtech.core.model.BaseModel;
import com.xdtech.show.model.Member;

/**
 * 
 * @author max.zheng
 * @create 2014-12-08 20:33:28
 * @since 1.0
 * @see 
 */
@Entity
@Table(name="SHOP_ORDER")
public class Order extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="ORDER_NO")
	private String orderNo;
	@Column(name="AMOUNT")
	private Integer amount;
	@Column(name="COUNT")
	private Integer count;
	@Column(name="STATUS")
	private String status;
	
	@Column(name="NAME")
	private String name;
	@Column(name="PHONE")
	private String phone;
	@Column(name="EMAIL")
	private String email;
	@Column(name="SIZE")
	private int size;
	@Column(name="EMBROIDERY")
	private String embroidery;
	@Column(name="QUANTITY")
	private int quantity;
	@Column(name="DATENEEDED")
	private Date dateNeeded;
	@Column(name="IMAGE")
	private String image;
	@Column(name="NOTES")
	private String notes;
	@Column(name="REMARK")
	private String remark;
//	@ManyToOne
//	private Member member;

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
//	public Member getMember() {
//		return member;
//	}
//	public void setMember(Member member) {
//		this.member = member;
//	}
	
	
}
