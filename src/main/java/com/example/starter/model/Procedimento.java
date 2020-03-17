package com.example.starter.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Procedimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, name = "procedimento")
    private String nomeProcedimento;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "procedimento_especialidade",
    joinColumns = {@JoinColumn(name = "procedimento_especialidade_procedimento_id")},
    inverseJoinColumns = {@JoinColumn(name = "especialidade_id")})
    private List<Especialidade> especialidades;

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

    public List<Especialidade> getEspecialidadeId() {
        return especialidades;
    }

    public void setEspecialidadeId(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }
}
