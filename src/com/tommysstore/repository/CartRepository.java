package com.tommysstore.repository;

import com.tommysstore.domain.Order;
import com.tommysstore.domain.OrderItem;

public interface CartRepository {
	
	Order placeOrder(Order order);
	
	void saveOrder(Order order);
	
	void saveOrderItem(OrderItem orderItem);
}
