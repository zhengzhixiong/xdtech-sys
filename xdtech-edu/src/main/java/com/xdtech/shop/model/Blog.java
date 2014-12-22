package com.xdtech.shop.model;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xdtech.core.model.BaseModel;
import com.xdtech.show.model.Member;

/**
 * 
 * @author max.zheng
 * @create 2014-12-08 20:19:20
 * @since 1.0
 * @see 
 */
@Entity
@Table(name="SHOP_BLOG")
public class Blog extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="TITLE")
	private String title;
	@Column(name="CONTENT",length=1000)
	private String content;
	@Column(name="STATUS")
	private String status;
	@Column(name="IMAGE")
	private String image;
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	
}
