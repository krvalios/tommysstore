package com.tommysstore.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tommysstore.bean.PopularProductBean;
import com.tommysstore.domain.Category;
import com.tommysstore.domain.OrderItem;
import com.tommysstore.domain.Product;
import com.tommysstore.exception.UnavailableProductException;
import com.tommysstore.generator.ProductUid;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Product> list() {
		
		TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p", Product.class);
		
        return query.getResultList();
	}
	
	public List<Product> listByCategory(Category category) {
		
		TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.category = :category", 
															  Product.class);
		query.setParameter("category", category);
		
        return query.getResultList();
	}
	
	public List<Product> listByName(String name) {
		
		TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.name LIKE :name", 
															  Product.class);
		query.setParameter("name", "%" + name + "%");
		
        return query.getResultList();
	}
	
	public List<Product> findByProduct(Product product) {
		
		TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.name LIKE :name"
															+ " AND p.category = :category", Product.class);
		query.setParameter("name", "%" + product.getName() + "%");
		query.setParameter("category", product.getCategory());
		
        return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<PopularProductBean> listPopular(int maxResult, int dayLimit) {
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, dayLimit);
		Date thirtyDays = calendar.getTime();
		
		Query query = entityManager.createQuery("SELECT p, SUM(o.quantity) FROM OrderItem o JOIN o.product p"
											  + " JOIN o.order d WHERE d.dateOrdered < CURRENT_TIMESTAMP"
											  + " AND d.dateOrdered > :thirtyDays GROUP BY p"
											  + " ORDER BY SUM(o.quantity) DESC");
		query.setParameter("thirtyDays", thirtyDays, TemporalType.DATE);
		query.setMaxResults(maxResult);
		
		List<PopularProductBean> popularProducts = new ArrayList<>();
		List<Object[]> results = query.getResultList();
		
		for(Object[] result : results) {
			
			Product product = (Product) result[0];
			Long longQuantity = (Long) result[1];
			int quantitySold = longQuantity.intValue();
			
			PopularProductBean popularProduct = new PopularProductBean();
			popularProduct.setProduct(product);
			popularProduct.setQuantitySold(quantitySold);
			
			popularProducts.add(popularProduct);
		}
		
		return popularProducts;
	}
	
	public List<OrderItem> listOrders(Product product) {
		
		TypedQuery<OrderItem> query = entityManager.createQuery("SELECT o FROM OrderItem o WHERE o.product = :product"
															  + " ORDER BY o.order.dateOrdered DESC", OrderItem.class);
		query.setParameter("product", product);
		
        return query.getResultList();
	}
	
	public Product find(Integer id) {
		
		return entityManager.find(Product.class, id);
	}
	
	public Product findByProductId(ProductUid productId) {
		
		TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.productId = :productId", 
															  Product.class);
		query.setParameter("productId", productId);
		
        return query.getSingleResult();
	}
	
	public ProductUid generateProductUid(ProductUid productId) throws UnavailableProductException {
		
		try {

			entityManager.persist(productId);
			
			return entityManager.merge(productId);
		} 
		
		catch (PersistenceException e) {
			
			throw new UnavailableProductException();
		}
	}
	
	public void add(Product product) throws UnavailableProductException {
		
		try {

			entityManager.persist(product);
		} 
		
		catch (PersistenceException e) {
			
			throw new UnavailableProductException();
		}
	}

	public void save(Product product) throws UnavailableProductException {
		
		try {

			entityManager.merge(product);
			entityManager.flush();
		} 
		
		catch (PersistenceException e) {
			
			throw new UnavailableProductException();
		}
	}
	
	public void remove(Product product) {
		
//		product = entityManager.merge(product);
		product = entityManager.find(Product.class, product.getId());
		entityManager.remove(product);
	}
}
