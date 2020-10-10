package com.example.starter.model;

import javax.persistence.*;

@Entity
public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nomeExame;

    public Exame() {
    }

    public Exame(Long id) {
        this.id = id;
    }

    public Exame(Long id, String nomeExame) {
        this.id = id;
        this.nomeExame = nomeExame;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeExame() {
        return nomeExame;
    }

    public void setNomeExame(String nomeExame) {
        this.nomeExame = nomeExame;
    }
}
