package com.tommysstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tommysstore.domain.Category;
import com.tommysstore.domain.Product;
import com.tommysstore.exception.CategoryNotFoundException;
import com.tommysstore.exception.EntityDeletionException;
import com.tommysstore.exception.UnavailableCategoryException;
import com.tommysstore.generator.CategoryUid;
import com.tommysstore.repository.CategoryRepository;
import com.tommysstore.repository.ProductRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
    private CategoryRepository categoryRepository;
	
	@Autowired
    private ProductRepository productRepository;
	
	@Transactional
	public List<Category> list() {
		
		return categoryRepository.list();
	}
	
	@Transactional
	public Category find(Integer id) throws CategoryNotFoundException {
		
		Category category = categoryRepository.find(id);
		
		if(category == null) {
			
			throw new CategoryNotFoundException();
		}
		
		return category;
	}
	
	@Transactional(rollbackFor = UnavailableCategoryException.class)
	public CategoryUid generateCategoryUid(CategoryUid categoryId) throws UnavailableCategoryException {
		
		return categoryRepository.generateCategoryUid(categoryId);
	}
	
	@Transactional(rollbackFor = UnavailableCategoryException.class)
	public void add(Category category) throws UnavailableCategoryException {
		
		CategoryUid categoryId = generateCategoryUid(new CategoryUid());
		category.setCategoryId(categoryId);
		
		categoryRepository.add(category);
	}
	
	@Transactional(rollbackFor = UnavailableCategoryException.class)
	public void save(Category category) throws UnavailableCategoryException {

		categoryRepository.save(category);
	}
	
	@Transactional(rollbackFor = EntityDeletionException.class)
	public void remove(Category category) throws EntityDeletionException {
		
		List<Product> products = productRepository.listByCategory(category);
		
		if(products.size() != 0) {
			
			throw new EntityDeletionException();
		}
		
		categoryRepository.remove(category);
	}
}
