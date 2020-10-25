package com.example.starter.form;

import javax.validation.constraints.NotNull;

public class ExameFORM {
    @NotNull(message  = "nome do exame não pode ser vazio")
    private String nomeExame;
    private boolean autorizacao;

    public String getNomeExame() {
        return nomeExame;
    }

    public boolean isAutorizacao() {
        return autorizacao;
    }
}
