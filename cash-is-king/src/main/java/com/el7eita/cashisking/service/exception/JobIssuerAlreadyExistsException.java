package com.el7eita.cashisking.service.exception;

public class JobIssuerAlreadyExistsException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public JobIssuerAlreadyExistsException(final String message) {
        super(message);
    }
}
