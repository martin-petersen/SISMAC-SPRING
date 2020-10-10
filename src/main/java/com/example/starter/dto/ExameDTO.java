package com.example.starter.dto;

public class ExameDTO {
    private Long id;
    private String nomeExame;

    public ExameDTO() {
    }

    public ExameDTO(Long id, String nomeExame) {
        this.id = id;
        this.nomeExame = nomeExame;
    }
}
