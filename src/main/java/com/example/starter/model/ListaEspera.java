package com.example.starter.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class ListaEspera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataEntradaLista = LocalDate.now();
    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente cliente;
    @Nullable
    @ManyToOne(cascade = CascadeType.ALL)
    private Barba barba = null;
    @Nullable
    @ManyToOne(cascade = CascadeType.ALL)
    private Cabelo cabelo = null;
    private boolean ativo = true;
    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario usuarioLastUpdate;
    private LocalDateTime lastUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEntradaLista() {
        return dataEntradaLista;
    }

    public void setDataEntradaLista(LocalDate dataEntradaLista) {
        this.dataEntradaLista = dataEntradaLista;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Usuario getUsuarioLastUpdate() {
        return usuarioLastUpdate;
    }

    public void setUsuarioLastUpdate(Usuario usuarioLastUpdate) {
        this.usuarioLastUpdate = usuarioLastUpdate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
