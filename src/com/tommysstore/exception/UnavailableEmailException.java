package com.tommysstore.exception;

public class UnavailableEmailException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnavailableEmailException() {
		
		super();
		
	}

	public UnavailableEmailException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		
		super(arg0, arg1, arg2, arg3);
		
	}

	public UnavailableEmailException(String arg0, Throwable arg1) {
		
		super(arg0, arg1);
		
	}

	public UnavailableEmailException(String arg0) {
		
		super(arg0);
		
	}

	public UnavailableEmailException(Throwable arg0) {
		
		super(arg0);
		
	}
}
