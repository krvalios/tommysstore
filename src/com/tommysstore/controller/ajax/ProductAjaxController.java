package com.tommysstore.controller.ajax;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tommysstore.bean.ProductInventoryBean;
import com.tommysstore.bean.form.ProductForm;
import com.tommysstore.bean.json.JsonResponse;
import com.tommysstore.bean.json.OrderItemJson;
import com.tommysstore.bean.json.PopularProductJson;
import com.tommysstore.bean.json.ProductInventoryJson;
import com.tommysstore.bean.json.ProductJson;
import com.tommysstore.bean.json.StockHistoryJson;
import com.tommysstore.domain.Category;
import com.tommysstore.domain.Product;
import com.tommysstore.exception.CategoryNotFoundException;
import com.tommysstore.exception.EntityDeletionException;
import com.tommysstore.exception.ProductNotFoundException;
import com.tommysstore.exception.UnavailableCategoryException;
import com.tommysstore.exception.UnavailableProductException;
import com.tommysstore.service.CategoryService;
import com.tommysstore.service.InventoryService;
import com.tommysstore.service.ProductService;

@Controller
@RequestMapping(value = "/ajax/product", produces = "application/json")
public class ProductAjaxController {

	@Autowired
	private ProductService productService;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	@ResponseBody
	public JsonResponse<List<ProductJson>> list(){
		
		JsonResponse<List<ProductJson>> response = new JsonResponse<List<ProductJson>>();
		List<ProductJson> productJson = productService.list().stream().map(ProductJson::new)
										.collect(Collectors.toList());
		response.setStatus("success");
		response.setData(productJson);
		
		return response;
	}
	
	@GetMapping("/popular")
	@ResponseBody
	public JsonResponse<List<PopularProductJson>> listPopular(){
		
		JsonResponse<List<PopularProductJson>> response = new JsonResponse<List<PopularProductJson>>();
		List<PopularProductJson> productJson = productService.listPopular().stream()
											   .map(PopularProductJson::new).collect(Collectors.toList());
		response.setStatus("success");
		response.setData(productJson);
		
		return response;
	}
	
	@GetMapping("/bean")
	@ResponseBody
	public JsonResponse<List<ProductInventoryJson>> listBeans(){
		
		JsonResponse<List<ProductInventoryJson>> response = new JsonResponse<List<ProductInventoryJson>>();
		List<Product> products = productService.list();
		List<ProductInventoryJson> productInventoryJson = new ArrayList<>();
		int displayStockLimit = inventoryService.getDisplayStockLimit();
		
		for(Product product : products) {
			
			ProductInventoryBean bean = new ProductInventoryBean();
			int stocks = product.getInventory().getStocks();
			String inventoryStatus = "";
			
			if(stocks >= displayStockLimit) {
				
				inventoryStatus = "In Stock";
			}
			
			else if(stocks < displayStockLimit && stocks >= 1) {
				
				inventoryStatus = "Only " + stocks + " left in stock";
			}
			
			else {
				
				inventoryStatus = "No Stock";
			}

			bean.setProduct(product);
			bean.setInventoryStatus(inventoryStatus);
			
			ProductInventoryJson json = new ProductInventoryJson(bean);
			productInventoryJson.add(json);
		}
		
		response.setStatus("success");
		response.setData(productInventoryJson);
		
		return response;
	}
	
	@GetMapping("/bean/{id}")
	@ResponseBody
	public JsonResponse<ProductInventoryJson> findBean(@PathVariable Integer id) throws ProductNotFoundException {
		
		JsonResponse<ProductInventoryJson> response = new JsonResponse<ProductInventoryJson>();
		ProductInventoryBean bean = new ProductInventoryBean();
		Product product = productService.find(id);
		int displayStockLimit = inventoryService.getDisplayStockLimit();
		int stocks = product.getInventory().getStocks();
		String inventoryStatus = "";
		
		if(stocks >= displayStockLimit) {
			
			inventoryStatus = "In Stock";
		}
		
		else if(stocks < displayStockLimit && stocks >= 1) {
			
			inventoryStatus = "Only " + stocks + " left in stock";
		}
		
		else {
			
			inventoryStatus = "No Stock";
		}

		bean.setProduct(product);
		bean.setInventoryStatus(inventoryStatus);
		
		response.setStatus("success");
		response.setData(new ProductInventoryJson(bean));
		
		return response;
	}
	
