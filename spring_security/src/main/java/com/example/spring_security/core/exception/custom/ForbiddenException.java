package com.example.spring_security.core.exception.custom;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) { 
        super(message);
    }
}
