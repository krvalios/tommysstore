package com.tommysstore.service;

import java.util.List;

import com.tommysstore.domain.Category;
import com.tommysstore.exception.CategoryNotFoundException;
import com.tommysstore.exception.EntityDeletionException;
import com.tommysstore.exception.UnavailableCategoryException;
import com.tommysstore.generator.CategoryUid;

public interface CategoryService {
	
	List<Category> list();
	
	Category find(Integer id) throws CategoryNotFoundException;
	
	CategoryUid generateCategoryUid(CategoryUid categoryId) throws UnavailableCategoryException;
	
	void add(Category category) throws UnavailableCategoryException;
	
	void save(Category category) throws UnavailableCategoryException;

	void remove(Category category) throws EntityDeletionException;
}
