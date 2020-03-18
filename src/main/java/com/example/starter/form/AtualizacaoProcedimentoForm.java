package com.example.starter.form;

import java.util.List;

public class AtualizacaoProcedimentoForm {
    private String nomeProcedimento;
    private List<Long> especialidades;

    public String getNomeProcedimento() {
        return nomeProcedimento;
    }

    public void setNomeProcedimento(String nomeProcedimento) {
        this.nomeProcedimento = nomeProcedimento;
    }

    public List<Long> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Long> especialidades) {
        this.especialidades = especialidades;
    }
}
