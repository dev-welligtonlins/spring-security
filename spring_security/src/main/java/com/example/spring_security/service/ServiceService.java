package com.example.spring_security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.example.spring_security.dto.NewServiceDTO;
import com.example.spring_security.dto.UpdateServiceDTO;
import com.example.spring_security.model.Barbershop;
import com.example.spring_security.model.Service;
import com.example.spring_security.model.User;
import com.example.spring_security.model.UserRole;
import com.example.spring_security.repository.BarbershopRepository;
import com.example.spring_security.repository.ServiceRepository;

import jakarta.transaction.Transactional;

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final BarbershopRepository barbershopRepository;

    public ServiceService(ServiceRepository serviceRepository, BarbershopRepository barbershopRepository) {
        this.serviceRepository = serviceRepository;
        this.barbershopRepository = barbershopRepository;
    }

    public Service insertService(User user, NewServiceDTO data) {
        Barbershop barbershop = barbershopRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Barbearia não encontrado!"));
        Service newService = new Service(data.serviceDescription(), data.value(), data.category(), barbershop);

        return this.serviceRepository.save(newService);
    }
    @Transactional
    public Service updateService(User user, String serviceId, UpdateServiceDTO data) {
        Barbershop barbershop = barbershopRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Barbearia não encontrado!"));
        Service service = serviceRepository.findById(serviceId).orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
        if (!service.getBarbershop().getId().equals(barbershop.getId()))
            throw new RuntimeException();
        service.setServiceDescription(data.serviceDescription());
        service.setValue(data.value());
        service.setCategory(data.category());
        serviceRepository.save(service);
        return service;
    }

    public List<Service> meServices(User user) {
        Barbershop barbershop = barbershopRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Barbearia não encontrado!"));
        return serviceRepository.findByBarbershopId(barbershop.getId())
                .orElseThrow(() -> new RuntimeException("serviço não encontrado"));
    }

    public void removeService(String id) {
        Service obj = this.serviceRepository.findById(id).orElseThrow(() -> new RuntimeException("não encontrado"));
        this.serviceRepository.delete(obj);
    }

    public Service findByServiceDescription(String serviceDescription) {
        Service obj = this.serviceRepository.findByServiceDescription(serviceDescription)
                .orElseThrow(() -> new RuntimeException("serviço não encontrado"));
        return obj;
    }

    public List<Service> findByBarbershopId(String barbershopId) {
        return serviceRepository.findByBarbershopId(barbershopId)
                .orElseThrow(() -> new RuntimeException("serviço não encontrado"));
    }

}
