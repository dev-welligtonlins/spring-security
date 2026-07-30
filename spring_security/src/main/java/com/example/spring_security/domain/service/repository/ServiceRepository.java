package com.example.spring_security.domain.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_security.domain.service.entity_dto.Service;

public interface ServiceRepository extends JpaRepository<Service, String>{

    List<Service> findByBarbershopIdAndServiceActiveTrue(String id);

    List<Service> findServicesByBarbershopIdAndServiceActiveTrue(String id);

    
} 
