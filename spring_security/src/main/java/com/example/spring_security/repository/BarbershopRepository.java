package com.example.spring_security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_security.model.Barbershop;

public interface BarbershopRepository extends JpaRepository<Barbershop, String>{
    
    Barbershop findByName(String name);

    Optional<Barbershop> findById(String id);

    Optional<Barbershop> findByUserId(String id);

}
