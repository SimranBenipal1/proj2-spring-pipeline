package com.skillstorm.demo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Value("${my.env.mode}") 
	private String mode;
	
	@Value("${spring.mail.username}")
	private String username;
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World! The CICD Pipeline Works! You are Currently in: " + mode + ". Emails are sent from: " + username  + ". FYI check your spam folder!" ; 
	}
}
