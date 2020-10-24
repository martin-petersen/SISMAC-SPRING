package com.example.starter.model;

import javax.persistence.*;

@Entity
public class Encaminhamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Anexo anexo;
    private boolean autorizado;
    private String motivo;


    public Encaminhamento() {
    }

    public Encaminhamento(Anexo anexo) {
        this.anexo = anexo;
    }

    public Encaminhamento(Long id, Anexo anexo, boolean autorizado, String motivo) {
        this.id = id;
        this.anexo = anexo;
        this.autorizado = autorizado;
        this.motivo = motivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

    public boolean isAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
