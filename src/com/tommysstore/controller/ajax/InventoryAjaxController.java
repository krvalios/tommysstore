package com.tommysstore.controller.ajax;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tommysstore.bean.form.StockHistoryForm;
import com.tommysstore.bean.json.InventoryJson;
import com.tommysstore.bean.json.JsonResponse;
import com.tommysstore.domain.Inventory;
import com.tommysstore.domain.StockHistory;
import com.tommysstore.domain.User;
import com.tommysstore.exception.ProductNotFoundException;
import com.tommysstore.service.InventoryService;

@Controller
@RequestMapping(value = "/ajax/inventory", produces = "application/json")
public class InventoryAjaxController {

	@Autowired
	private InventoryService inventoryService;
	
	@GetMapping
	@ResponseBody
	public JsonResponse<List<InventoryJson>> list(){
		
		JsonResponse<List<InventoryJson>> response = new JsonResponse<List<InventoryJson>>();
		List<InventoryJson> inventoryJson = inventoryService.list().stream().map(InventoryJson::new)
											.collect(Collectors.toList());
		response.setStatus("success");
		response.setData(inventoryJson);
		
		return response;
	}
	
	@GetMapping("/low")
	@ResponseBody
	public JsonResponse<List<InventoryJson>> listByStock(){
		
		JsonResponse<List<InventoryJson>> response = new JsonResponse<List<InventoryJson>>();
		List<InventoryJson> inventoryJson = inventoryService.listByStock().stream().map(InventoryJson::new)
											.collect(Collectors.toList());
		response.setStatus("success");
		response.setData(inventoryJson);
		
		return response;
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public JsonResponse<InventoryJson> find(@PathVariable Integer id) throws ProductNotFoundException {
		
		JsonResponse<InventoryJson> response = new JsonResponse<InventoryJson>();
		response.setStatus("success");
		response.setData(new InventoryJson(inventoryService.find(id)));
		
		return response;
	}
	
	@PostMapping("/restock")
	@ResponseBody
	public JsonResponse<?> restock(@ModelAttribute("inventoryForm") @Valid StockHistoryForm stockHistoryForm, 
			 			  		   BindingResult bindingResult, HttpSession session) throws ProductNotFoundException {
		
		if(bindingResult.hasErrors()) {
			
			Map<String, String> errors = bindingResult.getFieldErrors().stream().collect(
					Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
		  	);
			
			JsonResponse<StockHistoryForm> response = new JsonResponse<StockHistoryForm>();
			response.setStatus("error");
			response.setData(stockHistoryForm);
			response.setErrorMessages(errors);
			
			return response;
		}
		
		User loginUser = (User) session.getAttribute("loginUser");
		Integer inventoryId = stockHistoryForm.getInventoryId();
		Inventory validInventory = inventoryService.find(inventoryId);
		
		StockHistory stockHistory = new StockHistory();
		stockHistory.setUser(loginUser);
		stockHistory.setInventory(validInventory);
		stockHistory.setStockAdded(stockHistoryForm.getStockAdded());
		
		inventoryService.addStock(stockHistory);
		
		JsonResponse<InventoryJson> response = new JsonResponse<InventoryJson>();
		response.setStatus("success");
		response.setData(new InventoryJson(validInventory));
		response.setCustomMessage("Stock is successfully added");
		
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
	
	@ExceptionHandler(value = NumberFormatException.class)
	@ResponseBody
	public JsonResponse<?> handleNumberFormatException(HttpServletRequest request, Exception ex,
											  	  	   final RedirectAttributes redirectAttributes){
		
		JsonResponse<?> response = new JsonResponse<>();
		response.setStatus("error");
		response.setCustomMessage("Invalid inventory details");
		
		return response;
	}
}
