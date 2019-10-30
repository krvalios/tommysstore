package com.tommysstore.exception;

public class UnavailableProductException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnavailableProductException() {
		
		super();
		
	}

	public UnavailableProductException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		
		super(arg0, arg1, arg2, arg3);
		
	}

	public UnavailableProductException(String arg0, Throwable arg1) {
		
		super(arg0, arg1);
		
	}

	public UnavailableProductException(String arg0) {
		
		super(arg0);
		
	}

	public UnavailableProductException(Throwable arg0) {
		
		super(arg0);
		
	}
}
