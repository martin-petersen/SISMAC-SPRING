package com.example.starter.dto;

import com.example.starter.model.Especialidade;

import java.util.ArrayList;
import java.util.List;

public class DetalhamentoProcedimentoDTO {
    private Long id;
    private String nomeProcedimento;
    private List<String> especialidades = new ArrayList<>();

    public DetalhamentoProcedimentoDTO(Long id, String nomeProcedimento, List<Especialidade> especialidades) {
        this.id = id;
        this.nomeProcedimento = nomeProcedimento;
        this.especialidades = convert(especialidades);
    }

    private List<String> convert(List<Especialidade> especialidades) {
        List<String> listaEspecialidades = new ArrayList<>();
        for (Especialidade e:
             especialidades) {
            listaEspecialidades.add(e.getNomeEspacialidade());
        }
        return listaEspecialidades;
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

    public List<String> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<String> especialidades) {
        this.especialidades = especialidades;
    }
}
