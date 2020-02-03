package com.java.search.online.product.sql.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTS")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "P_ID")
	private Integer pId;

	@Column(name = "B_NAME")
	private String brandName;

	@Column(name = "P_CATEG")
	private String productCategory;

	@Column(name = "P_STOCK")
	private Integer stockAvl;

	@Column(name = "P_SIZE")
	private String size;

	@Column(name = "P_PRICE")
	private String price;
	
	@Column(name = "P_CLR")
	private String colour;

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public Integer getStockAvl() {
		return stockAvl;
	}

	public void setStockAvl(Integer stockAvl) {
		this.stockAvl = stockAvl;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
	
 }
