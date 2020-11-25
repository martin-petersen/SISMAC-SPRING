package com.example.starter.dto;

import com.example.starter.model.Paciente;

public class PacienteAgendamentoDTO {
    private String nome;
    private String CPF;

    public PacienteAgendamentoDTO() {
    }

    public PacienteAgendamentoDTO(Paciente paciente) {
        this.nome = paciente.getNomePaciente();
        this.CPF = paciente.getCpf();
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
