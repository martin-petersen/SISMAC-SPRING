package com.example.starter.dto;

import com.example.starter.model.Especialidade;
import com.example.starter.model.Medico;

import java.util.ArrayList;
import java.util.List;

public class MedicoDTO {
    private String nomeMedico;
    private String conselho;
    private String numeroConselho;
    private List<String> especialidades = new ArrayList<>();

    public MedicoDTO(String nomeMedico, String conselho, String numeroConselho, List<Especialidade> especialidades) {
        this.nomeMedico = nomeMedico;
        this.conselho = conselho;
        this.numeroConselho = numeroConselho;
        for (Especialidade e:
             especialidades) {
            this.especialidades.add(e.getNomeEspacialidade());
        }
    }

    public MedicoDTO(Medico medico) {
        this.nomeMedico = medico.getNomeMedico();
        this.conselho = medico.getConselho();
        this.numeroConselho = medico.getNumeroConselho();
        for (Especialidade e:
                medico.getEspecialidades()) {
            this.especialidades.add(e.getNomeEspacialidade());
        }
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
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

    public List<String> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<String> especialidades) {
        this.especialidades = especialidades;
    }
}
