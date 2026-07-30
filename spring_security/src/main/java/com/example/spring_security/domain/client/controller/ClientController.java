package com.example.spring_security.domain.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_security.core.auth.service.TokenService;
import com.example.spring_security.domain.client.entity_dto.Client;
import com.example.spring_security.domain.client.service.ClientService;
import com.example.spring_security.domain.user.entity_dto.User;


@RestController
@RequestMapping("clients/")
public class ClientController {
    
    private final AuthenticationManager authenticationManager;
    private final ClientService clientService;
    private final TokenService tokenService;



    public ClientController(AuthenticationManager authenticationManager, ClientService clientService,
            TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.clientService = clientService;
        this.tokenService = tokenService;
    }



    @GetMapping("my-perfil")
    public ResponseEntity<Client> getUserPerfil(@AuthenticationPrincipal User user) {
        var client = clientService.findByUserId(user.getId());
        return ResponseEntity.ok(client);
    }

    
}
