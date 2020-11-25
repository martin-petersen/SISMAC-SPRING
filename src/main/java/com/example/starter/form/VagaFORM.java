package com.example.starter.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VagaFORM {
    @NotNull(message = "Data não pode ser nula")
    @NotEmpty(message = "Data não pode ser vazia")
    private String data;
    @NotNull(message = "Quantidade de vagas não pode ser nula")
    private Integer vagasOfertadas;
    private Long especialidade;
    private Long exame;
    private boolean consulta;
    private String medico;
    @NotNull(message = "Lugar é obrigatório")
    @NotEmpty(message = "Lugar é obrigatório")
    private String lugar;


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

    public String getMedico() {
        return medico;
    }

    public String getLugar() {
        return lugar;
    }
}
