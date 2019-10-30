package com.tommysstore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tommysstore.domain.OrderItem;
import com.tommysstore.domain.Product;
import com.tommysstore.domain.StockHistory;
import com.tommysstore.exception.ProductNotFoundException;
import com.tommysstore.service.InventoryService;
import com.tommysstore.service.ProductService;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private InventoryService inventoryService;
	
	@GetMapping("/stockhistory/{id}")
	public String viewStockHistory(@PathVariable Integer id, Model model) throws ProductNotFoundException {
		
		Product product = productService.find(id);
		
		List<StockHistory> stockHistory = inventoryService.listStockHistory(product);
		
		model.addAttribute("product", product);
		model.addAttribute("stockHistory", stockHistory);
		
		return "admin_stock_history";
	}
	
	@GetMapping("/orders/{id}")
	public String viewOrders(@PathVariable Integer id, Model model) throws ProductNotFoundException {
		
		Product product = productService.find(id);
		
		List<OrderItem> orders = productService.listOrders(product);
		
		model.addAttribute("product", product);
		model.addAttribute("orders", orders);
		
		return "admin_product_orders";
	}
	
	@ExceptionHandler(value = ProductNotFoundException.class)
	public String handleProductNotFoundException(HttpServletRequest request, Exception ex,
											  	 final RedirectAttributes redirectAttributes){
		
		redirectAttributes.addFlashAttribute("css", "danger");
		redirectAttributes.addFlashAttribute("msg", "Invalid product details");
		
		return "redirect:/admin/products";
	}
	
	@ExceptionHandler(value = NumberFormatException.class)
	public String handleNumberFormatException(HttpServletRequest request, Exception ex,
											  final RedirectAttributes redirectAttributes){
		
		redirectAttributes.addFlashAttribute("css", "danger");
		redirectAttributes.addFlashAttribute("msg", "Invalid product details");
		
		return "redirect:/admin/products";
	}
}
