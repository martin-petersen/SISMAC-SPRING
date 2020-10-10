package com.example.starter.dto;

public class TokenDTO {
    private String token;
    private String tipo;
    private boolean validate;
    private Long id;

    public TokenDTO(String token, String tipo, boolean validate, Long id) {
        this.token = token;
        this.tipo = tipo;
        this.validate = validate;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
