package com.tommysstore.controller.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tommysstore.bean.form.ProductForm;
import com.tommysstore.bean.json.ProductJson;
import com.tommysstore.domain.Category;
import com.tommysstore.domain.Product;
import com.tommysstore.exception.CategoryNotFoundException;
import com.tommysstore.exception.EntityDeletionException;
import com.tommysstore.exception.ProductNotFoundException;
import com.tommysstore.exception.UnavailableProductException;
import com.tommysstore.service.CategoryService;
import com.tommysstore.service.ProductService;

@Controller
@RequestMapping(value = "/products", produces = "application/json")
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ProductJson> list() {

		return productService.list().stream().map(ProductJson::new).collect(Collectors.toList());
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<ProductJson> create(@RequestBody ProductForm productForm) 
											  throws UnavailableProductException, ProductNotFoundException, 
											  CategoryNotFoundException {
		
		Category validCategory = categoryService.find(productForm.getCategoryId());
		Product product = new Product();
		product.setName(productForm.getName().trim());
		product.setPrice(productForm.getPrice());
		product.setCategory(validCategory);
		
		productService.add(product);
		
		return new ResponseEntity<>(new ProductJson(product), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<ProductJson> update(@PathVariable("id") Integer id, @RequestBody ProductForm productForm) 
									throws ProductNotFoundException, CategoryNotFoundException, 
									UnavailableProductException {

		Product validProduct = productService.find(productForm.getId());
		Category validCategory = categoryService.find(productForm.getCategoryId());
		
		validProduct.setName(productForm.getName().trim());
		validProduct.setPrice(productForm.getPrice());
		validProduct.setCategory(validCategory);
		
		productService.save(validProduct);

		return new ResponseEntity<>(new ProductJson(validProduct), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<ProductJson> delete(@PathVariable("id") Integer id) throws EntityDeletionException, 
											   ProductNotFoundException {
		
		Product validProduct = productService.find(id);
		productService.remove(validProduct);
		
		return new ResponseEntity<>(new ProductJson(validProduct), HttpStatus.OK);
	}
	
	@ExceptionHandler(value = ProductNotFoundException.class)
	@ResponseBody
	public ResponseEntity<String> handleProductNotFoundException(){

		return new ResponseEntity<>("Invalid product details", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = UnavailableProductException.class)
	@ResponseBody
	public ResponseEntity<String> handleUnavailableProductException(){

		return new ResponseEntity<>("Product name is unavailable, try other name", HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(value = EntityDeletionException.class)
	@ResponseBody
	public ResponseEntity<String> handleEntityDeletionException(){

		return new ResponseEntity<>("Product cannot be deleted", HttpStatus.NOT_ACCEPTABLE);
	}

}
