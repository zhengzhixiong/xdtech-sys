package com.xdtech.shop.vo;

import java.io.Serializable;

import com.xdtech.core.model.BaseCondition;
import com.xdtech.web.freemark.item.GridColumn;

/**
 * 
 * @author max.zheng
 * @create 2014-11-30 21:45:39
 * @since 1.0
 * @see
 */
public class GoodsItem extends BaseCondition implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	@GridColumn(title="名称")
	private String name;
	@GridColumn(title="商品状态",width=80,formatter={"0=<span style=\"color:orange;\">下架</span>","1=<span style=\"color:green;\">上架</span>"})
	private String status;
	@GridColumn(title="描述")
	private String description;
	private String bigImg;
//	@GridColumn(title="图片路径")
	private String images;
	private String smallImg;
	@GridColumn(title="价格")
	private Long price;
	@GridColumn(title="产源地")
	private String produceArea;
	@GridColumn(title="保质期")
	private Integer qualityDay;
	//商品所属类别
	private String categoryIds;

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
		addToMap("name", name);
	}
	public String getName() {
		return name;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
		addToMap("status", status);
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
	public String getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
		addToMap("categoryIds", categoryIds);
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
	
	
	
}
