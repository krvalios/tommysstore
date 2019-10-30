package com.tommysstore.bean.json;

import com.tommysstore.domain.ShippingAddress;

public class ShippingAddressJson {
	
	private Integer id;
	private Integer userId;
	private String address1;
	private String address2;
	private String zipCode;
	private String country;
	
	public ShippingAddressJson() {
		
	}
	
	public ShippingAddressJson(ShippingAddress shippingAddress) {
		
		this.id = shippingAddress.getId();
		this.userId = shippingAddress.getUser().getId();
		this.address1 = shippingAddress.getAddress1();
		this.address2 = shippingAddress.getAddress2();
		this.zipCode = shippingAddress.getZipCode();
		this.country = shippingAddress.getCountry().getName();
	}

	public Integer getId() {
		
		return id;
	}

	public void setId(Integer id) {
		
		this.id = id;
	}

	public Integer getUserId() {
		
		return userId;
	}

	public void setUserId(Integer userId) {
		
		this.userId = userId;
	}

	public String getAddress1() {
		
		return address1;
	}

	public void setAddress1(String address1) {
		
		this.address1 = address1;
	}

	public String getAddress2() {
		
		return address2;
	}

	public void setAddress2(String address2) {
		
		this.address2 = address2;
	}

	public String getZipCode() {
		
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		
		this.zipCode = zipCode;
	}

	public String getCountry() {
		
		return country;
	}

	public void setCountry(String country) {
		
		this.country = country;
	}
}
