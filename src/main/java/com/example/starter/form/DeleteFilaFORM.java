package com.example.starter.form;

import javax.validation.constraints.NotNull;

public class DeleteFilaFORM {
    @NotNull(message = "id do usuário não pode ser  vazio")
    private Long user_id;
    @NotNull(message = "motivo do cancelamento não pode ser vazio")
    private String motivoCancelamento;

    public Long getUser_id() {
        return user_id;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }
}
