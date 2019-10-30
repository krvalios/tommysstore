package com.tommysstore.exception;

public class InsufficientStockException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InsufficientStockException() {
		
		super();
		
	}

	public InsufficientStockException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		
		super(arg0, arg1, arg2, arg3);
		
	}

	public InsufficientStockException(String arg0, Throwable arg1) {
		
		super(arg0, arg1);
		
	}

	public InsufficientStockException(String arg0) {
		
		super(arg0);
		
	}

	public InsufficientStockException(Throwable arg0) {
		
		super(arg0);
		
	}
}