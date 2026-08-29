package com.example.spring_security.core.exception;

public record ErrorResponse (
    String timestramp, 
    int status, 
    String error, 
    String message, 
    String path) {}
