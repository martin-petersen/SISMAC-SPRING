package com.example.starter.form;

import com.example.starter.model.Paciente;

public class AtualizacaoPacienteFORM {
    private String nome;
    private String cpf;
    private String carteiraSUS;

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCarteiraSUS() {
        return carteiraSUS;
    }

    public Paciente convert() {
        return new Paciente(getNome(),getCpf(),getCarteiraSUS());
    }
}
