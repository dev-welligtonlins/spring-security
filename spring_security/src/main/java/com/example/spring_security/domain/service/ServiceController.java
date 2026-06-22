package com.example.spring_security.domain.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_security.domain.service.dto.NewServiceDTO;
import com.example.spring_security.domain.service.dto.ServiceDTO;
import com.example.spring_security.dto.UpdateServiceDTO;
import com.example.spring_security.model.User;



@RestController
@RequestMapping("services/")
public class ServiceController {
    
    private ServiceService serviceService; 

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PreAuthorize("hasRole('BARBERSHOP')")
    @PostMapping("create")
    public ResponseEntity<ServiceDTO> createService(@AuthenticationPrincipal User user, @RequestBody NewServiceDTO data) {
        var client = serviceService.createService(user, data);
        return ResponseEntity.ok(client);
    }
    @PreAuthorize("hasRole('BARBERSHOP')")
    @PutMapping("update/{serviceId}")
    public ResponseEntity<ServiceDTO> updateService(@AuthenticationPrincipal User user, @PathVariable("serviceId") String serviceId, @RequestBody UpdateServiceDTO data) {
        var client = serviceService.updateService(user, serviceId, data);
        return ResponseEntity.ok(client);
    }

    @PreAuthorize("hasRole('BARBERSHOP')")
    @DeleteMapping("remove/{serviceId}")
    public ResponseEntity<?> removeService(@AuthenticationPrincipal User user, @PathVariable("serviceId") String serviceId) {
        serviceService.removeService(user, serviceId);     
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("find/{barbershopId}")
    public ResponseEntity<List<ServiceDTO>> findByBarbershopId(@AuthenticationPrincipal User user, @PathVariable("barbershopId") String barbershopId) {
        List<ServiceDTO> listService = serviceService.findByBarbershopId(barbershopId);
        return ResponseEntity.ok(listService);
    }

    @PreAuthorize("hasRole('BARBERSHOP')")
    @GetMapping("me")
    public ResponseEntity<List<ServiceDTO>> meServices(@AuthenticationPrincipal User user) {
        List<ServiceDTO> listService = serviceService.meServices(user);
        return ResponseEntity.ok(listService);
    }
    
}
