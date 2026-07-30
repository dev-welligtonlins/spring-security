package com.example.spring_security.core.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_security.core.auth.entity_dto.request.AuthRequestDTO;
import com.example.spring_security.core.auth.entity_dto.request.RegisterBarbershopDTO;
import com.example.spring_security.core.auth.entity_dto.request.RegisterClientDTO;
import com.example.spring_security.core.auth.entity_dto.response.LoginResponseDTO;
import com.example.spring_security.core.auth.entity_dto.response.UserResponseDTO;
import com.example.spring_security.core.auth.service.AuthService;
import com.example.spring_security.core.auth.service.CookieService;
import com.example.spring_security.core.auth.service.TokenService;
import com.example.spring_security.domain.barbershop.service.BarbershopService;
import com.example.spring_security.domain.client.service.ClientService;
import com.example.spring_security.domain.user.entity_dto.User;
import com.example.spring_security.domain.user.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("auth/")
public class AuthenticationController {
    
    private final UserRepository userRepository;
    private final ClientService clientService;
    private final BarbershopService barbershopService;
    private final AuthService authService;
    private final CookieService cookieUtil;

    public AuthenticationController(UserRepository userRepository, ClientService clientService,
            BarbershopService barbershopService, AuthService authService, CookieService cookieUtil) {
        this.userRepository = userRepository;
        this.clientService = clientService;
        this.barbershopService = barbershopService;
        this.authService = authService;
        this.cookieUtil = cookieUtil;
    }

    @PostMapping("login")
    public ResponseEntity<Void> login(@RequestBody AuthRequestDTO data, HttpServletResponse response) {
        String token = authService.login(data);
        cookieUtil.addAccessToken(response, token);
        return ResponseEntity.ok().build();
    }

    // primeiro modelo
    // @PostMapping("register")    
    // public ResponseEntity register(@RequestBody RegisterDTO data) {
    //     if(this.userRepository.findByLogin(data.login()) != null)
    //         return ResponseEntity.badRequest().build();
        
    //     String encryptedPassword = new BCryptPasswordEncoder()  .encode(data.password());
    //     User newUser = new User(data.login(), encryptedPassword, data.role());

    //     userRepository.save(newUser);
    //     return ResponseEntity.ok().build();
    // }

    @PostMapping("register/client")    
    public ResponseEntity<?> register(@RequestBody RegisterClientDTO data, HttpServletResponse response) {
        String token = clientService.newDto(data);
        
        cookieUtil.addAccessToken(response, token);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("register/barbershop")    
    public ResponseEntity<?> registerBarbershop(@RequestBody RegisterBarbershopDTO data, HttpServletResponse response) {
        String token = barbershopService.registerBarbershop(data);

        cookieUtil.addAccessToken(response, token);
        return ResponseEntity.ok().build();
    }

    @GetMapping("me")
    public ResponseEntity<UserResponseDTO> me(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(
            new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getRole().name()
            )
        );
    }
}
