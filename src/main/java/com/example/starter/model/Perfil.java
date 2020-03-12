package com.example.starter.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PerfilUsuario nomePerfil;


    @Override
    public String getAuthority() {
        return nomePerfil.toString();
    }
}
