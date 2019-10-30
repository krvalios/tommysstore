package com.tommysstore.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tommysstore.bean.form.LoginForm;
import com.tommysstore.bean.form.UserForm;

@Controller
public class LoginController {

	@GetMapping(value="/login")
	public String viewLogin(Model model) {
		
		model.addAttribute("loginForm", new LoginForm());
		
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model, HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/login";
	}
	
	@GetMapping("/register")
	public String viewRegister(Model model) {
		
		model.addAttribute("registerForm", new UserForm());
		
		return "register";
	}
	
	@GetMapping("/popular")
	public String viewPopularProducts(Model model) {
		
		return "customer_index";
	}
}
