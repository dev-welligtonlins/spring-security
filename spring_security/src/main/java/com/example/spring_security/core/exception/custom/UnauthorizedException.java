package com.example.spring_security.core.exception.custom;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) { 
        super(message);
    }
}
