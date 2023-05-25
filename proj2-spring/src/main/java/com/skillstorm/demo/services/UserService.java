package com.skillstorm.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.skillstorm.demo.dtos.UserDTO;
import com.skillstorm.demo.models.User;
import com.skillstorm.demo.repositories.UserRepo;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	public List<User> findAllUsers(){
		return userRepo.findAll();
	}
	
	public User findUserById(long id) {
		return userRepo.findById(id).orElseThrow();
	}
	
	public User createUser (User user) {
		return userRepo.save(user);
	}
	
	public void deleteItem (long id) {
		userRepo.deleteById(id);
	}
	
	public User updateUser (User user) {
		User updatedUser = new User (
				user.getId(),
				user.getName(),
				user.getEmail(),
				user.getPhoneNumber(),
				user.getLanguage(),
				user.getTimezone(),
				user.getPassword());
		return userRepo.save(updatedUser);	
	}
}
