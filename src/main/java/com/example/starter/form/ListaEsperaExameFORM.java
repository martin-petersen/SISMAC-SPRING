package com.example.starter.form;

import javax.validation.constraints.NotNull;

public class ListaEsperaExameFORM {
    @NotNull
    private Long paciente_id;
    @NotNull
    private Long user_id;
    private Long exame_id;

    public Long getPaciente_id() {
        return paciente_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public Long getExame_id() {
        return exame_id;
    }
}
