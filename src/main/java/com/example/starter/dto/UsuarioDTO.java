package com.example.starter.dto;

import com.example.starter.model.Usuario;

public class UsuarioDTO {
    private Long id;
    private String username;
    private String nome;
    private boolean validate;
    private Long cliente_id;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.username = usuario.getEmail();
        this.nome = usuario.getNome();
        if(usuario.getCliente() != null) {
            this.cliente_id = usuario.getCliente().getId();
        }
        this.validate = usuario.isValidate();
    }

    public UsuarioDTO(Long id, String username, String nome, Long cliente_id, boolean validate) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.cliente_id = cliente_id;
        this.validate = validate;
    }

    public UsuarioDTO(Long id, String username, String nome, boolean validate) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.validate = validate;
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

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }
}
