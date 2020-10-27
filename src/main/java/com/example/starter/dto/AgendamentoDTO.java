package com.example.starter.dto;

public class AgendamentoDTO {
    private Long id;
    private String dataAgendamento;
    private String nomePaciente;
    private boolean consulta;
    private String nomeEspecialidade;
    private String nomeExame;

    public AgendamentoDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getDataAgendamento() {
        return dataAgendamento;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public boolean isConsulta() {
        return consulta;
    }

    public String getNomeEspecialidade() {
        return nomeEspecialidade;
    }

    public String getNomeExame() {
        return nomeExame;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDataAgendamento(String dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public void setConsulta(boolean consulta) {
        this.consulta = consulta;
    }

    public void setNomeEspecialidade(String nomeEspecialidade) {
        this.nomeEspecialidade = nomeEspecialidade;
    }

    public void setNomeExame(String nomeExame) {
        this.nomeExame = nomeExame;
    }
}
