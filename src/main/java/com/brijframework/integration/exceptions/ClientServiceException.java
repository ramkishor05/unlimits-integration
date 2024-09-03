package com.brijframework.integration.exceptions;

import org.springframework.http.HttpStatus;

public class ClientServiceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus statusCode;

	public ClientServiceException(String msg) {
		super(msg);
		setStatusCode(HttpStatus.BAD_GATEWAY);
	}

	public ClientServiceException(String msg, Exception e) {
		super(msg, e);
		setStatusCode(HttpStatus.BAD_GATEWAY);
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
}
