package com.skillstorm.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillstorm.demo.dtos.UserDTO;
import com.skillstorm.demo.models.User;
import com.skillstorm.demo.repositories.UserRepo;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> findAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public UserDTO findUserById(long id) {
        User user = userRepo.findById(id).orElseThrow();
        return convertToDTO(user);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        return convertToDTO(userRepo.save(user));
    }

    public void deleteItem(long id) {
        userRepo.deleteById(id);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User updatedUser = userRepo.save(user);
        return convertToDTO(updatedUser);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return user;
    }

    public void register(User user) {
        Optional<User> foundUser = userRepo.findByEmail(user.getEmail());

        if (foundUser.isPresent()) {
            throw new RuntimeException("That Email is already in use.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepo.save(user);
    }

    // Helper method to convert UserDTO to User entity
    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setLanguage(userDTO.getLanguage());
        user.setTimezone(userDTO.getTimezone());
        user.setRole(userDTO.getRole());
        return user;
    }

    // Helper method to convert User entity to UserDTO
    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getLanguage(),
                user.getTimezone(),
                user.getRole()
        );
    }
}
