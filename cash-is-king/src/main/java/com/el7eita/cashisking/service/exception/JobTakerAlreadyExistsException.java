package com.el7eita.cashisking.service.exception;

public class JobTakerAlreadyExistsException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public JobTakerAlreadyExistsException(final String message) {
        super(message);
    }
}
