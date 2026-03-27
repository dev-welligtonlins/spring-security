package com.example.spring_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_security.dto.AuthenticationDTO;
import com.example.spring_security.dto.LoginResponseDTO;
import com.example.spring_security.dto.RegisterBarbershopDTO;
import com.example.spring_security.dto.RegisterClientDTO;
import com.example.spring_security.dto.RegisterDTO;
import com.example.spring_security.model.User;
import com.example.spring_security.repository.UserRepository;
import com.example.spring_security.security.TokenService;
import com.example.spring_security.service.BarbershopService;
import com.example.spring_security.service.ClientService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("auth/")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private ClientService clientService;
    @Autowired
    private BarbershopService barbershopService;

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("register")    
    public ResponseEntity register(@RequestBody RegisterDTO data) {
        if(this.userRepository.findByLogin(data.login()) != null)
            return ResponseEntity.badRequest().build();
        
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("register/client")    
    public ResponseEntity<?> register(@RequestBody RegisterClientDTO data) {
        String token = clientService.newDto(data);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    
    @PostMapping("register/barbershop")    
    public ResponseEntity<?> register(@RequestBody RegisterBarbershopDTO data) {
        String token = barbershopService.newDto(data);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