	@PostMapping("/search")
	@ResponseBody
	public JsonResponse<List<ProductInventoryJson>> searchProduct(@RequestParam String productName, 
																  @RequestParam Integer categoryId) 
																  throws CategoryNotFoundException{
		
		JsonResponse<List<ProductInventoryJson>> response = new JsonResponse<List<ProductInventoryJson>>();
		List<ProductInventoryJson> productInventoryJson = new ArrayList<>();
		List<Product> products = null;
		Product product = productName.isEmpty()? null : new Product();
		Category category = categoryId.equals(0)? null : categoryService.find(categoryId);
		int displayStockLimit = inventoryService.getDisplayStockLimit();
		
		if(product == null && category == null)		products = productService.list();
		if(product == null && category != null)		products = productService.listByCategory(category);
		if(product != null && category == null)		products = productService.listByName(productName);
		
		if(product != null && category != null)	{
			
			product.setName(productName);
			product.setCategory(category);
			products = productService.findByProduct(product);
		}
		
		for(Product p : products) {
			
			ProductInventoryBean bean = new ProductInventoryBean();
			int stocks = p.getInventory().getStocks();
			String inventoryStatus = "";
			
			if(stocks >= displayStockLimit) {
				
				inventoryStatus = "In Stock";
			}
			
			else if(stocks < displayStockLimit && stocks >= 1) {
				
				inventoryStatus = "Only " + stocks + " left in stock";
			}
			
			else {
				
				inventoryStatus = "No Stock";
			}

			bean.setProduct(p);
			bean.setInventoryStatus(inventoryStatus);
			
			ProductInventoryJson json = new ProductInventoryJson(bean);
			productInventoryJson.add(json);
		}
		
		response.setStatus("success");
		response.setData(productInventoryJson);
		
		return response;
	}
	
