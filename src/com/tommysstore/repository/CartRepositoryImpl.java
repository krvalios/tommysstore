package com.tommysstore.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.tommysstore.domain.Order;
import com.tommysstore.domain.OrderItem;

@Repository
public class CartRepositoryImpl implements CartRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Order placeOrder(Order order) {
		
		entityManager.persist(order);
		
		return entityManager.merge(order);
	}
	
	public void saveOrder(Order order) {
		
		entityManager.merge(order);
	}
	
	public void saveOrderItem(OrderItem orderItem) {
		
		entityManager.persist(orderItem);
	}
}
