package com.tommysstore.bean.json;

import com.tommysstore.domain.Category;

public class CategoryJson {
	
	private Integer id;
	private String categoryId;
	private String name;
	
	public CategoryJson() {
		
	}
	
	public CategoryJson(Category category) {
	
		this.id = category.getId();
		this.categoryId = category.getCategoryId().getId();
		this.name = category.getName();
	}
	
	public Integer getId() {
		
		return id;
	}
	
	public void setId(Integer id) {
		
		this.id = id;
	}
	
	public String getCategoryId() {
		
		return categoryId;
	}
	
	public void setCategoryId(String categoryId) {
		
		this.categoryId = categoryId;
	}
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
}
