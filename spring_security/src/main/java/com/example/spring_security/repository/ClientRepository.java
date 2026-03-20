package com.example.spring_security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_security.model.Client;

public interface ClientRepository extends JpaRepository<Client, String>{
    
    Client findByName(String name);

    Optional findById(String id);

}
