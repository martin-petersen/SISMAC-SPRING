package com.example.starter.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioFORM {
    @NotNull(message = "nome não pode ser nulo") @NotBlank(message = "nome não pode ser vazio")
    private String nome;
    @NotNull(message = "email não pode ser nulo") @NotBlank(message = "email não pode ser vazio")
    private String email;
    @NotNull(message = "senha não pode ser nulo") @NotBlank(message = "senha não pode ser vazio")
    private String senha;
    private Long role;

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

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }
}
