package com.xdtech.show.vo;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import com.xdtech.web.freemark.item.GridColumn;

/**
 * 
 * @author max.zheng
 * @create 2014-12-06 20:45:01
 * @since 1.0
 * @see
 */
public class MemberItem implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	@GridColumn(title="姓名")
	private String name;
	@GridColumn(title="昵称")
	private String nickName;
	@GridColumn(title="邮箱",width=140)
	private String email;
//	@GridColumn(title="密码")
	private String password;
	@GridColumn(title="手机号码")
	private String telephone;
	@GridColumn(title="性别",formatter={"M=男","F=女"})
	private String sex;
	@GridColumn(title="个人签名",width=200)
	private String sign;
	@GridColumn(title="头像路径",width=140)
	private String photo;

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
	
}
