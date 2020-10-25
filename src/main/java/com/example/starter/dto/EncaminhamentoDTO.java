package com.example.starter.dto;

public class EncaminhamentoDTO {
    private Long id;
    private boolean autorizado;
    private String motivo;

    public EncaminhamentoDTO(Long id, boolean autorizado, String motivo) {
        this.id = id;
        this.autorizado = autorizado;
        this.motivo = motivo;
    }

    public Long getId() {
        return id;
    }

    public boolean isAutorizado() {
        return autorizado;
    }

    public String getMotivo() {
        return motivo;
    }
}
