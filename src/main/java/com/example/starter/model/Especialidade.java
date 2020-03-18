package com.example.starter.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Especialidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, name = "especialidade")
    private String nomeEspecialidade;
    @ManyToMany(mappedBy = "especialidades")
    private List<Procedimento> procedimentos;

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

    public List<Procedimento> getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(List<Procedimento> procedimentos) {
        this.procedimentos = procedimentos;
    }
}
