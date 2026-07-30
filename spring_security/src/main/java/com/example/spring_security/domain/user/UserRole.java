package com.example.spring_security.domain.user;

public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    BARBERSHOP("barbershop"),
    CLIENT("client");

    private String role;
    
    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
