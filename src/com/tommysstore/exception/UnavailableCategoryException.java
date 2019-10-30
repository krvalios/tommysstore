package com.tommysstore.exception;

public class UnavailableCategoryException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnavailableCategoryException() {
		
		super();
		
	}

	public UnavailableCategoryException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		
		super(arg0, arg1, arg2, arg3);
		
	}

	public UnavailableCategoryException(String arg0, Throwable arg1) {
		
		super(arg0, arg1);
		
	}

	public UnavailableCategoryException(String arg0) {
		
		super(arg0);
		
	}

	public UnavailableCategoryException(Throwable arg0) {
		
		super(arg0);
		
	}
}
