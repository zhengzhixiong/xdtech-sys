package com.xdtech.show.vo;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import com.xdtech.web.freemark.item.GridColumn;

/**
 * 
 * @author max.zheng
 * @create 2014-12-06 20:53:58
 * @since 1.0
 * @see
 */
public class ArticleItem implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	@GridColumn(title="标题")
	private String title;
	@GridColumn(title="内容",width=200)
	private String content;
	@GridColumn(title="发帖人",width=110)
	private String member;
	@GridColumn(title="发帖时间",width=130)
	private Date createTime;

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
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	
}
