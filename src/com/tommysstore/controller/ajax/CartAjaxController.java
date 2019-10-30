package com.tommysstore.controller.ajax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tommysstore.bean.form.OrderForm;
import com.tommysstore.bean.json.CartItemJson;
import com.tommysstore.bean.json.CartJson;
import com.tommysstore.bean.json.JsonResponse;
import com.tommysstore.constant.PaymentMethod;
import com.tommysstore.domain.Cart;
import com.tommysstore.domain.CartItem;
import com.tommysstore.domain.Order;
import com.tommysstore.domain.Product;
import com.tommysstore.domain.ShippingAddress;
import com.tommysstore.domain.User;
import com.tommysstore.exception.CreditCardNotFoundException;
import com.tommysstore.exception.InsufficientStockException;
import com.tommysstore.exception.ProductNotFoundException;
import com.tommysstore.exception.ShippingAddressNotFoundException;
import com.tommysstore.service.CartService;
import com.tommysstore.service.InventoryService;
import com.tommysstore.service.ProductService;
import com.tommysstore.service.UserService;

@Controller
@RequestMapping(value = "/ajax/cart", produces = "application/json")
public class CartAjaxController {

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private CartService cartService;
	
	@GetMapping
	@ResponseBody
	public JsonResponse<List<CartItemJson>> list(HttpSession session) {
		
		JsonResponse<List<CartItemJson>> response = new JsonResponse<List<CartItemJson>>();
		List<CartItemJson> cartItemJson = new ArrayList<>();
		int displayStockLimit = inventoryService.getDisplayStockLimit();
		
		Cart cart = (Cart) session.getAttribute("cart");
		
		if(cart != null) {
			
			Map<Integer, CartItem> cartItems = cart.getCartItems();
			
			for (Map.Entry<Integer, CartItem> entry : cartItems.entrySet()) {
				
				CartItem cartItem = entry.getValue();
				CartItemJson json = new CartItemJson(cartItem);
				int stocks = cartItem.getProduct().getInventory().getStocks();
				String inventoryStatus = "";
				int quantity = cartItem.getQuantity();
				BigDecimal price = cartItem.getProduct().getPrice();
				BigDecimal amount = price.multiply(new BigDecimal(quantity));
				
				if(stocks >= displayStockLimit) {
					
					inventoryStatus = "In Stock";
				}
				
				else if(stocks < displayStockLimit && stocks >= 1) {
					
					inventoryStatus = "Only " + stocks + " left in stock";
				}
				
				else {
					
					inventoryStatus = "No Stock";
				}
				
				json.setInventoryStatus(inventoryStatus);
				json.setAmount(amount);
				cartItemJson.add(json);
			}
		}
		
		response.setStatus("success");
		response.setData(cartItemJson);
		
		return response;
	}
	
	@GetMapping("/clear")
	@ResponseBody
	public JsonResponse<?> clear(HttpSession session) {

		Cart cart = (Cart) session.getAttribute("cart");
		cart.getCartItems().clear();
		
		JsonResponse<?> response = new JsonResponse<>();
		response.setStatus("success");
		response.setCustomMessage("Cart is successfully cleared");
		
		return response;
	}
	
	@PostMapping("/add")
	@ResponseBody
	public JsonResponse<?> add(@ModelAttribute("cartItemForm") @Valid CartItem cartItem, 
			 			  	   BindingResult bindingResult, HttpSession session) 
			 			  	   throws ProductNotFoundException, InsufficientStockException {

		if(bindingResult.hasErrors()) {
			
			Map<String, String> errors = bindingResult.getFieldErrors().stream().collect(
					Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
		  	);
			
			JsonResponse<?> response = new JsonResponse<>();
			response.setStatus("error");
			response.setErrorMessages(errors);
			
			return response;
		}
		
		Integer productId = cartItem.getProduct().getId();
		Product validProduct = productService.find(productId);
		cartItem.setProduct(validProduct);
		
		int quantity = cartItem.getQuantity();
		inventoryService.checkStockSufficiency(cartItem);
		
		User loginUser = (User) session.getAttribute("loginUser");
		Cart cart = (Cart) session.getAttribute("cart");
		
		if(cart == null) {
			
			cart = new Cart();
			cart.setUser(loginUser);
			Map<Integer, CartItem> cartItems = new HashMap<>();
			
			cartItems.put(productId, cartItem);
			cart.setCartItems(cartItems);
			session.setAttribute("cart",  cart);
		}
		
		else {
			
			Map<Integer, CartItem> cartItems = cart.getCartItems();
			
			if(cartItems.containsKey(productId)) {
				
				int currentQuantity = cartItems.get(productId).getQuantity();
				cartItem.setQuantity(quantity + currentQuantity);
				inventoryService.checkStockSufficiency(cartItem);
				
				cartItems.replace(productId, cartItem);
			}
			
			else cartItems.put(productId, cartItem);
		}
		
		JsonResponse<?> response = new JsonResponse<>();
		response.setStatus("success");
		response.setCustomMessage("Product is successfully added to your cart");
		
		return response;
	}
	
