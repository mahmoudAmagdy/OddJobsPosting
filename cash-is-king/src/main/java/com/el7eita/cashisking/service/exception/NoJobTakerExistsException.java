package com.el7eita.cashisking.service.exception;

public class NoJobTakerExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NoJobTakerExistsException(final String message) {
		super(message);
	}
}
