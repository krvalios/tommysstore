package com.tommysstore.repository;

import java.util.List;

import com.tommysstore.domain.Category;
import com.tommysstore.exception.UnavailableCategoryException;
import com.tommysstore.generator.CategoryUid;

public interface CategoryRepository {
	
	List<Category> list();
	
	Category find(Integer id);
	
	CategoryUid generateCategoryUid(CategoryUid categoryId) throws UnavailableCategoryException;
	
	void add(Category category) throws UnavailableCategoryException;

	void save(Category category) throws UnavailableCategoryException;
	
	void remove(Category category);
}
