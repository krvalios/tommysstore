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

import com.tommysstore.bean.json.CategoryJson;
import com.tommysstore.domain.Category;
import com.tommysstore.exception.CategoryNotFoundException;
import com.tommysstore.exception.EntityDeletionException;
import com.tommysstore.exception.UnavailableCategoryException;
import com.tommysstore.service.CategoryService;

@Controller
@RequestMapping(value = "/categories", produces = "application/json")
public class CategoryRestController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<CategoryJson> list() {

		return categoryService.list().stream().map(CategoryJson::new).collect(Collectors.toList());
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<CategoryJson> create(@RequestBody Category category) 
											   throws UnavailableCategoryException {
		
		category.setName(category.getName().trim());
		categoryService.add(category);
		
		return new ResponseEntity<>(new CategoryJson(category), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<CategoryJson> update(@PathVariable("id") Integer id, @RequestBody Category category) 
									throws UnavailableCategoryException, CategoryNotFoundException {
		System.out.println(category.getName());
		Category validCategory = categoryService.find(id);
		validCategory.setName(category.getName().trim());
		
		categoryService.save(validCategory);

		return new ResponseEntity<>(new CategoryJson(validCategory), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<CategoryJson> delete(@PathVariable("id") Integer id) throws EntityDeletionException, 
											   CategoryNotFoundException {
		
		Category validCategory = categoryService.find(id);
		categoryService.remove(validCategory);
		
		return new ResponseEntity<>(new CategoryJson(validCategory), HttpStatus.OK);
	}
	
	@ExceptionHandler(value = CategoryNotFoundException.class)
	@ResponseBody
	public ResponseEntity<String> handleCategoryNotFoundException(){

		return new ResponseEntity<>("Invalid category details", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = UnavailableCategoryException.class)
	@ResponseBody
	public ResponseEntity<String> handleUnavailableCategoryException(){

		return new ResponseEntity<>("Category name is unavailable, try other name", HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(value = EntityDeletionException.class)
	@ResponseBody
	public ResponseEntity<String> handleEntityDeletionException(){

		return new ResponseEntity<>("Category cannot be deleted", HttpStatus.NOT_ACCEPTABLE);
	}

}
