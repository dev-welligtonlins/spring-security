package com.example.spring_security.domain.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, String>{

    List<Service> findByBarbershopIdAndServiceActiveTrue(String id);

    List<Service> findServicesByBarbershopIdAndServiceActiveTrue(String id);

    
} 
