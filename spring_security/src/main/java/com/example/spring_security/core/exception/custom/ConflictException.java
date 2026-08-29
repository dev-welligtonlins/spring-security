package com.example.spring_security.core.exception.custom;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) { 
        super(message);
    }
}
