package com.kime.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Result implements Serializable {
	private String statusCode;
	private String message;
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
