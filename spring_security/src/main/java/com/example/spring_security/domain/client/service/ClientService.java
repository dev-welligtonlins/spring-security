package com.example.spring_security.domain.client.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring_security.core.auth.entity_dto.request.RegisterClientDTO;
import com.example.spring_security.core.auth.service.TokenService;
import com.example.spring_security.domain.client.entity_dto.Client;
import com.example.spring_security.domain.client.repository.ClientRepository;
import com.example.spring_security.domain.user.UserRole;
import com.example.spring_security.domain.user.entity_dto.User;
import com.example.spring_security.domain.user.repository.UserRepository;

@Service
public class ClientService {

    private final ClientRepository repo;
    private final UserRepository userRepository;
    private final TokenService tokenService;
  
    public ClientService(ClientRepository repo,  UserRepository userRepository,  TokenService tokenService){
        this.repo = repo;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public Client save(Client obj){
        return this.repo.save(obj);
    }
 
    // @Override
    // public List<Client> findAll(){
    //     return this.repo.findAll();
    // }

    public Client findById(String id){
        return this.repo.findById(id).orElseThrow(() -> new RuntimeException("não encontrado"));
    }

    public void delete(String id){
        Client obj = this.repo.findById(id).orElseThrow(() -> new RuntimeException("não encontrado"));
        this.repo.delete(obj);
    }

    public Client findByUserId(String id){
        return this.repo.findByUserId(id).orElseThrow(() -> new RuntimeException("não encontrado"));
    }

    public String newDto(RegisterClientDTO data){
        if(this.userRepository.findByLogin(data.login()) != null){
            return "usuário não disponível";
        }
        
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, UserRole.CLIENT);
        userRepository.save(newUser);

        Client newClient = new Client();
        newClient.setLogin(data.login());
        newClient.setName(data.name());
        newClient.setPhone(data.phone());
        newClient.setUser(newUser);

        repo.save(newClient);
        return tokenService.generateToken(newUser);
    }
}

