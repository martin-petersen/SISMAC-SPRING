package com.example.starter.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ListaEspera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataEntradaLista = LocalDateTime.now();
    @ManyToOne(cascade = CascadeType.ALL)
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "especialidade")
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

    public LocalDateTime getDataEntradaLista() {
        return dataEntradaLista;
    }

    public void setDataEntradaLista(LocalDateTime dataEntradaLista) {
        this.dataEntradaLista = dataEntradaLista;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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
