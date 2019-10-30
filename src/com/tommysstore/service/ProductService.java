package com.tommysstore.service;

import java.util.List;

import com.tommysstore.bean.PopularProductBean;
import com.tommysstore.domain.Category;
import com.tommysstore.domain.OrderItem;
import com.tommysstore.domain.Product;
import com.tommysstore.exception.EntityDeletionException;
import com.tommysstore.exception.ProductNotFoundException;
import com.tommysstore.exception.UnavailableProductException;
import com.tommysstore.generator.ProductUid;

public interface ProductService {

	List<Product> list();
	
	List<Product> listByCategory(Category category);
	
	List<Product> listByName(String name);
	
	List<Product> findByProduct(Product product);
	
	List<PopularProductBean> listPopular();
	
	List<OrderItem> listOrders(Product product);
	
	Product find(Integer id) throws ProductNotFoundException;
	
	Product findByProductId(ProductUid productId) throws ProductNotFoundException;
	
	ProductUid generateProductUid(ProductUid productId) throws UnavailableProductException;
	
	void add(Product product) throws UnavailableProductException, ProductNotFoundException;
	
	void save(Product product) throws UnavailableProductException;

	void remove(Product product) throws ProductNotFoundException, EntityDeletionException;
}
