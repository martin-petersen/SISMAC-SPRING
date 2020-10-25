package com.example.starter.model;

import javax.persistence.*;

@Entity
public class Anexo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeAnexo;
    private String tipoAnexo;

    @Lob
    private byte[] data;

    public Anexo() {
    }

    public Anexo(String nomeAnexo, String tipoAnexo, byte[] data) {
        this.nomeAnexo = nomeAnexo;
        this.tipoAnexo = tipoAnexo;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAnexo() {
        return nomeAnexo;
    }

    public void setNomeAnexo(String nomeAnexo) {
        this.nomeAnexo = nomeAnexo;
    }

    public String getTipoAnexo() {
        return tipoAnexo;
    }

    public void setTipoAnexo(String tipoAnexo) {
        this.tipoAnexo = tipoAnexo;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
