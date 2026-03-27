package com.example.spring_security.model;

import java.util.Collection;
import java.util.List;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "users")
@Entity(name = "users")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "user_login")
    private String login;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_role")
    private UserRole role;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Client client;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Barbershop barbershop;

    // @OneToOne(mappedBy = "barbershops", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JsonManagedReference
    // private Barbershop barbershop;

    public User() {

    }
    
    public User(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public UserRole getRole() {
        return role;
    }
  
    // public Client getClient() {
    //     return client;
    // }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));

    }
    @Override
    public String getPassword() {
        return this.password;
    }
    
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
   
}
