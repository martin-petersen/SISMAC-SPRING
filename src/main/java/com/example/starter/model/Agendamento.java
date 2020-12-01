package com.example.starter.model;

import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private LocalDate dataAgendamento;
    private final LocalDateTime dataCriacao = LocalDateTime.now();
    private Long paciente;
    @Nullable
    private Long especialidade;
    @Nullable
    private Long exame;
    private boolean consulta;
    private String medico;
    private String lugar;
    private Long vaga;

    public Agendamento(LocalDate localDate, ListaEspera listaEspera, String medico, String lugar, Long vaga) {
        this.dataAgendamento = localDate;
        this.paciente = listaEspera.getPaciente().getId();
        if(listaEspera.getEspecialidade() != null) {
            this.consulta = true;
            this.especialidade = listaEspera.getEspecialidade().getId();
        } else {
            this.exame = listaEspera.getExame().getId();
            this.consulta = false;
        }
        this.medico = medico;
        this.lugar = lugar;
        this.vaga = vaga;
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
        return paciente;
    }

    public void setPaciente_id(Long paciente_id) {
        this.paciente = paciente_id;
    }

    @Nullable
    public Long getEspecialidade_id() {
        return especialidade;
    }

    public void setEspecialidade_id(@Nullable Long especialidade_id) {
        this.especialidade = especialidade_id;
    }

    @Nullable
    public Long getExame_id() {
        return exame;
    }

    public void setExame_id(@Nullable Long exame_id) {
        this.exame = exame_id;
    }

    public boolean isConsulta() {
        return consulta;
    }

    public void setConsulta(boolean consulta) {
        this.consulta = consulta;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Long getVaga() {
        return vaga;
    }

    public void setVaga(Long vaga) {
        this.vaga = vaga;
    }
}
