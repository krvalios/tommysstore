package com.tommysstore.exception;

public class ShippingAddressNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ShippingAddressNotFoundException() {
		
		super();
		
	}

	public ShippingAddressNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		
		super(arg0, arg1, arg2, arg3);
		
	}

	public ShippingAddressNotFoundException(String arg0, Throwable arg1) {
		
		super(arg0, arg1);
		
	}

	public ShippingAddressNotFoundException(String arg0) {
		
		super(arg0);
		
	}

	public ShippingAddressNotFoundException(Throwable arg0) {
		
		super(arg0);
		
	}
}
