package com.tommysstore.bean;

import com.tommysstore.domain.Product;

public class PopularProductBean {
	
	private Product product;
	private int quantitySold;
	
	public Product getProduct() {
		
		return product;
	}
	
	public void setProduct(Product product) {
		
		this.product = product;
	}
	
	public int getQuantitySold() {
		
		return quantitySold;
	}
	
	public void setQuantitySold(int quantitySold) {
		
		this.quantitySold = quantitySold;
	}
	
	

}
