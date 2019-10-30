package com.tommysstore.bean.form;

import javax.validation.constraints.NotNull;

public class SearchForm {
	
	private String productName;
	
	@NotNull
	private Integer categoryId;

	public String getProductName() {
		
		return productName;
	}

	public void setProductName(String productName) {
		
		this.productName = productName;
	}

	public Integer getCategoryId() {
		
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		
		this.categoryId = categoryId;
	}
}
