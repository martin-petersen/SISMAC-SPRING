package com.example.starter.form;

import javax.validation.constraints.NotNull;

public class DeleteFilaFORM {
    @NotNull
    private Long user_id;
    @NotNull
    private String motivoCancelamento;

    public Long getUser_id() {
        return user_id;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }
}
