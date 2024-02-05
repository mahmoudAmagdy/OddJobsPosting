package com.el7eita.cashisking.service.exception;

public class NoJobIssuerExistsException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public NoJobIssuerExistsException(final String message) {
		super(message);
	}
}
