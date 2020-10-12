package com.example.starter.dto;

import com.example.starter.model.Exame;

public class ExameDTO {
    private Long id;
    private String nomeExame;
    private boolean autorizacao;

    public ExameDTO() {
    }

    public ExameDTO(Exame exame) {
        this.id = exame.getId();
        this.nomeExame = exame.getNomeExame();
        this.autorizacao = exame.isAutorizacao();
    }

    public ExameDTO(Long id, String nomeExame, boolean autorizacao) {
        this.id = id;
        this.nomeExame = nomeExame;
        this.autorizacao = autorizacao;
    }

    public Long getId() {
        return id;
    }

    public String getNomeExame() {
        return nomeExame;
    }

    public boolean isAutorizacao() {
        return autorizacao;
    }
}
