package com.example.spring_security.service;
 
 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service; 

import com.example.spring_security.dto.RegisterBarbershopDTO; 
import com.example.spring_security.model.Barbershop; 
import com.example.spring_security.model.User;
import com.example.spring_security.model.UserRole;
import com.example.spring_security.repository.BarbershopRepository; 
import com.example.spring_security.repository.UserRepository;
import com.example.spring_security.security.TokenService;

@Service
public class BarbershopService {

    private final BarbershopRepository repo;
    private final UserRepository userRepository;
    private final TokenService tokenService;
  
    public BarbershopService(BarbershopRepository repo,  UserRepository userRepository,  TokenService tokenService){
        this.repo = repo;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public Barbershop save(Barbershop obj){
        return this.repo.save(obj);
    }
 
    // @Override
    // public List<Client> findAll(){
    //     return this.repo.findAll();
    // }

    public Barbershop findById(String id){
        return this.repo.findById(id).orElseThrow(() -> new RuntimeException("não encontrado"));
    }

    public void delete(String id){
        Barbershop obj = this.repo.findById(id).orElseThrow(() -> new RuntimeException("não encontrado"));
        this.repo.delete(obj);
    }

    public Barbershop findByUserId(String id){
        return this.repo.findByUserId(id).orElseThrow(() -> new RuntimeException("não encontrado"));
    }

    public String newDto(RegisterBarbershopDTO data){
        if(this.userRepository.findByLogin(data.login()) != null){
            return "usuário não disponível";
        }
        
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, UserRole.BARBERSHOP);
        userRepository.save(newUser);

        Barbershop newBarbershop = new Barbershop();
        newBarbershop.setLogin(data.login());
        newBarbershop.setName(data.name());
        newBarbershop.setPhone(data.phone());
        newBarbershop.setUser(newUser);

        repo.save(newBarbershop);
        return tokenService.generateToken(newUser);
    }
}

