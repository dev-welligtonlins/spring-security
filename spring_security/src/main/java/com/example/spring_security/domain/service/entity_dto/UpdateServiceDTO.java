package com.example.spring_security.domain.service.entity_dto;

import java.math.BigDecimal;

import com.example.spring_security.domain.service.EnumCategoryService;

public record UpdateServiceDTO(
        String serviceDescription,
        Integer duration,
        BigDecimal value,
        EnumCategoryService serviceCategory) {

}