package com.example.spring_security.core.auth.entity_dto.request;


public record RegisterClientDTO (String login, String name, String phone, String password) {
    
}
