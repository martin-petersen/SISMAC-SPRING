package com.example.starter.model;

import javax.persistence.*;

@Entity
public class Especialidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nomeEspacialidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEspacialidade() {
        return nomeEspacialidade;
    }

    public void setNomeEspacialidade(String nomeEspacialidade) {
        this.nomeEspacialidade = nomeEspacialidade;
    }
}
