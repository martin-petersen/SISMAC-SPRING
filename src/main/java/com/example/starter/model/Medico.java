package com.example.starter.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Especialidade> especialidades;
    private String conselho;
    @Column(unique = true)
    private String numeroConselho;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidade(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    public String getConselho() {
        return conselho;
    }

    public void setConselho(String conselho) {
        this.conselho = conselho;
    }

    public String getNumeroConselho() {
        return numeroConselho;
    }

    public void setNumeroConselho(String numeroConselho) {
        this.numeroConselho = numeroConselho;
    }
}
