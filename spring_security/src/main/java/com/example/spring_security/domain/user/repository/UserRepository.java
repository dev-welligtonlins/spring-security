package com.example.spring_security.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.spring_security.domain.user.entity_dto.User;

public interface UserRepository extends JpaRepository<User, String>{
    
    UserDetails findByLogin(String login);
}
