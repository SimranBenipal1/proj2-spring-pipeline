package com.skillstorm.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.demo.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

}
