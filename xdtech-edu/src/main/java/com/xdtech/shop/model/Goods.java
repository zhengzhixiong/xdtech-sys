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
import javax.persistence.Table;

import com.xdtech.core.model.BaseModel;

/**
 * 
 * @author max.zheng
 * @create 2014-11-30 21:45:39
 * @since 1.0
 * @see 
 */
@Entity
@Table(name="SHOP_GOODS")
public class Goods extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="NAME")
	private String name;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="BIG_IMG")
	private String bigImg;
	@Column(name="IMAGES")
	private String images;
	@Column(name="SMALL_IMG")
	private String smallImg;
	@Column(name="PRICE")
	private Long price;
	@Column(name="PRODUCE_AREA")
	private String produceArea;
	@Column(name="QUALITY_DAY")
	private Integer qualityDay;
	@Column(name="STATUS")
	private String status;
	//商品类别
	@ManyToMany(
			fetch = FetchType.LAZY,
			targetEntity = com.xdtech.shop.model.Category.class
			)
	@JoinTable(
			name = "SHOP_GOODS_CATEGORY",
			joinColumns={@JoinColumn(
                    name="GOODS_ID",
                    referencedColumnName="ID"
                    )},
            inverseJoinColumns={@JoinColumn(
                            name="CATEGORY_ID",
                            referencedColumnName = "ID")}
			)
	private List<Category> categories = new ArrayList<Category>();

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getImages() {
		return images;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getPrice() {
		return price;
	}
	public void setProduceArea(String produceArea) {
		this.produceArea = produceArea;
	}
	public String getProduceArea() {
		return produceArea;
	}
	public void setQualityDay(Integer qualityDay) {
		this.qualityDay = qualityDay;
	}
	public Integer getQualityDay() {
		return qualityDay;
	}
	public String getBigImg() {
		return bigImg;
	}
	public void setBigImg(String bigImg) {
		this.bigImg = bigImg;
	}
	public String getSmallImg() {
		return smallImg;
	}
	public void setSmallImg(String smallImg) {
		this.smallImg = smallImg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	
}
