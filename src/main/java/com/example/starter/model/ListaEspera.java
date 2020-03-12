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
    @ManyToOne(cascade = CascadeType.ALL)
    private Especialidade especialidade;
    @ManyToOne(cascade = CascadeType.ALL)
    private Procedimento procedimento;

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

    public Procedimento getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }
}
