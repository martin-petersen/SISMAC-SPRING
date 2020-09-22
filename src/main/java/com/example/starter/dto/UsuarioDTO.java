package com.example.starter.dto;

import com.example.starter.model.Usuario;

public class UsuarioDTO {
    private Long id;
    private String username;
    private String nome;
    private boolean validate;
    private Long paciente_id;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.username = usuario.getEmail();
        this.nome = usuario.getNome();
        this.paciente_id = usuario.getId_paciente();
    }

    public UsuarioDTO(Long id, String username, String nome, Long paciente_id) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.paciente_id = paciente_id;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public Long getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(Long paciente_id) {
        this.paciente_id = paciente_id;
    }
}
