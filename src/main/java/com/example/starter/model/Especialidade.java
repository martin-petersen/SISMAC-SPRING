package com.example.starter.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Especialidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String nome;

    private int vagasPorMes;

    private int vagasPorDia;

    private LocalDateTime inicioVigencia;

    private LocalDateTime fimVigencia;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVagasPorMes() {
        return vagasPorMes;
    }

    public void setVagasPorMes(int vagasPorMes) {
        this.vagasPorMes = vagasPorMes;
    }

    public int getVagasPorDia() {
        return vagasPorDia;
    }

    public void setVagasPorDia(int vagasPorDia) {
        this.vagasPorDia = vagasPorDia;
    }

    public LocalDateTime getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(LocalDateTime inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public LocalDateTime getFimVigencia() {
        return fimVigencia;
    }

    public void setFimVigencia(LocalDateTime fimVigencia) {
        this.fimVigencia = fimVigencia;
    }
}
