package com.example.spring_security.core.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.spring_security.core.auth.entity_dto.request.AuthRequestDTO;
import com.example.spring_security.domain.user.entity_dto.User;

@Service
public class AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthService(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }



    public String login(AuthRequestDTO data) {
        var usernamePassword =
            new UsernamePasswordAuthenticationToken(data.login(), data.password());

        var auth = authenticationManager.authenticate(usernamePassword);

        var user = (User) auth.getPrincipal();

        return tokenService.generateToken(user);
    }
            
}

