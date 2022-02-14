package com.quant.exception;

public class EmployeeMailAlreadyExists extends Exception{
	

	private static final long serialVersionUID = 1923843198717265597L;

	
	public EmployeeMailAlreadyExists() {
		super ();
	}
	
	public EmployeeMailAlreadyExists(String msg) {
		super (msg);
	}
}
