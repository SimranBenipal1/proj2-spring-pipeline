package com.skillstorm.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		//Unauth'ed Access
		//http.authorizeHttpRequests().anyRequest().permitAll();
		
		http.authorizeHttpRequests()
			.mvcMatchers("/hello", "users/register", "/rooms").permitAll()
			.anyRequest().authenticated();
		
		http.httpBasic();
		
		http.csrf().disable();
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
}
