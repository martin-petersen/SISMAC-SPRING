package com.example.starter.model;

import com.example.starter.form.MedicoForm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeMedico;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "medico_especialidade",
            joinColumns = {@JoinColumn(name = "medico_id")},
            inverseJoinColumns = {@JoinColumn(name = "especialidade_id")})
    private List<Especialidade> especialidades = new ArrayList<>();
    private String conselho;
    @Column(unique = true)
    private String numeroConselho;

    public Medico() {
    }

    public Medico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public Medico(MedicoForm medicoForm) {
        this.nomeMedico = medicoForm.getNomeMedico();
        this.conselho = medicoForm.getConselho();
        this.numeroConselho = medicoForm.getNumeroConselho();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
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
