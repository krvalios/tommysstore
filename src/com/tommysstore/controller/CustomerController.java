package com.tommysstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tommysstore.bean.form.OrderForm;
import com.tommysstore.domain.CartItem;
import com.tommysstore.domain.CreditCard;
import com.tommysstore.domain.ShippingAddress;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@GetMapping("/categories")
	public String browseCategories(Model model) {
		
		return "customer_categories";
	}
	
	@GetMapping("/products")
	public String viewProducts(Model model) {
		
		model.addAttribute("cartItemForm", new CartItem());
		
		return "customer_products";
	}
	
	@GetMapping("/cart")
	public String viewCart(Model model) {

		model.addAttribute("cartItemForm", new CartItem());
		
		return "customer_cart";
	}
	
	@GetMapping("/cart/checkout")
	public String viewCheckout(Model model) {
		
		model.addAttribute("orderForm", new OrderForm());
		model.addAttribute("addressForm", new ShippingAddress());
		model.addAttribute("creditCardForm", new CreditCard());
		
		return "customer_checkout";
	}
}
