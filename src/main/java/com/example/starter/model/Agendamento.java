package com.example.starter.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataAgendamento;
    private final LocalDateTime dataCriacao = LocalDateTime.now();
    private Long paciente_id;
    @Nullable
    private Long especialidade_id;
    @Nullable
    private Long exame_id;
    private boolean consulta;

    public Agendamento(LocalDate localDate, ListaEspera listaEspera) {
        this.dataAgendamento = localDate;
        this.paciente_id = listaEspera.getPaciente().getId();
        if(listaEspera.getEspecialidade() != null) {
            this.consulta = true;
            this.especialidade_id = listaEspera.getEspecialidade().getId();
        } else {
            this.exame_id = listaEspera.getExame().getId();
            this.consulta = false;
        }

    }

    public Agendamento() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Long getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(Long paciente_id) {
        this.paciente_id = paciente_id;
    }

    @Nullable
    public Long getEspecialidade_id() {
        return especialidade_id;
    }

    public void setEspecialidade_id(@Nullable Long especialidade_id) {
        this.especialidade_id = especialidade_id;
    }

    @Nullable
    public Long getExame_id() {
        return exame_id;
    }

    public void setExame_id(@Nullable Long exame_id) {
        this.exame_id = exame_id;
    }

    public boolean isConsulta() {
        return consulta;
    }
}
