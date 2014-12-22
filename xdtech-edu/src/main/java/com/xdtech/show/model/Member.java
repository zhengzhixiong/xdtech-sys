package com.xdtech.show.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.xdtech.core.model.BaseModel;
import com.xdtech.shop.model.Blog;
import com.xdtech.shop.model.Order;

/**
 * 
 * @author max.zheng
 * @create 2014-12-06 20:45:01
 * @since 1.0
 * @see 
 */
@Entity
@Table(name="SHOW_MEMBER")
public class Member extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="NAME")
	private String name;
	@Column(name="NICK_NAME")
	private String nickName;
	@Column(name="EMAIL")
	private String email;
	@Column(name="PASSWORD")
	private String password;
	@Column(name="TELEPHONE")
	private String telephone;
	@Column(name="SEX")
	private String sex;
	@Column(name="SIGN",length=300)
	private String sign;
	@Column(name="PHOTO")
	private String photo;
	@OneToMany(mappedBy="member",fetch=FetchType.LAZY)
	private List<Article> articles = new ArrayList<Article>();
	@OneToMany(mappedBy="member",fetch=FetchType.LAZY)
	private List<Blog> blogs = new ArrayList<Blog>();
//	@OneToMany(mappedBy="member",fetch=FetchType.LAZY)
//	private List<Order> orders = new ArrayList<Order>();

	
	public Member() {
		super();
	}
	public Member(Long id) {
		super();
		this.id = id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	public List<Blog> getBlogs() {
		return blogs;
	}
	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}
//	public List<Order> getOrders() {
//		return orders;
//	}
//	public void setOrders(List<Order> orders) {
//		this.orders = orders;
//	}
	
}
