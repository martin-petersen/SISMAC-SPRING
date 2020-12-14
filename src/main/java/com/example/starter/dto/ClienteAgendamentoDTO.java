package com.example.starter.dto;

import com.example.starter.model.Cliente;

public class ClienteAgendamentoDTO {
    private String nome;
    private String CPF;

    public ClienteAgendamentoDTO() {
    }

    public ClienteAgendamentoDTO(Cliente cliente) {
        this.nome = cliente.getNomeCliente();
        this.CPF = cliente.getCpf();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
}
