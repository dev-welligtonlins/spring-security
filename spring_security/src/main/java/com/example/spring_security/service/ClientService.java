package com.example.spring_security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.spring_security.model.Client;
import com.example.spring_security.repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository repo;
  
    public ClientService(ClientRepository repo){
        this.repo = repo;
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
}

