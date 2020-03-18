package com.example.starter.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ProcedimentoForm {
    @NotNull @NotEmpty
    private String nomeProcedimento;
    @NotNull
    private List<Long> especialidadesId;

    public String getNomeProcedimento() {
        return nomeProcedimento;
    }

    public void setNomeProcedimento(String nomeProcedimento) {
        this.nomeProcedimento = nomeProcedimento;
    }

    public List<Long> getEspecialidadesId() {
        return especialidadesId;
    }

    public void setEspecialidadesId(List<Long> especialidadesId) {
        this.especialidadesId = especialidadesId;
    }
}
