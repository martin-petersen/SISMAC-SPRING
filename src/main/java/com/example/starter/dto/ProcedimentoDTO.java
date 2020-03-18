package com.example.starter.dto;

public class ProcedimentoDTO {
    private Long id;
    private String nomeProcedimento;

    public ProcedimentoDTO(Long id, String nomeProcedimento) {
        this.id = id;
        this.nomeProcedimento = nomeProcedimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProcedimento() {
        return nomeProcedimento;
    }

    public void setNomeProcedimento(String nomeProcedimento) {
        this.nomeProcedimento = nomeProcedimento;
    }
}
