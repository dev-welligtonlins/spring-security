package com.example.spring_security.model;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "service_description")
    private String serviceDescription;
    @Column(name = "service_value", precision = 10, scale = 2)
    private BigDecimal value;
    @Column(name = "service_active")
    private Boolean serviceActive;
    @Column(name = "category")
    private String category;

    @ManyToOne
    @JoinColumn(name = "barbershop_id")
    @JsonBackReference
    private Barbershop barbershop;

    

    public Service() {
    }
    
    public Service(String serviceDescription, BigDecimal value, String category, Barbershop barbershop) {
        this.serviceDescription = serviceDescription;
        this.value = value;
        this.category = category;
        this.serviceActive = true;
        this.barbershop = barbershop;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Boolean getServiceActive() {
        return serviceActive;
    }

    public void setServiceActive(Boolean serviceActive) {
        this.serviceActive = serviceActive;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Barbershop getBarbershop() {
        return barbershop;
    }

    public void setBarbershop(Barbershop barbershop) {
        this.barbershop = barbershop;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
}