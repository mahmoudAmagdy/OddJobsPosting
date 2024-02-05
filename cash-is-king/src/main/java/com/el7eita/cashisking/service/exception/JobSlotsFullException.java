package com.el7eita.cashisking.service.exception;

public class JobSlotsFullException  extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public JobSlotsFullException(final String message) {
        super(message);
    }
}
