package com.tommysstore.bean.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class UserForm {
	
	@NotEmpty(message = "Email is required")
	private String email;

	@NotEmpty(message = "Password is required")
	private String password;
	
	@NotEmpty(message = "Password confirmation is required")
	private String confirmPassword;

	@NotEmpty(message = "First name is required")
	@Pattern(regexp = "^[A-Za-z ]+", message = "Must contain letters only")
	private String firstname;

	@NotEmpty(message = "Last name is required")
	@Pattern(regexp = "^[A-Za-z ]+", message = "Must contain letters only")
	private String lastname;

	@NotEmpty(message = "Contact number is required")
	@Pattern(regexp = "^[0-9+]+", message = "Must contain numbers only")
	private String contactNumber;
	
	public String getEmail() {
		
		return email;
	}
	
	public void setEmail(String email) {
		
		this.email = email;
	}
	
	public String getPassword() {
		
		return password;
	}
	
	public void setPassword(String password) {
		
		this.password = password;
	}
	
	public String getConfirmPassword() {
		
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		
		this.confirmPassword = confirmPassword;
	}

	public String getFirstname() {
		
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		
		this.firstname = firstname;
	}
	
	public String getLastname() {
		
		return lastname;
	}
	
	public void setLastname(String lastname) {
		
		this.lastname = lastname;
	}
	
	public String getContactNumber() {
		
		return contactNumber;
	}
	
	public void setContactNumber(String contactNumber) {
		
		this.contactNumber = contactNumber;
	}
}
