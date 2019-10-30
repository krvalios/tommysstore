package com.tommysstore.bean.json;

import com.tommysstore.constant.UserRole;
import com.tommysstore.domain.User;

public class UserJson {
	
	private String email;
	private String firstname;
	private String lastname;
	private String contactNumber;
	private UserRole role;
	
	public UserJson() {
		
	}
	
	public UserJson(User user) {
		
		this.email = user.getEmail();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.contactNumber = user.getContactNumber();
		this.role = user.getRole();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		
		this.email = email;
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
	
	public UserRole getRole() {
		
		return role;
	}
	
	public void setRole(UserRole role) {
		
		this.role = role;
	}
}
