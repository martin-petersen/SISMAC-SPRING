package com.example.starter.form;

import javax.validation.constraints.NotNull;

public class ExameFORM {
    @NotNull
    private String nomeExame;

    public String getNomeExame() {
        return nomeExame;
    }
}
