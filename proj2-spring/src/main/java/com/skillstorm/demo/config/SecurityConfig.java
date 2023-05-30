package com.skillstorm.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		//Unauth'ed Access
		//http.authorizeHttpRequests().anyRequest().permitAll();
		
//		http.authorizeHttpRequests()
//			.mvcMatchers("/hello", "users/register", "/rooms", "/login").permitAll()
//			.anyRequest().authenticated();
//		
//		http.httpBasic();
//		
//		http.csrf().disable()
//        .formLogin()
//        .permitAll()
//        .loginProcessingUrl("/login")
//        .usernameParameter("email")
//        .passwordParameter("password");
//				
//		return http.build();
		http
		.cors() // Enable CORS configuration
			.and()
		.authorizeHttpRequests()
			.mvcMatchers("/hello", "/users/register", "/rooms", "/login").permitAll()
			.anyRequest().authenticated()
			.and()
		.httpBasic()
			.and()
		.csrf().disable()
		.formLogin()
			.permitAll()
			.loginProcessingUrl("/login")
			.usernameParameter("email")
			.passwordParameter("password");
			
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*"); // Allow requests from any origin
        configuration.addAllowedMethod("*"); // Allow all HTTP methods
        configuration.addAllowedHeader("*"); // Allow all headers
        configuration.setAllowCredentials(true); // Allow credentials (e.g., cookies)
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply the CORS configuration to all paths
        return source;
    }
}
