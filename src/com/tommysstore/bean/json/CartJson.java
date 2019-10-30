package com.tommysstore.bean.json;

import java.math.BigDecimal;
import java.util.List;

public class CartJson {
	
	private List<CartItemJson> cartItems;
	private BigDecimal totalPrice;
	
	public CartJson() {
		
	}
	
	public CartJson(List<CartItemJson> cartItems, BigDecimal totalPrice) {
		
		this.cartItems = cartItems;
		this.totalPrice = totalPrice;
	}
	
	public List<CartItemJson> getCartItems() {
		
		return cartItems;
	}
	
	public void setCartItems(List<CartItemJson> cartItems) {
		
		this.cartItems = cartItems;
	}
	
	public BigDecimal getTotalPrice() {
		
		return totalPrice;
	}
	
	public void setTotalPrice(BigDecimal totalPrice) {
		
		this.totalPrice = totalPrice;
	}
}
