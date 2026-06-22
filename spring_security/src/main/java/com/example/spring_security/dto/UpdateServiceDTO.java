package com.example.spring_security.dto;

import java.math.BigDecimal;

import com.example.spring_security.domain.service.EnumCategoryService;
import com.example.spring_security.model.Barbershop;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record UpdateServiceDTO(
        String serviceDescription,
        Integer duration,
        BigDecimal value,
        EnumCategoryService serviceCategory) {

}