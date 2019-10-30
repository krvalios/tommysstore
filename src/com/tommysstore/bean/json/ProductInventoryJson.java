package com.tommysstore.bean.json;

import java.math.BigDecimal;

import com.tommysstore.bean.ProductInventoryBean;
import com.tommysstore.domain.Product;

public class ProductInventoryJson {
	
	private Integer id;
	private String productId;
	private String productName;
	private BigDecimal price;
	private String picture;
	private Integer categoryId;
	private String categoryName;
	private String inventoryStatus;
	
	public ProductInventoryJson() {
		
	}
	
	public ProductInventoryJson(ProductInventoryBean productInventoryBean) {
		
		Product product = productInventoryBean.getProduct();
		this.id = product.getId();
		this.productId = product.getProductId().getId();
		this.productName = product.getName();
		this.price = product.getPrice();
		this.picture = product.getPicture();
		this.categoryId = product.getCategory().getId();
		this.categoryName = product.getCategory().getName();
		this.inventoryStatus = productInventoryBean.getInventoryStatus();
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
	
	public String getProductName() {
		
		return productName;
	}
	
	public void setProductName(String productName) {
		
		this.productName = productName;
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
	
	public String getInventoryStatus() {
		
		return inventoryStatus;
	}
	
	public void setInventoryStatus(String inventoryStatus) {
		
		this.inventoryStatus = inventoryStatus;
	}
}
