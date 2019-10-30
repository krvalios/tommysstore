package com.tommysstore.exception;

public class CreditCardNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public CreditCardNotFoundException() {
		
		super();
		
	}

	public CreditCardNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		
		super(arg0, arg1, arg2, arg3);
		
	}

	public CreditCardNotFoundException(String arg0, Throwable arg1) {
		
		super(arg0, arg1);
		
	}

	public CreditCardNotFoundException(String arg0) {
		
		super(arg0);
		
	}

	public CreditCardNotFoundException(Throwable arg0) {
		
		super(arg0);
		
	}
}
