package com.tommysstore.exception;

public class CartItemNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public CartItemNotFoundException() {
		
		super();
		
	}

	public CartItemNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		
		super(arg0, arg1, arg2, arg3);
		
	}

	public CartItemNotFoundException(String arg0, Throwable arg1) {
		
		super(arg0, arg1);
		
	}

	public CartItemNotFoundException(String arg0) {
		
		super(arg0);
		
	}

	public CartItemNotFoundException(Throwable arg0) {
		
		super(arg0);
		
	}
}
