package com.tommysstore.bean;

import com.tommysstore.domain.Product;

public class ProductInventoryBean {
	
	private Product product;
	private String inventoryStatus;
	
	public Product getProduct() {
		
		return product;
	}
	
	public void setProduct(Product product) {
		
		this.product = product;
	}
	
	public String getInventoryStatus() {
		
		return inventoryStatus;
	}
	
	public void setInventoryStatus(String inventoryStatus) {
		
		this.inventoryStatus = inventoryStatus;
	}
}
