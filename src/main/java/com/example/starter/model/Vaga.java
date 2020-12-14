package com.example.starter.model;

import org.springframework.lang.Nullable;

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
    @Nullable
    @ManyToOne(cascade = CascadeType.ALL)
    private Barba barba = null;
    @Nullable
    @ManyToOne(cascade = CascadeType.ALL)
    private Cabelo cabelo = null;

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

    @Nullable
    public Barba getBarba() {
        return barba;
    }

    public void setBarba(@Nullable Barba barba) {
        this.barba = barba;
    }

    @Nullable
    public Cabelo getCabelo() {
        return cabelo;
    }

    public void setCabelo(@Nullable Cabelo cabelo) {
        this.cabelo = cabelo;
    }
}
