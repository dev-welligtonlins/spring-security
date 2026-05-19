package com.example.spring_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_security.dto.AuthenticationDTO;
import com.example.spring_security.dto.LoginResponseDTO;
import com.example.spring_security.dto.RegisterBarbershopDTO;
import com.example.spring_security.dto.RegisterClientDTO;
import com.example.spring_security.dto.RegisterDTO;
import com.example.spring_security.dto.UserResponseDTO;
import com.example.spring_security.model.User;
import com.example.spring_security.repository.UserRepository;
import com.example.spring_security.security.TokenService;
import com.example.spring_security.service.AuthService;
import com.example.spring_security.service.BarbershopService;
import com.example.spring_security.service.ClientService;
import com.example.spring_security.util.CookieUtil;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("auth/")
public class AuthenticationController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ClientService clientService;
    @Autowired
    private BarbershopService barbershopService;
    @Autowired
    private AuthService authService;
    @Autowired
    private CookieUtil cookieUtil;

    @PostMapping("login")
    public ResponseEntity<Void> login(@RequestBody AuthenticationDTO data, HttpServletResponse response) {
        String token = authService.login(data);
        cookieUtil.addAccessToken(response, token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("register")    
    public ResponseEntity register(@RequestBody RegisterDTO data) {
        if(this.userRepository.findByLogin(data.login()) != null)
            return ResponseEntity.badRequest().build();
        
        String encryptedPassword = new BCryptPasswordEncoder()  .encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("register/client")    
    public ResponseEntity<?> register(@RequestBody RegisterClientDTO data, HttpServletResponse response) {
        String token = clientService.newDto(data);
        
        cookieUtil.addAccessToken(response, token);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("register/barbershop")    
    public ResponseEntity<?> register(@RequestBody RegisterBarbershopDTO data) {
        String token = barbershopService.newDto(data);
        return ResponseEntity.ok(new LoginResponseDTO(token));
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
