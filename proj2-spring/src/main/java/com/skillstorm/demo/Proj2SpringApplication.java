package com.skillstorm.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Proj2SpringApplication implements CommandLineRunner{

	@Value("${my.env.mode}") 
	private String mode;
	
	@Value("${spring.mail.username}")
	private String username;
	
	public static void main(String[] args) {
		SpringApplication.run(Proj2SpringApplication.class, args);
		System.out.println("Spring server has started");

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Currently in: " + mode);
		System.out.println("Don't forget to pass in the Email and Password");
		System.out.println("Emails will be sent from: " + username);
	}

}
