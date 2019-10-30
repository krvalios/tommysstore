package com.tommysstore.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tommysstore.domain.Cart;
import com.tommysstore.domain.CartItem;
import com.tommysstore.domain.Inventory;
import com.tommysstore.domain.Order;
import com.tommysstore.domain.OrderItem;
import com.tommysstore.domain.Product;
import com.tommysstore.repository.CartRepository;
import com.tommysstore.repository.InventoryRepository;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
    private CartRepository cartRepository;
	
	@Autowired
    private InventoryRepository inventoryRepository;
	
	@Transactional
	public void checkout(Cart cart, Order order) {
		
		BigDecimal totalAmount = new BigDecimal(0);
		order.setTotalAmount(totalAmount);
		
		order = cartRepository.placeOrder(order);
		
		Map<Integer, CartItem> cartItems = cart.getCartItems();
		
		for(CartItem cartItem : cartItems.values()) {
			
			OrderItem orderItem = new OrderItem();
			Product product = cartItem.getProduct();
			BigDecimal price = product.getPrice();
			int quantity = cartItem.getQuantity();

			orderItem.setOrder(order);
			orderItem.setProduct(product);
			orderItem.setPrice(price);
			orderItem.setQuantity(quantity);
			
			cartRepository.saveOrderItem(orderItem);
			
			Inventory inventory = product.getInventory();
			int stocks = inventory.getStocks();
			
			inventory.setStocks(stocks - quantity);
			inventoryRepository.save(inventory);
			
			BigDecimal q = new BigDecimal(quantity);
			BigDecimal amount = price.multiply(q);
			
			totalAmount = totalAmount.add(amount);
		}
		
		order.setTotalAmount(totalAmount);
		cartRepository.saveOrder(order);
	}
}
