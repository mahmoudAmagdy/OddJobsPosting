package com.el7eita.cashisking.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.el7eita.cashisking.service.exception.JobAlreadyExistsException;
import com.el7eita.cashisking.service.exception.JobIssuerAlreadyExistsException;
import com.el7eita.cashisking.service.exception.JobTakerAlreadyExistsException;
import com.el7eita.cashisking.service.exception.NoJobExistsException;
import com.el7eita.cashisking.service.exception.NoJobIssuerExistsException;
import com.el7eita.cashisking.service.exception.NoJobTakerExistsException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionController {
	protected static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionController.class);
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException e) {
	    LOGGER.info("IllegalArgumentException occured: URL="+request.getRequestURL());
	    return e.getLocalizedMessage();
	}
	
	
	@ExceptionHandler(NoJobIssuerExistsException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handleNoJobIssuerException(HttpServletRequest request, NoJobIssuerExistsException e) {
        LOGGER.info("NoJobIssuerExistsException occured: URL="+request.getRequestURL());
        return e.getLocalizedMessage();

	}
	
	@ExceptionHandler(NoJobExistsException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handleNoJobException(HttpServletRequest request, NoJobExistsException e) {
        LOGGER.info("NoJobExistsException occured: URL="+request.getRequestURL());
        return e.getLocalizedMessage();

	}
	
	@ExceptionHandler(NoJobTakerExistsException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handleNoJobTakerException(HttpServletRequest request, NoJobTakerExistsException e) {
        LOGGER.info("NoJobTakerExistsException occured: URL="+request.getRequestURL());
        return e.getLocalizedMessage();

	}
	
	@ExceptionHandler(JobAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public String handleJobAlreadyExistsException(HttpServletRequest request, JobAlreadyExistsException e) {
        LOGGER.info("JobAlreadyExistsException occured: URL="+request.getRequestURL());
        return e.getLocalizedMessage();

	}
	

	@ExceptionHandler(JobTakerAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public String handleJobTakerAlreadyExistsException(HttpServletRequest request, JobTakerAlreadyExistsException e) {
        LOGGER.info("JobAlreadyExistsException occured: URL="+request.getRequestURL());
        return e.getLocalizedMessage();

	}
	
	@ExceptionHandler(JobIssuerAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public String handleJobIssuerAlreadyExistsException(HttpServletRequest request, JobIssuerAlreadyExistsException e) {
        LOGGER.info("JobIssuerAlreadyExistsException occured: URL="+request.getRequestURL());
        return e.getLocalizedMessage();

	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public String handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException e) {
        LOGGER.info("ConstraintViolationException occured: URL="+request.getRequestURL());
        return e.getConstraintViolations().iterator().next().getMessage();

	}

	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handleUsernameNotFoundException(HttpServletRequest request, UsernameNotFoundException e) {
        LOGGER.info("UsernameNotFoundException occured: URL="+request.getRequestURL());
        return e.getLocalizedMessage();

	}
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handleBadCredentialsException(HttpServletRequest request, BadCredentialsException e) {
        LOGGER.info("BadCredentialsException occured: URL="+request.getRequestURL());
        return e.getLocalizedMessage();

	}

}
