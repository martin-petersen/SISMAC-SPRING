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
    private Long cliente;
    @Nullable
    private boolean barba;
    private boolean cabelo;
    private Long vaga;

    public Agendamento(LocalDate localDate, ListaEspera listaEspera, Long vaga) {
        this.dataAgendamento = localDate;
        this.cliente = listaEspera.getCliente().getId();
        if(listaEspera.getCabelo() != null) {
            this.cabelo = true;
            this.barba = false;
        } else {
            this.barba = true;
            this.cabelo = false;
        }
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

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public boolean isBarba() {
        return barba;
    }

    public void setBarba(boolean barba) {
        this.barba = barba;
    }

    public boolean isCabelo() {
        return cabelo;
    }

    public void setCabelo(boolean cabelo) {
        this.cabelo = cabelo;
    }

    public Long getVaga() {
        return vaga;
    }

    public void setVaga(Long vaga) {
        this.vaga = vaga;
    }
}
