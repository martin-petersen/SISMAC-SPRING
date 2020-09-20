package com.example.starter.form;

import com.example.starter.model.Paciente;

public class AtualizacaoPacienteFORM {
    private String nome;
    private String cpf;
    private String carteiraSUS;
    private String cidade;
    private String bairro;
    private String complemento;
    private String dataNascimento;
    private String telefone;

    public String getNome() {
        return nome.toUpperCase();
    }

    public String getCpf() {
        return cpf;
    }

    public String getCarteiraSUS() {
        return carteiraSUS;
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

    public Paciente convert() {
        return new Paciente(getNome().toUpperCase(),getCarteiraSUS(),getCpf(),getCidade(),getBairro(),getComplemento(),getDataNascimento(),getTelefone());
    }
}
