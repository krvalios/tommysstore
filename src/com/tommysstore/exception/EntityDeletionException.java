package com.tommysstore.exception;

public class EntityDeletionException extends Exception {

	private static final long serialVersionUID = 1L;

	public EntityDeletionException() {
		
		super();
		
	}

	public EntityDeletionException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		
		super(arg0, arg1, arg2, arg3);
		
	}

	public EntityDeletionException(String arg0, Throwable arg1) {
		
		super(arg0, arg1);
		
	}

	public EntityDeletionException(String arg0) {
		
		super(arg0);
		
	}

	public EntityDeletionException(Throwable arg0) {
		
		super(arg0);
		
	}
}
