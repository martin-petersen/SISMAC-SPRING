package com.example.starter.dto;

public class AgendamentoDTO {
    private Long id;
    private String dataAgendamento;
    private String nomeCliente;
    private boolean cabelo;
    private boolean barba;

    public AgendamentoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(String dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public boolean isCabelo() {
        return cabelo;
    }

    public void setCabelo(boolean cabelo) {
        this.cabelo = cabelo;
    }

    public boolean isBarba() {
        return barba;
    }

    public void setBarba(boolean barba) {
        this.barba = barba;
    }
}
