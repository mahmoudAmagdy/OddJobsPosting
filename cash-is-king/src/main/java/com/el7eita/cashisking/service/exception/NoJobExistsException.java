package com.el7eita.cashisking.service.exception;

public class NoJobExistsException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public NoJobExistsException(final String message) {
        super(message);
    }

}
