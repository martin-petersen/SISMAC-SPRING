package com.example.starter.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class MedicoForm {
    @NotNull @NotEmpty @Length(max = 40)
    private String nomeMedico;
    @NotNull @NotEmpty
    private String conselho;
    @NotNull @NotEmpty
    private String numeroConselho;
    @NotNull
    private List<String> especialidades;

    public String getNomeMedico() {
        return nomeMedico.toUpperCase();
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
