package com.skillstorm.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Proj2SpringApplication implements CommandLineRunner{

	@Value("${my.env.mode}") 
	private String mode;
	
	public static void main(String[] args) {
		SpringApplication.run(Proj2SpringApplication.class, args);
		System.out.println("Spring server has started");

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Currently in: " + mode);
	}

}
