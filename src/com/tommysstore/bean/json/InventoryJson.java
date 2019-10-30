package com.tommysstore.bean.json;

import java.math.BigDecimal;

import com.tommysstore.domain.Inventory;

public class InventoryJson {
	
	private Integer id;
	private String productId;
	private String productName;
	private BigDecimal price;
	private String picture;
	private String categoryName;
	private int stocks;
	
	public InventoryJson() {
		
	}
	
	public InventoryJson(Inventory inventory) {
		
		this.id = inventory.getProduct().getId();
		this.productId = inventory.getProduct().getProductId().getId();
		this.productName = inventory.getProduct().getName();
		this.price = inventory.getProduct().getPrice();
		this.picture = inventory.getProduct().getPicture();
		this.categoryName = inventory.getProduct().getCategory().getName();
		this.stocks = inventory.getStocks();
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

	public String getCategoryName() {
		
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		
		this.categoryName = categoryName;
	}
	
	public int getStocks() {
		
		return stocks;
	}
	
	public void setStocks(int stocks) {
		
		this.stocks = stocks;
	}
}
