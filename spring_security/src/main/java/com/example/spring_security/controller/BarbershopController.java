package com.example.spring_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_security.model.Barbershop;
import com.example.spring_security.model.User;
import com.example.spring_security.service.BarbershopService;


@RestController
@RequestMapping("barbershops/")
public class BarbershopController {
    
    @Autowired
    private BarbershopService barbershopService;

    @GetMapping("my-perfil")
    public ResponseEntity<Barbershop> getUserPerfil(@AuthenticationPrincipal User user) {
        var barbershop = barbershopService.findByUserId(user.getId());
        return ResponseEntity.ok(barbershop);
    }

    
}
