package com.example.spring_security.core.exception.custom;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) { 
        super(message);
    }
}
