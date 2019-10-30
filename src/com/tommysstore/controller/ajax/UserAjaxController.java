package com.tommysstore.controller.ajax;

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

import com.tommysstore.bean.form.LoginForm;
import com.tommysstore.bean.form.UserForm;
import com.tommysstore.bean.json.CountryJson;
import com.tommysstore.bean.json.CreditCardJson;
import com.tommysstore.bean.json.JsonResponse;
import com.tommysstore.bean.json.ShippingAddressJson;
import com.tommysstore.bean.json.UserJson;
import com.tommysstore.constant.Country;
import com.tommysstore.constant.UserRole;
import com.tommysstore.domain.Cart;
import com.tommysstore.domain.CreditCard;
import com.tommysstore.domain.ShippingAddress;
import com.tommysstore.domain.User;
import com.tommysstore.exception.UnavailableEmailException;
import com.tommysstore.exception.UserNotFoundException;
import com.tommysstore.service.UserService;

@Controller
@RequestMapping(value = "/ajax/user", produces = "application/json")
public class UserAjaxController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	@ResponseBody
	public JsonResponse<?> login(@ModelAttribute("loginForm") @Valid LoginForm login, BindingResult bindingResult, 
								 HttpSession session) throws UserNotFoundException {
		
		JsonResponse<LoginForm> response = new JsonResponse<LoginForm>();
		
		if(bindingResult.hasErrors()) {
			
			Map<String, String> errors = bindingResult.getFieldErrors().stream().collect(
					Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
		  	);
			
			response.setStatus("error");
			response.setData(login);
			response.setErrorMessages(errors);
			
			return response;
		}

		User validUser = userService.validateLogin(login);
		session.setAttribute("loginUser", validUser);
		
		response.setStatus("success");
		
		Cart cart = (Cart) session.getAttribute("cart");
		
		if(cart != null && session.getAttribute("path") != null) {
			
			response.setLocation("customer/cart/checkout");
			
			return response;
		}
		
		response.setLocation("/TommysStore");
		
		return response;
	}
	
	@PostMapping("/register")
	@ResponseBody
	public JsonResponse<UserForm> processRegister(@ModelAttribute("registerForm") @Valid UserForm userBean, 
												  BindingResult bindingResult, HttpSession session) 
												  throws UnavailableEmailException {
		
		JsonResponse<UserForm> response = new JsonResponse<UserForm>();
		response.setData(userBean);
		
		if (bindingResult.hasErrors()) {
			
			Map<String, String> errors = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				
				String field = error.getField();
				String message = error.getDefaultMessage();
				
				if(errors.containsKey(field)) {
					
					String value = errors.get(field);
					errors.replace(field, value + " | " + message);
					
					continue;
				}
				
				errors.put(field, message);
			}
			
			response.setStatus("error");
			response.setErrorMessages(errors);
			
			return response;
		}
		
		String password = userBean.getPassword();
		String confirmPassword = userBean.getConfirmPassword();
		
		if(!password.equals(confirmPassword)) {
			
			response.setStatus("error");
			response.setCustomMessage("Password confirmation is not matched");
			
			return response;
		}
		
		User customer = new User();
		customer.setEmail(userBean.getEmail());
		customer.setPassword(userBean.getPassword());
		customer.setFirstname(userBean.getFirstname());
		customer.setLastname(userBean.getLastname());
		customer.setContactNumber(userBean.getContactNumber());
		customer.setRole(UserRole.CUSTOMER);
		
		userService.save(customer);
		session.setAttribute("loginUser", customer);
		
		response.setStatus("success");
		
		Cart cart = (Cart) session.getAttribute("cart");
		
		if(cart != null) {
			
			response.setLocation("customer/cart/checkout");
			
			return response;
		}
		
		response.setLocation("popular");
		
		return response;
	}
	
	@GetMapping
	@ResponseBody
	public JsonResponse<List<UserJson>> list(){
		
		JsonResponse<List<UserJson>> response = new JsonResponse<List<UserJson>>();
		List<UserJson> usersJson = userService.list().stream().map(UserJson::new).collect(Collectors.toList());
		response.setStatus("success");
		response.setData(usersJson);
		
		return response;
	}
	
	@GetMapping("/new")
	@ResponseBody
	public JsonResponse<List<UserJson>> listNewUsers(){
		
		JsonResponse<List<UserJson>> response = new JsonResponse<List<UserJson>>();
		List<UserJson> usersJson = userService.listNewUsers().stream().map(UserJson::new).collect(Collectors.toList());
		response.setStatus("success");
		response.setData(usersJson);
		
		return response;
	}
	
	@GetMapping("/address")
	@ResponseBody
	public JsonResponse<List<ShippingAddressJson>> listAddress(HttpSession session){
		
		JsonResponse<List<ShippingAddressJson>> response = new JsonResponse<List<ShippingAddressJson>>();
		User loginUser = (User) session.getAttribute("loginUser");
		List<ShippingAddressJson> shippingAddressJson = userService.listShippingAddresses(loginUser).stream()
														.map(ShippingAddressJson::new).collect(Collectors.toList());
		response.setStatus("success");
		response.setData(shippingAddressJson);
		
		return response;
	}
	
	@GetMapping("/creditcard")
	@ResponseBody
	public JsonResponse<List<CreditCardJson>> listCreditCard(HttpSession session){
		
		JsonResponse<List<CreditCardJson>> response = new JsonResponse<List<CreditCardJson>>();
		User loginUser = (User) session.getAttribute("loginUser");
		List<CreditCardJson> creditCardJson = userService.listCreditCards(loginUser).stream()
											  .map(CreditCardJson::new).collect(Collectors.toList());
		response.setStatus("success");
		response.setData(creditCardJson);
		
		return response;
	}
	
	@GetMapping("/address/country")
	@ResponseBody
	public JsonResponse<List<CountryJson>> listCountry(){

		JsonResponse<List<CountryJson>> response = new JsonResponse<List<CountryJson>>();
		List<CountryJson> countries = new ArrayList<>();
		
		for(Country country : Country.values()) {

			countries.add(new CountryJson(country));
		}
		
		response.setStatus("success");
		response.setData(countries);
		
		return response;
	}
	
	@PostMapping("/address/add")
	@ResponseBody
	public JsonResponse<?> addAddress(@ModelAttribute("addressForm") @Valid ShippingAddress shippingAddress, 
									  BindingResult bindingResult, HttpSession session) {

		if(bindingResult.hasErrors()) {
		
			Map<String, String> errors = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				
				String field = error.getField();
				String message = error.getDefaultMessage();
				
				if(errors.containsKey(field)) {
					
					String value = errors.get(field);
					errors.replace(field, value + " | " + message);
					
					continue;
				}
				
				errors.put(field, message);
			}

			JsonResponse<ShippingAddress> response = new JsonResponse<ShippingAddress>();
			response.setStatus("error");
			response.setData(shippingAddress);
			response.setErrorMessages(errors);
			
			return response;
		}
		
		User loginUser = (User) session.getAttribute("loginUser");
		shippingAddress.setUser(loginUser);
		
		userService.addShippingAddress(shippingAddress);

		JsonResponse<ShippingAddressJson> successResponse = new JsonResponse<ShippingAddressJson>();
		successResponse.setStatus("success");
		successResponse.setData(new ShippingAddressJson(shippingAddress));
		successResponse.setCustomMessage("Shipping Address is successfully added");
		
		return successResponse;
	}
	
	@PostMapping("/creditcard/add")
	@ResponseBody
	public JsonResponse<?> addCreditCard(@ModelAttribute("creditCardForm") @Valid CreditCard creditCard, 
									  	 BindingResult bindingResult, HttpSession session) {

		if(bindingResult.hasErrors()) {
		
			Map<String, String> errors = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				
				String field = error.getField();
				String message = error.getDefaultMessage();
				
				if(errors.containsKey(field)) {
					
					String value = errors.get(field);
					errors.replace(field, value + " | " + message);
					
					continue;
				}
				
				errors.put(field, message);
			}

			JsonResponse<CreditCard> response = new JsonResponse<CreditCard>();
			response.setStatus("error");
			response.setData(creditCard);
			response.setErrorMessages(errors);
			
			return response;
		}
		
		User loginUser = (User) session.getAttribute("loginUser");
		creditCard.setUser(loginUser);
		
		userService.addCreditCard(creditCard);

		JsonResponse<CreditCardJson> successResponse = new JsonResponse<CreditCardJson>();
		successResponse.setStatus("success");
		successResponse.setData(new CreditCardJson(creditCard));
		successResponse.setCustomMessage("Credit card is successfully added");
		
		return successResponse;
	}
	
	@PostMapping("/add")
	@ResponseBody
	public JsonResponse<?> addAdmin(@ModelAttribute("adminForm") @Valid UserForm userForm, BindingResult bindingResult) 
									throws UnavailableEmailException {

		JsonResponse<UserForm> response = new JsonResponse<UserForm>();
		response.setData(userForm);
		
		if(bindingResult.hasErrors()) {
		
			Map<String, String> errors = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				
				String field = error.getField();
				String message = error.getDefaultMessage();
				
				if(errors.containsKey(field)) {
					
					String value = errors.get(field);
					errors.replace(field, value + " | " + message);
					
					continue;
				}
				
				errors.put(field, message);
			}

			response.setStatus("error");
			response.setErrorMessages(errors);
			
			return response;
		}
		
		String password = userForm.getPassword();
		String confirmPassword = userForm.getConfirmPassword();
		
		if(!password.equals(confirmPassword)) {

			response.setStatus("error");
			response.setCustomMessage("Password confirmation is not matched");
			
			return response;
		}
		
		User admin = new User();
		admin.setEmail(userForm.getEmail().trim());
		admin.setPassword(userForm.getPassword());
		admin.setFirstname(userForm.getFirstname());
		admin.setLastname(userForm.getLastname());
		admin.setContactNumber(userForm.getContactNumber());
		admin.setRole(UserRole.ADMIN);
		
		userService.save(admin);

		JsonResponse<UserJson> successResponse = new JsonResponse<UserJson>();
		successResponse.setStatus("success");
		successResponse.setData(new UserJson(admin));
		successResponse.setCustomMessage("New administrator is successfully added");
		
		return successResponse;
	}
	
	@PostMapping("/filter")
	@ResponseBody
	public JsonResponse<List<User>> filter(@RequestParam String userType) {

		JsonResponse<List<User>> response = new JsonResponse<List<User>>();
		List<User> users = new ArrayList<>();
		
		if(userType.equals("") || userType.equals("ALL")) {
			
			users = userService.list();
		}
		
		if(userType.equals("CUSTOMER") || userType.equals("ADMIN")) { 	
			
			users = userService.listByRole(UserRole.valueOf(userType));
		}
		
		response.setStatus("success");
		response.setData(users);
		
		return response;
	}
	
	@ExceptionHandler(value = UserNotFoundException.class)
	@ResponseBody
	public JsonResponse<?> handleUserNotFoundException(HttpServletRequest request, Exception ex,
											  	  	   final RedirectAttributes redirectAttributes){
		
		JsonResponse<?> response = new JsonResponse<>();
		response.setStatus("error");
		response.setCustomMessage("Username and password do not match");
		
		return response;
	}
	
	@ExceptionHandler(value = UnavailableEmailException.class)
	@ResponseBody
	public JsonResponse<?> handleUnavailableEmailException(HttpServletRequest request, Exception ex,
											  	  	 	   final RedirectAttributes redirectAttributes){
		
		JsonResponse<?> response = new JsonResponse<>();
		response.setStatus("error");
		response.setCustomMessage("Email is unavailable, try other email");
		
		return response;
	}
}
