package com.example.starter.model;

import javax.persistence.Entity;

@Entity
public class Usuario extends Pessoa{
    private String senha;

    private String tipoUsuario;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
