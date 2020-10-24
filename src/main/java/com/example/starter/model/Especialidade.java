package com.example.starter.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Especialidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, name = "especialidade")
    private String nomeEspecialidade;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "especialidade_consulta",
            joinColumns = {@JoinColumn(name = "especialidade_id")},
            inverseJoinColumns = {@JoinColumn(name = "consulta_id")})
    private List<Consulta> consultas = new ArrayList<>();

    public Especialidade() {
    }

    public Especialidade(Long id) {
        this.id = id;
    }

    public Especialidade(String nomeEspacialidade) {
        this.nomeEspecialidade = nomeEspacialidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEspacialidade() {
        return nomeEspecialidade;
    }

    public void setNomeEspacialidade(String nomeEspacialidade) {
        this.nomeEspecialidade = nomeEspacialidade;
    }

    public String getNomeEspecialidade() {
        return nomeEspecialidade;
    }

    public void setNomeEspecialidade(String nomeEspecialidade) {
        this.nomeEspecialidade = nomeEspecialidade;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setCosultas(List<Consulta> cosultas) {
        this.consultas = cosultas;
    }

    public void setCosulta(Consulta cosulta) {
        this.consultas.add(cosulta);
    }
}