	@PostMapping("/add")
	@ResponseBody
	public JsonResponse<?> add(@ModelAttribute("productForm") @Valid ProductForm productForm, 
							   BindingResult bindingResult) throws CategoryNotFoundException, 
							   ProductNotFoundException, UnavailableProductException, 
							   UnavailableCategoryException, IOException {
		
		if(bindingResult.hasErrors()) {
			
			Map<String, String> errors = bindingResult.getFieldErrors().stream().collect(
					Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
		  	);
			
			productForm.setPicture(null);
			
			JsonResponse<ProductForm> response = new JsonResponse<ProductForm>();
			response.setStatus("error");
			response.setData(productForm);
			response.setErrorMessages(errors);
			
			return response;
		}
		
		Category validCategory = categoryService.find(productForm.getCategoryId());
		MultipartFile file = productForm.getPicture();
		String picture = file.getOriginalFilename().isEmpty()? null : file.getOriginalFilename();;
		
		Product product = new Product();
		product.setName(productForm.getName().trim());
		product.setPrice(productForm.getPrice());
		product.setCategory(validCategory);
		product.setPicture(picture);
		
		productService.add(product);
		
		if(picture != null) {

			byte[] bytes = file.getBytes();
			File dir = new File("/home/katrina/Pictures");
			
			if(!dir.exists()) {
				
				dir.mkdirs();
			}
			
			File uploadFile = new File(dir.getAbsolutePath() + File.separator + picture);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
			
			stream.write(bytes);
			stream.close();
		}
		
		JsonResponse<ProductJson> response = new JsonResponse<ProductJson>();
		response.setStatus("success");
		response.setData(new ProductJson(product));
		response.setCustomMessage("Product is successfully added");
		
		return response;
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public JsonResponse<ProductJson> find(@PathVariable Integer id) throws ProductNotFoundException {
		
		JsonResponse<ProductJson> response = new JsonResponse<ProductJson>();
		response.setStatus("success");
		response.setData(new ProductJson(productService.find(id)));
		
		return response;
	}
	
	@PostMapping("/edit")
	@ResponseBody
	public JsonResponse<?> edit(@ModelAttribute("productForm") @Valid ProductForm productForm,
			  					BindingResult bindingResult) throws ProductNotFoundException, 
								CategoryNotFoundException, UnavailableProductException, IOException {

		if(bindingResult.hasErrors()) {
		
			Map<String, String> errors = bindingResult.getFieldErrors().stream().collect(
			    	Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
		  	);
			
			productForm.setPicture(null);
			
			JsonResponse<ProductForm> response = new JsonResponse<ProductForm>();
			response.setData(productForm);
			response.setStatus("error");
			response.setErrorMessages(errors);
			
			return response;
		}
		
		Product validProduct = productService.find(productForm.getId());
		Category validCategory = categoryService.find(productForm.getCategoryId());
		MultipartFile file = productForm.getPicture();
		String picture = file.getOriginalFilename().isEmpty()? validProduct.getPicture() : file.getOriginalFilename();
		
		validProduct.setName(productForm.getName().trim());
		validProduct.setPrice(productForm.getPrice());
		validProduct.setCategory(validCategory);
		validProduct.setPicture(picture);
		
		productService.save(validProduct);
		
		if(!file.getOriginalFilename().isEmpty()) {

			byte[] bytes = file.getBytes();
			File dir = new File("/home/katrina/Pictures");
			
			if(!dir.exists()) {
				
				dir.mkdirs();
			}
			
			File uploadFile = new File(dir.getAbsolutePath() + File.separator + picture);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
			
			stream.write(bytes);
			stream.close();
		}

		JsonResponse<ProductJson> response = new JsonResponse<ProductJson>();
		response.setData(new ProductJson(validProduct));
		response.setStatus("success");
		response.setCustomMessage("Product is successfully saved");
		
		return response;
	}
	
	@PostMapping("/remove")
	@ResponseBody
	public JsonResponse<ProductJson> remove(@RequestParam Integer id) throws ProductNotFoundException, 
											EntityDeletionException {
		
		Product product = productService.find(id);
		productService.remove(product);
		
		JsonResponse<ProductJson> response = new JsonResponse<ProductJson>();
		response.setData(new ProductJson(product));
		response.setStatus("success");
		response.setCustomMessage("Product is successfully removed");
		
		return response;
	}
	
	@GetMapping("/stockhistory/{id}")
	@ResponseBody
	public JsonResponse<List<StockHistoryJson>> listStockHistory(@PathVariable Integer id) 
																 throws ProductNotFoundException {
		
		JsonResponse<List<StockHistoryJson>> response = new JsonResponse<List<StockHistoryJson>>();
		Product product = productService.find(id);
		List<StockHistoryJson> stockHistoryJson = inventoryService.listStockHistory(product).stream()
												  .map(StockHistoryJson::new).collect(Collectors.toList());
		response.setStatus("success");
		response.setData(stockHistoryJson);
		
		return response;
	}
	
	@GetMapping("/orders/{id}")
	@ResponseBody
	public JsonResponse<List<OrderItemJson>> listOrders(@PathVariable Integer id) throws ProductNotFoundException {
		
		JsonResponse<List<OrderItemJson>> response = new JsonResponse<List<OrderItemJson>>();
		Product product = productService.find(id);
		List<OrderItemJson> orderItemJson = productService.listOrders(product).stream()
											.map(OrderItemJson::new).collect(Collectors.toList());
		response.setStatus("success");
		response.setData(orderItemJson);
		
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
	
	@ExceptionHandler(value = CategoryNotFoundException.class)
	@ResponseBody
	public JsonResponse<?> handleCategoryNotFoundException(HttpServletRequest request, Exception ex,
											  	  	 	   final RedirectAttributes redirectAttributes){
		
		JsonResponse<?> response = new JsonResponse<>();
		response.setStatus("error");
		response.setCustomMessage("Invalid category details");
		
		return response;
	}
	
	@ExceptionHandler(value = UnavailableProductException.class)
	@ResponseBody
	public JsonResponse<?> handleUnavailableProductException(HttpServletRequest request, Exception ex,
											  	  	 		 final RedirectAttributes redirectAttributes){
		
		JsonResponse<?> response = new JsonResponse<>();
		response.setStatus("error");
		response.setCustomMessage("Product name or picture is already taken, try other name or picture");
		
		return response;
	}
	
	@ExceptionHandler(value = EntityDeletionException.class)
	@ResponseBody
	public JsonResponse<?> handleEntityDeletionException(HttpServletRequest request, Exception ex,
											  	  	 	 final RedirectAttributes redirectAttributes){
		
		JsonResponse<?> response = new JsonResponse<>();
		response.setStatus("error");
		response.setCustomMessage("Product cannot be deleted");
		
		return response;
	}
	
	@ExceptionHandler(value = NumberFormatException.class)
	@ResponseBody
	public JsonResponse<?> handleNumberFormatException(HttpServletRequest request, Exception ex,
											  	  	   final RedirectAttributes redirectAttributes){
		
		JsonResponse<?> response = new JsonResponse<>();
		response.setStatus("error");
		response.setCustomMessage("Invalid product details");
		
		return response;
	}
}
