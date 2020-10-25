package com.example.starter.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaDataVagaFORM {
    @NotNull @NotBlank(message = "a data nova não pode ser vazia")
    private String novaData;

    public String getNovaData() {
        return novaData;
    }
}
