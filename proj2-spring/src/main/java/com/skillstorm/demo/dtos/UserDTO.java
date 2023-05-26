package com.skillstorm.demo.dtos;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String language;
    private String timezone;
    private String role;

    // Constructors

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email, String phoneNumber, String language, String timezone, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.language = language;
        this.timezone = timezone;
        this.role = role;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    
    public String getRole() {
    	return this.role;
    }
    
    public void setRole(String role) {
    	this.role = role;
    }
    

}