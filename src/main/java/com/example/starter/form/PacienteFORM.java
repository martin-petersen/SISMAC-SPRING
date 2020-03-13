package com.example.starter.form;

import com.example.starter.model.Paciente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PacienteFORM {
    @NotEmpty @NotNull @Length(max = 40)
    private String nome;
    @NotEmpty @NotNull
    private String cpf;
    @NotEmpty @NotNull
    private String carteiraSUS;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCarteiraSUS() {
        return carteiraSUS;
    }

    public void setCarteiraSUS(String carteiraSUS) {
        this.carteiraSUS = carteiraSUS;
    }

    public Paciente convert() {
        return new Paciente(getNome(), getCpf(), getCarteiraSUS());
    }
}
