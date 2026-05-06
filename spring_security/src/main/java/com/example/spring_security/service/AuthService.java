package com.example.spring_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.spring_security.dto.AuthenticationDTO;
import com.example.spring_security.model.User;
import com.example.spring_security.security.TokenService;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public String login(AuthenticationDTO data) {
        var usernamePassword =
            new UsernamePasswordAuthenticationToken(data.login(), data.password());

        var auth = authenticationManager.authenticate(usernamePassword);

        var user = (User) auth.getPrincipal();

        return tokenService.generateToken(user);
    }
            
}

