package com.quant.exception;

public class EmployeeNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6229381736025480184L;

	
	public EmployeeNotFoundException() {
		super ();
	}
	
	public EmployeeNotFoundException(String msg) {
		super (msg);
	}
}
