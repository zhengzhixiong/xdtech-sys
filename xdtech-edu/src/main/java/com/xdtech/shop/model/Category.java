package com.xdtech.shop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.xdtech.core.model.BaseModel;

/**
 * 
 * @author max.zheng
 * @create 2014-12-08 20:01:36
 * @since 1.0
 * @see 
 */
@Entity
@Table(name="SHOP_CATEGORY")
public class Category extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="CODE")
	private String code;
	@Column(name="NAME")
	private String name;
	@Column(name="DESCRIPTION",length=1000)
	private String description;
	@Column(name="REMARK")
	private String remark;
	
	@ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;
	@OneToMany(mappedBy="parent",orphanRemoval=true,fetch=FetchType.LAZY)  
    private List<Category> children = new ArrayList<Category>();

	@ManyToMany
	@JoinTable(name="SHOP_GOODS_CATEGORY", 
			   joinColumns={@JoinColumn(name="CATEGORY_ID",
			                            referencedColumnName="ID")},
			   inverseJoinColumns={@JoinColumn(name="GOODS_ID",
                                    referencedColumnName="ID")}
    )
	private List<Goods> goodsList = new ArrayList<Goods>();

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return remark;
	}
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	public List<Category> getChildren() {
		return children;
	}
	public void setChildren(List<Category> children) {
		this.children = children;
	}
	public List<Goods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
	
	
}
