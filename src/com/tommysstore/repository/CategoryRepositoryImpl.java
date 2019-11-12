package com.tommysstore.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tommysstore.domain.Category;
import com.tommysstore.exception.UnavailableCategoryException;
import com.tommysstore.generator.CategoryUid;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Category> list() {
		
		TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c", Category.class);
		
        return query.getResultList();
	}
	
	public Category find(Integer id) {
		
		return entityManager.find(Category.class, id);
	}
	
	public CategoryUid generateCategoryUid(CategoryUid categoryId) throws UnavailableCategoryException {
		
		try {

			entityManager.persist(categoryId);
			
			return entityManager.merge(categoryId);
		} 
		
		catch (PersistenceException e) {
			
			throw new UnavailableCategoryException();
		}
	}
	
	public void add(Category category) throws UnavailableCategoryException {
		
		try {

			entityManager.persist(category);
		} 
		
		catch (PersistenceException e) {
			
			throw new UnavailableCategoryException();
		}
	}

	public void save(Category category) throws UnavailableCategoryException {
		
		try {

			entityManager.merge(category);
			entityManager.flush();
		} 
		
		catch (PersistenceException e) {

			throw new UnavailableCategoryException();
		}
	}
	
	public void remove(Category category) {
		
		category = entityManager.merge(category);
		entityManager.remove(category);
	}
}
