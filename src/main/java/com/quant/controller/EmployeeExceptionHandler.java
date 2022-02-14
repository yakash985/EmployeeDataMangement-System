package com.quant.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.quant.entity.ErrorResponse;
import com.quant.exception.EmployeeMailAlreadyExists;
import com.quant.exception.EmployeeNotFoundException;

@RestControllerAdvice
public class EmployeeExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleEmployeeMailAlreadyExists(EmployeeMailAlreadyExists ex){
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
		        .contentType(MediaType.APPLICATION_JSON)
		        .body((new ErrorResponse(ex.getMessage())));
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
		        .contentType(MediaType.APPLICATION_JSON)
		        .body((new ErrorResponse(ex.getMessage())));
	}
}
