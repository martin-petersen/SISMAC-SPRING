package com.example.starter.form;

import com.example.starter.model.Especialidade;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EspecialidadeFORM {
    @NotNull @NotEmpty @Length(max = 30)
    private String nomeEspecialidade;

    public EspecialidadeFORM() {
    }

    public EspecialidadeFORM(String nomeEspecialidade) {
        this.nomeEspecialidade = nomeEspecialidade.toUpperCase();
    }

    public String getNomeEspecialidade() {
        return nomeEspecialidade.toUpperCase();
    }

    public void setNomeEspecialidade(String nomeEspecialidade) {
        this.nomeEspecialidade = nomeEspecialidade;
    }

    public Especialidade convert() {
        return new Especialidade(getNomeEspecialidade().toUpperCase());
    }
}
