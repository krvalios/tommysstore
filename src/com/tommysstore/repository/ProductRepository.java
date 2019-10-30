package com.tommysstore.repository;

import java.util.List;

import com.tommysstore.bean.PopularProductBean;
import com.tommysstore.domain.Category;
import com.tommysstore.domain.OrderItem;
import com.tommysstore.domain.Product;
import com.tommysstore.exception.UnavailableProductException;
import com.tommysstore.generator.ProductUid;

public interface ProductRepository {

	List<Product> list();
	
	List<Product> listByCategory(Category category);
	
	List<Product> listByName(String name);
	
	List<Product> findByProduct(Product product);
	
	List<PopularProductBean> listPopular(int maxResult, int dayLimit);
	
	List<OrderItem> listOrders(Product product);
	
	Product find(Integer id);
	
	Product findByProductId(ProductUid productId);
	
	ProductUid generateProductUid(ProductUid productId) throws UnavailableProductException;

	void add(Product product) throws UnavailableProductException;
	
	void save(Product product) throws UnavailableProductException;
	
	void remove(Product product);
	
}
