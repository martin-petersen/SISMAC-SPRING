package com.example.starter.dto;

import com.example.starter.model.Cabelo;
import com.example.starter.model.Barba;

import java.time.LocalDate;

public class VagaDTO {
    private Long id;
    private LocalDate data;
    private Integer vagasOfertadas;
    private Integer vagasRestantes;
    private String barba = null;
    private String cabelo = null;

    public VagaDTO(Long id, LocalDate data, Integer vagasOfertadas, Integer vagasRestantes, Barba barba) {
        this.id = id;
        this.data = data;
        this.vagasOfertadas = vagasOfertadas;
        this.vagasRestantes = vagasRestantes;
        this.barba = barba.getNome();
    }

    public VagaDTO(Long id, LocalDate data, Integer vagasOfertadas, Integer vagasRestantes, Cabelo cabelo) {
        this.id = id;
        this.data = data;
        this.vagasOfertadas = vagasOfertadas;
        this.vagasRestantes = vagasRestantes;
        this.cabelo = cabelo.getNome();
    }

    public Long getId() {
        return id;
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

    public String getBarba() {
        return barba;
    }

    public String getCabelo() {
        return cabelo;
    }
}
