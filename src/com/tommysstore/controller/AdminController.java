package com.tommysstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tommysstore.bean.form.ProductForm;
import com.tommysstore.bean.form.StockHistoryForm;
import com.tommysstore.bean.form.UserForm;
import com.tommysstore.domain.Category;
import com.tommysstore.domain.Product;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/dashboard")
	public String viewDashboard() {
		
		return "admin_dashboard";
	}
	
	@GetMapping("/users")
	public String viewUsers(Model model) {
		
		model.addAttribute("adminForm", new UserForm());
		
		return "admin_users";
	}
	
	@GetMapping("/categories")
	public String viewAllCategories(Model model) {
		
		model.addAttribute("categoryForm", new Category());
		model.addAttribute("editCategoryForm", new Category());
		
		return "admin_categories";
	}
	
	@GetMapping("/products")
	public String viewAllProducts(Model model) {
		
		model.addAttribute("productForm", new Product());
		model.addAttribute("addProductForm", new ProductForm());
		model.addAttribute("editProductForm", new ProductForm());
		
		return "admin_products";
	}
	
	@GetMapping("/inventory")
	public String viewInventory(Model model) {
		
		model.addAttribute("inventoryForm", new StockHistoryForm());
		
		return "admin_inventory";
	}
	
	@GetMapping("/product/stockhistory")
	public String viewStockHistory() {
		
		return "admin_stock_history";
	}
	
	@GetMapping("/product/orders")
	public String viewOrders() {
		
		return "admin_product_orders";
	}
}
