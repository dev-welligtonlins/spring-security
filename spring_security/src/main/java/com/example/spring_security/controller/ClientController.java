package com.example.spring_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_security.dto.AuthenticationDTO;
import com.example.spring_security.dto.LoginResponseDTO;
import com.example.spring_security.dto.RegisterDTO;
import com.example.spring_security.model.Client;
import com.example.spring_security.model.User;
import com.example.spring_security.repository.UserRepository;
import com.example.spring_security.security.TokenService;
import com.example.spring_security.service.ClientService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("user/")
public class ClientController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ClientService clientService;
    @Autowired
    private TokenService tokenService;

    @GetMapping("my-perfil")
    public ResponseEntity<Client> getUserPerfil(@AuthenticationPrincipal User user) {
        var client = clientService.findByUserId(user.getId());
        return ResponseEntity.ok(client);
    }

    
}
