package com.example.spring_security.dto;

import com.example.spring_security.model.UserRole;

public record RegisterDTO (String login, String password, UserRole role) {
    
}
