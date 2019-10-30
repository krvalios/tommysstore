package com.tommysstore.bean.json;

import java.math.BigDecimal;

import com.tommysstore.bean.PopularProductBean;

public class PopularProductJson {
	
	private String productName;
	private BigDecimal price;
	private String picture;
	private String categoryName;
	private int quantitySold;
	
	public PopularProductJson() {
		
	}
	
	public PopularProductJson(PopularProductBean popularProductBean) {
		
		this.productName = popularProductBean.getProduct().getName();
		this.price = popularProductBean.getProduct().getPrice();
		this.picture = popularProductBean.getProduct().getPicture();
		this.categoryName = popularProductBean.getProduct().getCategory().getName();
		this.quantitySold = popularProductBean.getQuantitySold();
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

	public int getQuantitySold() {
		
		return quantitySold;
	}

	public void setQuantitySold(int quantitySold) {
		
		this.quantitySold = quantitySold;
	}
}
