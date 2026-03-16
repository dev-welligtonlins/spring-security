package com.example.spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.spring_security.model.User;

public interface UserRepository extends JpaRepository<User, String>{
    
    UserDetails findByLogin(String login);
}
