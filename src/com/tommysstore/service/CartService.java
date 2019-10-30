package com.tommysstore.service;

import com.tommysstore.domain.Cart;
import com.tommysstore.domain.Order;

public interface CartService {
	
	void checkout(Cart cart, Order order);
}
