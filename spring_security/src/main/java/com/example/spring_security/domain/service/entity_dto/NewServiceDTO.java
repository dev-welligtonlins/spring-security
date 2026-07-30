package com.example.spring_security.domain.service.entity_dto;

import java.math.BigDecimal;

import com.example.spring_security.domain.barbershop.entity_dto.Barbershop;
import com.example.spring_security.domain.service.EnumCategoryService;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record NewServiceDTO(
    String serviceDescription,
    Integer duration,
    BigDecimal value, 
    EnumCategoryService serviceCategory) {}