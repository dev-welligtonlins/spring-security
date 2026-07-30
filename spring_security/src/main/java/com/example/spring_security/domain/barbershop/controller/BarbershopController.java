package com.example.spring_security.domain.barbershop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_security.domain.barbershop.entity_dto.Barbershop;
import com.example.spring_security.domain.barbershop.service.BarbershopService;
import com.example.spring_security.domain.user.entity_dto.User;


@RestController
@RequestMapping("barbershops/")
public class BarbershopController {
    
    private final BarbershopService barbershopService;

    public BarbershopController(BarbershopService barbershopService) {
        this.barbershopService = barbershopService;
    }


    @GetMapping("my-perfil")
    public ResponseEntity<Barbershop> getUserPerfil(@AuthenticationPrincipal User user) {
        var barbershop = barbershopService.findByUserId(user.getId());
        return ResponseEntity.ok(barbershop);
    }

    
}
