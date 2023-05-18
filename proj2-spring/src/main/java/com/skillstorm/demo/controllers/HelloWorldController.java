package com.skillstorm.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World! The CICD Pipeline Works!";
	}
}
