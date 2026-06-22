package com.example.spring_security.domain.service.dto;

import java.math.BigDecimal;

import com.example.spring_security.domain.service.EnumCategoryService;
import com.example.spring_security.domain.service.Service;

public record ServiceDTO(
    String id,
    String serviceDescription,
    Integer duration,
    BigDecimal value,
    EnumCategoryService serviceCategory
) {

    public static ServiceDTO fromEntity(Service service) {
        return new ServiceDTO(
                service.getId(),
                service.getServiceDescription(),
                service.getDuration(),
                service.getValue(),
                service.getServiceCategory());
    }
}