	@PostMapping("/edit")
	@ResponseBody
	public JsonResponse<?> edit(@ModelAttribute("cartItemForm") @Valid CartItem cartItem, BindingResult bindingResult, 
								HttpSession session) throws ProductNotFoundException, InsufficientStockException {
		
		if(bindingResult.hasErrors()) {
			
			Map<String, String> errors = bindingResult.getFieldErrors().stream().collect(
					Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
		  	);
			
			JsonResponse<?> response = new JsonResponse<>();
			response.setStatus("error");
			response.setErrorMessages(errors);
			
			return response;
		}
		
		Integer productId = cartItem.getProduct().getId();
		Product validProduct = productService.find(productId);
		cartItem.setProduct(validProduct);
		
		inventoryService.checkStockSufficiency(cartItem);
		
		Cart cart = (Cart) session.getAttribute("cart");
		cart.getCartItems().replace(productId, cartItem);
		
		CartItemJson cartItemJson = new CartItemJson(cartItem);
		int stocks = validProduct.getInventory().getStocks();
		int displayStockLimit = inventoryService.getDisplayStockLimit();
		String inventoryStatus = "";
		
		int quantity = cartItem.getQuantity();
		BigDecimal price = validProduct.getPrice();
		BigDecimal amount = price.multiply(new BigDecimal(quantity));
		
		if(stocks >= displayStockLimit) {
			
			inventoryStatus = "In Stock";
		}
		
		else if(stocks < displayStockLimit && stocks >= 1) {
			
			inventoryStatus = "Only " + stocks + " left in stock";
		}
		
		else {
			
			inventoryStatus = "No Stock";
		}
		
		cartItemJson.setInventoryStatus(inventoryStatus);
		cartItemJson.setAmount(amount);
		
		JsonResponse<CartItemJson> response = new JsonResponse<>();
		response.setStatus("success");
		response.setData(cartItemJson);
		response.setCustomMessage("Changes in the cart item is successfully saved");
		
		return response;
	}
	
	@PostMapping("/remove")
	@ResponseBody
	public JsonResponse<?> remove(@RequestParam Integer productId, HttpSession session) 
								  throws ProductNotFoundException{

		JsonResponse<?> response = new JsonResponse<>();
		Cart cart = (Cart) session.getAttribute("cart");
		Map<Integer, CartItem> cartItems = cart.getCartItems();
		
		if(!cartItems.containsKey(productId)) {
			
			response.setStatus("success");
			response.setCustomMessage("Invalid cart item details");
		}
		
		cartItems.remove(productId);
		
		response.setStatus("success");
		response.setCustomMessage("Cart item is successfully removed");
		
		return response;
	}
	
	@GetMapping("/checkout")
	@ResponseBody
	public JsonResponse<CartJson> checkout(HttpSession session) {
		
		JsonResponse<CartJson> response = new JsonResponse<CartJson>();
		JsonResponse<List<CartItemJson>> cartItemResponse = list(session);
		List<CartItemJson> cartItemJson = cartItemResponse.getData();
		BigDecimal totalPrice = new BigDecimal(0);
		
		for(CartItemJson json : cartItemJson) {
			
			totalPrice = totalPrice.add(json.getAmount());
		}
		
		totalPrice = totalPrice.setScale(2);
		
		response.setStatus("success");
		response.setData(new CartJson(cartItemJson, totalPrice));
		
		return response;
	}
	
	@PostMapping("/placecheckout")
	@ResponseBody
	public JsonResponse<?> placeCheckout(@ModelAttribute("orderForm") @Valid OrderForm orderForm, 
										 BindingResult bindingResult, HttpSession session) 
										 throws CreditCardNotFoundException, ShippingAddressNotFoundException {

		JsonResponse<?> response = new JsonResponse<>();
		
		if(bindingResult.hasErrors()) {
			
			Map<String, String> errors = bindingResult.getFieldErrors().stream().collect(
					Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
		  	);
			
			response.setStatus("error");
			response.setErrorMessages(errors);
			
			return response;
		}
		
		Cart cart = (Cart) session.getAttribute("cart");
		User loginUser = (User) session.getAttribute("loginUser");
		
		Integer shippingAddressId = orderForm.getShippingAddressId();
		Integer creditCardId = orderForm.getCreditCardId();
		ShippingAddress validAddress = userService.findAddressById(shippingAddressId);
		
		Order order = new Order();
		order.setShippingAddress(validAddress);
		order.setUser(loginUser);
		
		if(creditCardId == 0) {
			
			order.setPaymentMethod(PaymentMethod.CASH);
		}
		
		else {
			
			order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
			userService.findCreditCardById(creditCardId);
		}
		
		cartService.checkout(cart, order);
		
		session.removeAttribute("cart");
		
		response.setStatus("success");
		
		return response;
	}
	
	@ExceptionHandler(value = ProductNotFoundException.class)
	@ResponseBody
	public JsonResponse<?> handleProductNotFoundException(HttpServletRequest request, Exception ex,
														  final RedirectAttributes redirectAttributes){
		
		JsonResponse<?> response = new JsonResponse<>();
		response.setStatus("error");
		response.setCustomMessage("Invalid product details");
		
		return response;
	}
	
	@ExceptionHandler(value = InsufficientStockException.class)
	@ResponseBody
	public JsonResponse<?> handleInsufficientStockException(HttpServletRequest request, Exception ex,
											  	  	   		final RedirectAttributes redirectAttributes){
		
		JsonResponse<?> response = new JsonResponse<>();
		response.setStatus("error");
		response.setCustomMessage("Stock is insufficient");
		
		return response;
	}
	
	@ExceptionHandler(value = NumberFormatException.class)
	@ResponseBody
	public JsonResponse<?> handleNumberFormatException(HttpServletRequest request, Exception ex,
											  	  	   final RedirectAttributes redirectAttributes){
		
		JsonResponse<?> response = new JsonResponse<>();
		response.setStatus("error");
		response.setCustomMessage("Invalid cart item details");
		
		return response;
	}
}
