package com.ibeetl.code.ch05.messagePack;

import org.msgpack.annotation.Message;

import java.util.Date;

/**
 * 商品
 */
@Message
public class Product {
	private Integer id;
	private String name;
	private String description;
	private Double price;
	//商品状态 ：true 正常 ，false 已下架
	private Boolean productStatus;
	private Date createTime;
	private Date updateTime;
	private Boolean del;

	public Product() {
	}

	public Product(Integer id, String name, String description, Double price, Boolean productStatus, Date createTime,
			Date updateTime, Boolean del) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.productStatus = productStatus;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.del = del;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Boolean productStatus) {
		this.productStatus = productStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getDel() {
		return del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	@Override
	public String toString() {
		return "Product{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", price="
				+ price + ", productStatus=" + productStatus + ", createTime=" + createTime + ", updateTime=" + updateTime + ", del=" + del + '}';
	}
}