package com.example.starter.dto;

import com.example.starter.model.*;

public class ListaEsperaDTO {
    private Long id;
    private String nomePaciente;
    private String especialidade;
    private String nomeExame;
    private boolean consulta = false;
    private boolean encaminhamento;
    private boolean requerEncaminhamento;
    private boolean ativo;

    public ListaEsperaDTO() {
    }

    public ListaEsperaDTO(ListaEspera listaEspera) {
        this.id = listaEspera.getId();
        this.nomePaciente = listaEspera.getPaciente().getNomePaciente();
        if(listaEspera.getEspecialidade()!=null)
            this.especialidade = listaEspera.getEspecialidade().getNomeEspecialidade();
        else
            this.especialidade = null;
        if(listaEspera.getExame()!=null)
            this.nomeExame = listaEspera.getExame().getNomeExame();
        else
            this.nomeExame = null;
        this.consulta = listaEspera.getConsulta() != null;
        this.encaminhamento = listaEspera.getEncaminhamento() != null;
        this.requerEncaminhamento = listaEspera.isRequerAutorizacao();
        this.ativo = listaEspera.isAtivo();
    }

    public ListaEsperaDTO(Long id, Paciente paciente, Especialidade especialidade, Consulta consulta, boolean ativo) {
        this.id = id;
        this.nomePaciente = paciente.getNomePaciente();
        this.especialidade = especialidade.getNomeEspecialidade();
        this.consulta = consulta != null;
        this.ativo = ativo;
    }

    public ListaEsperaDTO(Long id, Paciente paciente, Exame exame, Encaminhamento encaminhamento, boolean requerEncaminhamento, boolean ativo) {
        this.id = id;
        this.nomePaciente = paciente.getNomePaciente();
        this.nomeExame = exame.getNomeExame();
        this.encaminhamento = encaminhamento != null;
        this.requerEncaminhamento = requerEncaminhamento;
        this.ativo = ativo;
    }

    public ListaEsperaDTO(Long id, Paciente paciente, Especialidade especialidade, Consulta consulta, Exame exame, Encaminhamento encaminhamento, boolean requerEncaminhamento, boolean ativo) {
        this.id = id;
        this.nomePaciente = paciente.getNomePaciente();
        this.especialidade = especialidade.getNomeEspecialidade();
        this.consulta = consulta != null;
        this.nomeExame = exame.getNomeExame();
        this.encaminhamento = encaminhamento != null;
        this.requerEncaminhamento = requerEncaminhamento;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getNomeExame() {
        return nomeExame;
    }

    public boolean isConsulta() {
        return consulta;
    }

    public boolean isEncaminhamento() {
        return encaminhamento;
    }

    public boolean isRequerEncaminhamento() {
        return requerEncaminhamento;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
