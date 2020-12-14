package com.example.starter.dto;

import com.example.starter.model.*;

public class ListaEsperaDTO {
    private Long id;
    private String nomeCliente;
    private boolean barba = false;
    private boolean cabelo = false;
    private boolean ativo;

    public ListaEsperaDTO() {
    }

    public ListaEsperaDTO(ListaEspera listaEspera) {
        this.id = listaEspera.getId();
        this.nomeCliente = listaEspera.getCliente().getNomeCliente();
        if(listaEspera.getBarba()!=null)
            this.barba = true;
        else
            this.cabelo = true;
        this.ativo = listaEspera.isAtivo();
    }

    public ListaEsperaDTO(Long id, Cliente cliente, Cabelo cabelo, boolean ativo) {
        this.id = id;
        this.nomeCliente = cliente.getNomeCliente();
        this.cabelo = cabelo != null;
        this.ativo = ativo;
    }

    public ListaEsperaDTO(Long id, Cliente cliente, Barba barba, boolean ativo) {
        this.id = id;
        this.nomeCliente = cliente.getNomeCliente();
        this.barba = barba != null;
        this.ativo = ativo;
    }

    public ListaEsperaDTO(Long id, Cliente cliente, Cabelo cabelo, Barba barba, boolean ativo) {
        this.id = id;
        this.nomeCliente = cliente.getNomeCliente();
        this.cabelo = cabelo != null;
        this.barba = barba != null;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
