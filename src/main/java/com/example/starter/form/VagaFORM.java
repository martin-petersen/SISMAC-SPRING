package com.example.starter.form;

import javax.validation.constraints.NotNull;

public class VagaFORM {
    @NotNull
    private String data;
    @NotNull
    private Integer vagasOfertadas;
    private Long especialidade;
    private Long exame;
    private boolean consulta;

    public String getData() {
        return data;
    }

    public Integer getVagasOfertadas() {
        return vagasOfertadas;
    }

    public Long getEspecialidade() {
        return especialidade;
    }

    public Long getExame() {
        return exame;
    }

    public boolean isConsulta() {
        return consulta;
    }
}
