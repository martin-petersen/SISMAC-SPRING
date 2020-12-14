package com.example.starter.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VagaFORM {
    @NotNull(message = "Data não pode ser nula")
    @NotEmpty(message = "Data não pode ser vazia")
    private String data;
    @NotNull(message = "Quantidade de vagas não pode ser nula")
    private Integer vagasOfertadas;
    private boolean barba;
    private boolean cabelo;


    public String getData() {
        return data;
    }

    public Integer getVagasOfertadas() {
        return vagasOfertadas;
    }

    public boolean isBarba() {
        return barba;
    }

    public boolean isCabelo() {
        return cabelo;
    }
}
