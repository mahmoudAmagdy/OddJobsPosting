package com.el7eita.cashisking.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    protected final Logger log = LoggerFactory.getLogger(getClass());
    
    @RequestMapping("/")
    public String home() {
        return "Welcome to home!";
    }
    
    @GetMapping("/profileIssuer")
    public String jIssuer() {
    	return ("<h1>Welcome Job Issuer!</h1>");
    }
    
    @GetMapping("/profileTaker")
    public String jTaker() {
    	return ("<h1>Welcome Job Taker!</h1>");
    }

}