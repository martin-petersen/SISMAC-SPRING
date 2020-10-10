package com.example.starter.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioFORM {
    @NotNull @NotBlank
    private String nome;
    @NotNull @NotBlank
    private String email;
    @NotNull @NotBlank
    private String senha;
    private String paciente_id;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(String paciente_id) {
        this.paciente_id = paciente_id;
    }
}
