package com.tommysstore.bean.json;

import java.math.BigDecimal;

import com.tommysstore.domain.Product;

public class ProductJson {
	
	private Integer id;
	private String productId;
	private String name;
	private BigDecimal price;
	private String picture;
	private Integer categoryId;
	private String categoryName;
	
	public ProductJson() {
		
	}
	
	public ProductJson(Product product) {
		
		this.id = product.getId();
		this.productId = product.getProductId().getId();
		this.name = product.getName();
		this.price = product.getPrice();
		this.picture = product.getPicture();
		this.categoryId = product.getCategory().getId();
		this.categoryName = product.getCategory().getName();
	}
	
	public Integer getId() {
		
		return id;
	}
	
	public void setId(Integer id) {
		
		this.id = id;
	}
	
	public String getProductId() {
		
		return productId;
	}
	
	public void setProductId(String productId) {
		
		this.productId = productId;
	}
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public BigDecimal getPrice() {
		
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		
		this.price = price;
	}
	
	public String getPicture() {
		
		return picture;
	}
	
	public void setPicture(String picture) {
		
		this.picture = picture;
	}
	
	public Integer getCategoryId() {
		
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		
		this.categoryName = categoryName;
	}
}
