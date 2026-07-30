package com.example.spring_security.domain.service.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.example.spring_security.domain.barbershop.entity_dto.Barbershop;
import com.example.spring_security.domain.barbershop.repository.BarbershopRepository;
import com.example.spring_security.domain.service.entity_dto.NewServiceDTO;
import com.example.spring_security.domain.service.entity_dto.Service;
import com.example.spring_security.domain.service.entity_dto.ServiceDTO;
import com.example.spring_security.domain.service.entity_dto.UpdateServiceDTO;
import com.example.spring_security.domain.service.repository.ServiceRepository;
import com.example.spring_security.domain.user.entity_dto.User;

import jakarta.transaction.Transactional;

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final BarbershopRepository barbershopRepository;
  
    public ServiceService(ServiceRepository serviceRepository, BarbershopRepository barbershopRepository){
        this.serviceRepository = serviceRepository;
        this.barbershopRepository = barbershopRepository;
    }


    // // retorna um unico servico
    // public ServiceDTO findById(Long barbershopId, Long serviceId) {
    //     Service service = serviceRepository.findById(serviceId).orElseThrow(() -> new NotFoundException("Serviço não encontrado!"));
    //     if(!service.getBarbershop().getId().equals(barbershopId)) {
    //         throw new ForbiddenException("Serviço não pertence à barbearia!");
    //     }        
    //     return ServiceDTO.fromEntity(service);
    // }

    public List<ServiceDTO> meServices(User user) {
        Barbershop barbershop = barbershopRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Barbearia não encontrado!"));
        List<Service> services = serviceRepository.findByBarbershopIdAndServiceActiveTrue(barbershop.getId());
        if(services.isEmpty()) {
            throw new RuntimeException("Barbearia não possui serviços!");
        } 
        return services.stream().map(ServiceDTO::fromEntity).toList();
    }

    // usado para clientes visualizarem serviços das barbearias
    public List<ServiceDTO> findByBarbershopId(String barbershopId) {
        List<Service> services = serviceRepository.findByBarbershopIdAndServiceActiveTrue(barbershopId);
        if(services.isEmpty()) {
            throw new RuntimeException("Barbearia não possui serviços!");
        }        
        return services.stream().map(ServiceDTO::fromEntity).toList();
    }

    @Transactional
    public ServiceDTO createService(User user, NewServiceDTO data){
        // Barbershop barbershop = barbershopRepository.findByUserId(user.getId()).orElseThrow(() -> new NotFoundException("Barbearia não encontrada!"));        
        Barbershop barbershop = barbershopRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Barbearia não encontrado!"));        
 
        Service service = new Service();
        service.setServiceDescription(data.serviceDescription());
        service.setDuration(data.duration());
        service.setValue(data.value());
        service.setServiceCategory(data.serviceCategory());
        service.setServiceActive(true);

        service.setBarbershop(barbershop);
        barbershop.getServices().add(service);

        serviceRepository.save(service);
        return ServiceDTO.fromEntity(service);
    }
    
    @Transactional
    public ServiceDTO updateService(User user, String serviceId, UpdateServiceDTO data) {
        Barbershop barbershop = barbershopRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Barbearia não encontrado!"));
        Service service = serviceRepository.findById(serviceId).orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
        if (!service.getBarbershop().getId().equals(barbershop.getId()))
            throw new RuntimeException();     
        service.setServiceDescription(data.serviceDescription());
        service.setDuration(data.duration());
        service.setValue(data.value());
        service.setServiceCategory(data.serviceCategory());
        return ServiceDTO.fromEntity(service);
    }

    @Transactional
    public void removeService(User user, String serviceId) {
        Barbershop barbershop = barbershopRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Barbearia não encontrado!"));
        Service service = serviceRepository.findById(serviceId).orElseThrow(() -> new RuntimeException("Serviço não encontrado!"));
        if(!service.getBarbershop().getId().equals(barbershop.getId()))
            throw new RuntimeException("Serviço não pertence barbearia!");
            // throw new ConflictException("Serviço não pertence barbearia!");
        
        service.setServiceActive(false);
    }

    // public ServicePageViewDTO servicePage(Long id){
    //     ServiceDashboardViewDTO dashboard = serviceDashboardViewRepository.findByBarbershopId(id);
    //     List<ServiceListViewDTO> services = serviceListViewRepository.findByBarbershopId(id);
        
    //     return ServicePageViewDTO.fromView(dashboard, services);
    // }

}
