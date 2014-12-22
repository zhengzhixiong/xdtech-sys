package com.xdtech.shop.vo;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import com.xdtech.core.model.BaseCondition;
import com.xdtech.web.freemark.item.GridColumn;

/**
 * 
 * @author max.zheng
 * @create 2014-12-08 20:19:20
 * @since 1.0
 * @see
 */
public class BlogItem extends BaseCondition implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	@GridColumn(title="标题",width=200)
	private String title;
	@GridColumn(title="内容",width=300)
	private String content;
	@GridColumn(title="状态",width=80,formatter={"-1=<span style=\"color:red;\">审核失败</span>","0=<span style=\"color:orange;\">待审核</span>","1=<span style=\"color:green;\">审核通过</span>"})
	private String status;
//	@GridColumn(title="图片",width=200)
	private String image;
	
	private Long memberId;

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	
	public void setTitle(String title) {
		this.title = title;
		addToMap("title", title);
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
		addToMap("status", status);
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
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
}
