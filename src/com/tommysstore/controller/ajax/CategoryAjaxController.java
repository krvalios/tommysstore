package com.tommysstore.controller.ajax;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tommysstore.bean.json.CategoryJson;
import com.tommysstore.bean.json.JsonResponse;
import com.tommysstore.bean.json.ProductJson;
import com.tommysstore.domain.Category;
import com.tommysstore.exception.CategoryNotFoundException;
import com.tommysstore.exception.EntityDeletionException;
import com.tommysstore.exception.UnavailableCategoryException;
import com.tommysstore.service.CategoryService;
import com.tommysstore.service.ProductService;

@Controller
@RequestMapping(value = "/ajax/category", produces = "application/json")
public class CategoryAjaxController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;
	
	@GetMapping
	@ResponseBody
	public JsonResponse<List<CategoryJson>> list(){

		JsonResponse<List<CategoryJson>> response = new JsonResponse<List<CategoryJson>>();
		List<CategoryJson> categoryJson = categoryService.list().stream()
										  .map(CategoryJson::new).collect(Collectors.toList());
		response.setStatus("success");
		response.setData(categoryJson);
		
		return response;
	}
	
	@GetMapping("/{id}/products")
	@ResponseBody
	public JsonResponse<List<ProductJson>> listProductsByCategory(@PathVariable Integer id) 
																  throws CategoryNotFoundException{
		
		JsonResponse<List<ProductJson>> response = new JsonResponse<List<ProductJson>>();
		Category category = categoryService.find(id);
		List<ProductJson> productJson = productService.listByCategory(category).stream()
										.map(ProductJson::new).collect(Collectors.toList());
		response.setStatus("success");
		response.setData(productJson);
		
		return response;
	}
	
	@PostMapping("/add")
	@ResponseBody
	public JsonResponse<?> add(@ModelAttribute("categoryForm") @Valid Category category, 
							   BindingResult bindingResult) throws UnavailableCategoryException {
		
		if(bindingResult.hasErrors()){
			
			Map<String, String> errors = bindingResult.getFieldErrors().stream().collect(
					Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
		  	);

			JsonResponse<Category> response = new JsonResponse<Category>();
			response.setStatus("error");
			response.setData(category);
			response.setErrorMessages(errors);
			
			return response;
		}
		
		category.setName(category.getName().trim());
		categoryService.add(category);

		JsonResponse<CategoryJson> response = new JsonResponse<CategoryJson>();
		response.setStatus("success");
		response.setData(new CategoryJson(category));
		response.setCustomMessage("Category is successfully added");
		
		return response;
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public JsonResponse<CategoryJson> find(@PathVariable Integer id) throws CategoryNotFoundException {
		
		JsonResponse<CategoryJson> response = new JsonResponse<CategoryJson>();
		response.setStatus("success");
		response.setData(new CategoryJson(categoryService.find(id)));
		
		return response;
	}
	
	@PostMapping("/edit")
	@ResponseBody
	public JsonResponse<?> edit(@ModelAttribute("categoryForm") @Valid Category category,
							    BindingResult bindingResult) throws CategoryNotFoundException, 
								UnavailableCategoryException {
		
		if(bindingResult.hasErrors()) {
		
			Map<String, String> errors = bindingResult.getFieldErrors().stream().collect(
			    	Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
		  	);

			JsonResponse<Category> response = new JsonResponse<Category>();
			response.setData(category);
			response.setStatus("error");
			response.setErrorMessages(errors);
			
			return response;
		}
		
		Category validCategory = categoryService.find(category.getId());
		validCategory.setName(category.getName().trim());
		
		categoryService.save(validCategory);

		JsonResponse<CategoryJson> response = new JsonResponse<CategoryJson>();
		response.setData(new CategoryJson(validCategory));
		response.setStatus("success");
		response.setCustomMessage("Category is successfully saved");
		
		return response;
	}
	
	@PostMapping("/remove")
	@ResponseBody
	public JsonResponse<?> remove(@ModelAttribute("categoryForm") @Valid Category category,
							  	  BindingResult bindingResult) throws EntityDeletionException, 
								  CategoryNotFoundException {
		
		if(bindingResult.hasErrors()) {
			
			Map<String, String> errors = bindingResult.getFieldErrors().stream().collect(
			    	Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
		  	);

			JsonResponse<Category> response = new JsonResponse<Category>();
			response.setData(category);
			response.setStatus("error");
			response.setErrorMessages(errors);
			
			return response;
		}
		
		Category validCategory = categoryService.find(category.getId());
		categoryService.remove(validCategory);

		JsonResponse<CategoryJson> response = new JsonResponse<CategoryJson>();
		response.setStatus("success");
		response.setData(new CategoryJson(validCategory));
		response.setCustomMessage("Category is successfully removed");
		
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
	
	@ExceptionHandler(value = UnavailableCategoryException.class)
	@ResponseBody
	public JsonResponse<?> handleUnavailableCategoryException(HttpServletRequest request, Exception ex,
											  	  	 		  final RedirectAttributes redirectAttributes){
		
		JsonResponse<?> response = new JsonResponse<>();
		response.setStatus("error");
		response.setCustomMessage("Category name is unavailable, try other name");
		
		return response;
	}
	
	@ExceptionHandler(value = EntityDeletionException.class)
	@ResponseBody
	public JsonResponse<?> handleEntityDeletionException(HttpServletRequest request, Exception ex,
											  	  	 	 final RedirectAttributes redirectAttributes){
		
		JsonResponse<?> response = new JsonResponse<>();
		response.setStatus("error");
		response.setCustomMessage("Category cannot be deleted");
		
		return response;
	}
	
	@ExceptionHandler(value = NumberFormatException.class)
	@ResponseBody
	public JsonResponse<?> handleNumberFormatException(HttpServletRequest request, Exception ex,
											  	  	   final RedirectAttributes redirectAttributes){
		
		JsonResponse<?> response = new JsonResponse<>();
		response.setStatus("error");
		response.setCustomMessage("Invalid category details");
		
		return response;
	}
}