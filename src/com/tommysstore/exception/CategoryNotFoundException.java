package com.tommysstore.exception;

public class CategoryNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public CategoryNotFoundException() {
		
		super();
		
	}

	public CategoryNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		
		super(arg0, arg1, arg2, arg3);
		
	}

	public CategoryNotFoundException(String arg0, Throwable arg1) {
		
		super(arg0, arg1);
		
	}

	public CategoryNotFoundException(String arg0) {
		
		super(arg0);
		
	}

	public CategoryNotFoundException(Throwable arg0) {
		
		super(arg0);
		
	}
}
