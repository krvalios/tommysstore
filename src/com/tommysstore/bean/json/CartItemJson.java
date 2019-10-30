package com.tommysstore.bean.json;

import java.math.BigDecimal;

import com.tommysstore.domain.CartItem;
import com.tommysstore.domain.Product;

public class CartItemJson {
	
	private Integer id;
	private String productName;
	private BigDecimal price;
	private String picture;
	private Integer categoryId;
	private String categoryName;
	private String inventoryStatus;
	private int quantity;
	private BigDecimal amount;
	
	public CartItemJson() {
		
	}
	
	public CartItemJson(CartItem cartItem) {
		
		Product product = cartItem.getProduct();
		this.id = product.getId();
		this.productName = product.getName();
		this.price = product.getPrice();
		this.picture = product.getPicture();
		this.categoryId = product.getCategory().getId();
		this.categoryName = product.getCategory().getName();
		this.quantity = cartItem.getQuantity();
	}
	
	public Integer getId() {
		
		return id;
	}

	public void setId(Integer id) {
		
		this.id = id;
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

	public int getQuantity() {
		
		return quantity;
	}

	public void setQuantity(int quantity) {
		
		this.quantity = quantity;
	}

	public BigDecimal getAmount() {
		
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		
		this.amount = amount;
	}
}