package com.example.starter.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Procedimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "procedimento")
    private String nomeProcedimento;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Especialidade> especialidades;
    private int fichasDiarias;

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

    public int getFichasDiarias() {
        return fichasDiarias;
    }

    public void setFichasDiarias(int fichasDiarias) {
        this.fichasDiarias = fichasDiarias;
    }
}
