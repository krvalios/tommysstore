package com.tommysstore.bean.form;

import javax.validation.constraints.NotNull;

public class OrderForm {
	
	@NotNull(message = "Shipping Address is required")
	private Integer shippingAddressId;

	@NotNull(message = "Payment method is required")
	private Integer creditCardId;

	public Integer getShippingAddressId() {
		
		return shippingAddressId;
	}

	public void setShippingAddressId(Integer shippingAddressId) {
		
		this.shippingAddressId = shippingAddressId;
	}

	public Integer getCreditCardId() {
		
		return creditCardId;
	}

	public void setCreditCardId(Integer creditCardId) {
		
		this.creditCardId = creditCardId;
	}
}
