package com.example.starter.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Procedimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nomeProcedimento;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "procedimento_especialidade",
            joinColumns = {@JoinColumn(name = "procedimento_id")},
            inverseJoinColumns = {@JoinColumn(name = "especialidade_id")})
    private List<Especialidade> especialidades = new ArrayList<>();

    public Procedimento() {
    }

    public Procedimento(Long id) {
        this.id = id;
    }

    public Procedimento(String nomeProcedimento) {
        this.nomeProcedimento = nomeProcedimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProcedimento() {
        return nomeProcedimento;
    }

    public void setNomeProcedimento(String nomeProcedimento) {
        this.nomeProcedimento = nomeProcedimento;
    }

    public List<Especialidade> getEspecialidade() {
        return especialidades;
    }

    public void setEspecialidade(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    public void addEspecialidade(Especialidade especialidade) {
        this.especialidades.add(especialidade);
    }
}
