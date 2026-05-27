package com.example.spring_security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_security.dto.NewServiceDTO;
import com.example.spring_security.model.Client;
import com.example.spring_security.model.Service;
import com.example.spring_security.model.User;
import com.example.spring_security.security.TokenService;
import com.example.spring_security.service.ClientService;
import com.example.spring_security.service.ServiceService;


@RestController
@RequestMapping("services/")
public class ServiceController {
    
    @Autowired
    private ServiceService serviceService;

    @PreAuthorize("hasRole('BARBERSHOP')")
    @PostMapping("create")
    public ResponseEntity<Service> insertService(@AuthenticationPrincipal User user, @RequestBody NewServiceDTO data) {
        var client = serviceService.insertService(user, data);
        return ResponseEntity.ok(client);
    }

    @PreAuthorize("hasRole('BARBERSHOP')")
    @DeleteMapping("remove/{serviceId}")
    public ResponseEntity<?> removeService(@AuthenticationPrincipal User user, @PathVariable("serviceId") String serviceId) {
        serviceService.removeService(serviceId);     
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("find/{barbershopId}")
    public ResponseEntity<List<Service>> findByBarbershopId(@AuthenticationPrincipal User user, @PathVariable("barbershopId") String barbershopId) {
        List<Service> listService = serviceService.findByBarbershopId(barbershopId);
        return ResponseEntity.ok(listService);
    }

    @PreAuthorize("hasRole('BARBERSHOP')")
    @GetMapping("me")
    public ResponseEntity<List<Service>> meServices(@AuthenticationPrincipal User user) {
        List<Service> listService = serviceService.meServices(user);
        return ResponseEntity.ok(listService);
    }
    
}
