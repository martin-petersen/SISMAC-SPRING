package com.example.starter.dto;

import com.example.starter.model.Consulta;
import com.example.starter.model.Especialidade;
import com.example.starter.model.Exame;

import java.time.LocalDate;

public class VagaDTO {
    private LocalDate data;
    private Integer vagasOfertadas;
    private Integer vagasRestantes;
    private String especialidade;
    private String exame = null;
    private String consulta = null;

    public VagaDTO(LocalDate data, Integer vagasOfertadas, Integer vagasRestantes, Exame exame) {
        this.data = data;
        this.vagasOfertadas = vagasOfertadas;
        this.vagasRestantes = vagasRestantes;
        this.exame = exame.getNomeExame();
    }

    public VagaDTO(LocalDate data, Integer vagasOfertadas, Integer vagasRestantes, Especialidade especialidade, Consulta consulta) {
        this.data = data;
        this.vagasOfertadas = vagasOfertadas;
        this.vagasRestantes = vagasRestantes;
        this.especialidade = especialidade.getNomeEspecialidade();
        this.consulta = consulta.getNome();
    }

    public LocalDate getData() {
        return data;
    }

    public Integer getVagasOfertadas() {
        return vagasOfertadas;
    }

    public Integer getVagasRestantes() {
        return vagasRestantes;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getExame() {
        return exame;
    }

    public String getConsulta() {
        return consulta;
    }
}
