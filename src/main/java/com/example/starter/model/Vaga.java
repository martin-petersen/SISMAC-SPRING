package com.example.starter.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private Integer vagasOfertadas;
    private Integer vagasRestantes;
    @ManyToOne(cascade = CascadeType.ALL)
    private Especialidade especialidade;
    @ManyToOne(cascade = CascadeType.ALL)
    private Exame exame = null;
    @ManyToOne(cascade = CascadeType.ALL)
    private Consulta consulta = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getVagasOfertadas() {
        return vagasOfertadas;
    }

    public void setVagasOfertadas(Integer vagasOfertadas) {
        this.vagasOfertadas = vagasOfertadas;
    }

    public Integer getVagasRestantes() {
        return vagasRestantes;
    }

    public void setVagasRestantes(Integer vagasRestantes) {
        this.vagasRestantes = vagasRestantes;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}
