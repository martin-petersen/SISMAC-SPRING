package com.example.starter.form;

import com.example.starter.model.Cliente;

public class AtualizacaoClienteFORM {
    private String nome;
    private String cpf;
    private String cidade;
    private String bairro;
    private String complemento;
    private String dataNascimento;
    private String telefone;
    private String numero;

    public String getNome() {
        return nome.toUpperCase();
    }

    public String getCpf() {
        return cpf;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getNumero() {
        return numero;
    }

    public Cliente convert() {
        return new Cliente(getNome().toUpperCase(),getCpf(),getCidade(),getBairro(),getComplemento(),getDataNascimento(),getTelefone(), getNumero());
    }
}
