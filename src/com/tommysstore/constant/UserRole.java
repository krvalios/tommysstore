package com.tommysstore.constant;

public enum UserRole {
	
	ADMIN("Admin"),
	CUSTOMER("Customer");
	
	private String role;

	UserRole(String role) {
		
		this.role = role;
	}
	
	public String getRole() {
		
		return this.role;
	}
}
