package com.example.starter.form;

import javax.validation.constraints.NotNull;

public class ListaEsperaFORM {
    @NotNull(message = "id do paciente não pode ser vazio")
    private Long cliente_id;
    @NotNull(message = "id do usuario não pode ser vazio")
    private Long user_id;
    @NotNull(message = "cabelo é um boolean e não pode ser nulo")
    private boolean cabelo;
    @NotNull(message = "barba é um boolen e não pode ser nulo")
    private boolean barba;


    public Long getCliente_id() {
        return cliente_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public boolean isCabelo() {
        return cabelo;
    }

    public boolean isBarba() {
        return barba;
    }
}
