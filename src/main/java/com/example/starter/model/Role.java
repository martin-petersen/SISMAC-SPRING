package com.example.starter.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Role implements GrantedAuthority {

    @Id
    @Enumerated(EnumType.STRING)
    private PerfilUsuario id;



    @Override
    public String getAuthority() {
        return id.toString();
    }
}
