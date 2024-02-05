package com.el7eita.cashisking.service.exception;

public class JobAlreadyExistsException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public JobAlreadyExistsException(final String message) {
        super(message);
    }
}
