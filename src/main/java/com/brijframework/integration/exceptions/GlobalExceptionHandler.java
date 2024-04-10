package com.brijframework.integration.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Conflict", 5001, ex.getMessage());

		return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	@ExceptionHandler(value = { HttpClientErrorException.class  })
	protected ResponseEntity<Object> notFoundException(HttpClientErrorException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(),"NOT_FOUND", ex.getStatusCode().value(), ex.getMessage());
		return new ResponseEntity<Object>(errorResponse, new HttpHeaders(), HttpStatus.FORBIDDEN);
	}
}