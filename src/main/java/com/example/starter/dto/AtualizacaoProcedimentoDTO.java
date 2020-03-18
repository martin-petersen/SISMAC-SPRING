package com.example.starter.dto;

import java.util.List;

public class AtualizacaoProcedimentoDTO {
    private String nomeProcedimento;
    private List<String> especialidades;

    public AtualizacaoProcedimentoDTO() {
    }

    public AtualizacaoProcedimentoDTO(String nomeProcedimento, List<String> especialidades) {
        this.nomeProcedimento = nomeProcedimento;
        this.especialidades = especialidades;
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
