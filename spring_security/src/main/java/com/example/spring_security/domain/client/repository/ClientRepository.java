package com.example.spring_security.domain.client.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_security.domain.client.entity_dto.Client;

public interface ClientRepository extends JpaRepository<Client, String>{
    
    Client findByName(String name);

    Optional<Client> findById(String id);

    Optional<Client> findByUserId(String id);

}
