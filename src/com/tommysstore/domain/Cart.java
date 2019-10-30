package com.tommysstore.domain;

import java.util.Map;

public class Cart {
	
	private User user;
	private Map<Integer, CartItem> cartItems;
	
	public User getUser() {
		
		return user;
	}
	
	public void setUser(User user) {
		
		this.user = user;
	}
	
	public Map<Integer, CartItem> getCartItems() {
		
		return cartItems;
	}
	
	public void setCartItems(Map<Integer, CartItem> cartItems) {
		
		this.cartItems = cartItems;
	}
}
