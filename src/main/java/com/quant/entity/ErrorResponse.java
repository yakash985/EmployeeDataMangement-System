package com.quant.entity;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Error Response model")
public class ErrorResponse {
	private String message;

	public ErrorResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
