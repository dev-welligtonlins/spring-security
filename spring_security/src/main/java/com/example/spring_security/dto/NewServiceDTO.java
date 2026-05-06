package com.example.spring_security.dto;

import java.math.BigDecimal;

import com.example.spring_security.model.Barbershop;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record NewServiceDTO(String serviceDescription, BigDecimal value, String category) {
    
}