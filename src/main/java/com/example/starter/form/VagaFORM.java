package com.example.starter.form;

import javax.validation.constraints.NotNull;

public class VagaFORM {
    @NotNull
    private String data;
    @NotNull
    private Integer vagasOfertadas;
    @NotNull
    private String especialidade;
    private String exame;
    private boolean consulta;

    public String getData() {
        return data;
    }

    public Integer getVagasOfertadas() {
        return vagasOfertadas;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getExame() {
        return exame;
    }

    public boolean isConsulta() {
        return consulta;
    }
}
