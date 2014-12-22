package com.xdtech.show.model;

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

/**
 * 
 * @author max.zheng
 * @create 2014-12-06 20:53:58
 * @since 1.0
 * @see 
 */
@Entity
@Table(name="SHOW_ARTICLE")
public class Article extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="TITLE")
	private String title;
	@Column(name="CONTENT")
	private String content;
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
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	
}
