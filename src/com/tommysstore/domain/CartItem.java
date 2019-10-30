package com.tommysstore.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CartItem {
	
	@NotNull
	private Product product;
	
	@Min(value = 1, message = "Quantity must be at least 1")
	private int quantity;
	
	public Product getProduct() {
		
		return product;
	}
	
	public void setProduct(Product product) {
		
		this.product = product;
	}

	public int getQuantity() {
		
		return quantity;
	}

	public void setQuantity(int quantity) {
		
		this.quantity = quantity;
	}
}
