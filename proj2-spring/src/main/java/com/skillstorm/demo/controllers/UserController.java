package com.skillstorm.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skillstorm.demo.dtos.UserDTO;
import com.skillstorm.demo.models.User;
import com.skillstorm.demo.services.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long id) {
        UserDTO user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //See register function
//    @PostMapping
//    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
//        UserDTO createdUser = userService.createUser(userDTO);
//        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
//    }

    //It isn't a requirement for users to be able to update their info
//    @PutMapping("/{id}")
//    public ResponseEntity<UserDTO> updateUser(@PathVariable long id, @RequestBody UserDTO userDTO) {
//        UserDTO updatedUser = userService.findUserById(id);
//        updatedUser.setName(userDTO.getName());
//        updatedUser.setEmail(userDTO.getEmail());
//        updatedUser.setPhoneNumber(userDTO.getPhoneNumber());
//        updatedUser.setLanguage(userDTO.getLanguage());
//        updatedUser.setTimezone(userDTO.getTimezone());
//
//        UserDTO savedUser = userService.updateUser(updatedUser);
//        return new ResponseEntity<>(savedUser, HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody User user) {
        userService.register(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
