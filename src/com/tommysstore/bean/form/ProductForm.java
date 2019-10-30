package com.tommysstore.bean.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
	
	private Integer id;
	
	@NotEmpty(message = "Product name is required")
	private String name;
	
	@NotNull(message = "Category is required")
	private Integer categoryId;
	
	@NotNull(message = "Price is required")
	private BigDecimal price;
	
	private MultipartFile picture;

	public Integer getId() {
		
		return id;
	}

	public void setId(Integer id) {
		
		this.id = id;
	}

	public String getName() {
		
		return name;
	}

	public void setName(String name) {
		
		this.name = name;
	}

	public Integer getCategoryId() {
		
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		
		this.categoryId = categoryId;
	}

	public BigDecimal getPrice() {
		
		return price;
	}

	public void setPrice(BigDecimal price) {
		
		this.price = price;
	}

	public MultipartFile getPicture() {
		
		return picture;
	}

	public void setPicture(MultipartFile picture) {
		
		this.picture = picture;
	}
}
