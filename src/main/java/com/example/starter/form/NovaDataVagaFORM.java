package com.example.starter.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaDataVagaFORM {
    @NotNull(message = "nova data não pode ser nulo") @NotBlank(message = "data nova não pode ser vazio")
    private String novaData;

    public String getNovaData() {
        return novaData;
    }
}
