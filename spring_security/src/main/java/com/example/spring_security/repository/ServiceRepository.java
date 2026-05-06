package com.example.spring_security.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_security.model.Service;

public interface ServiceRepository extends JpaRepository<Service, String>{

    Optional<List<Service>> findByBarbershopId(String id);

    Optional<Service> findById(String id);

    Optional<Service> findByServiceDescription(String id);

}
