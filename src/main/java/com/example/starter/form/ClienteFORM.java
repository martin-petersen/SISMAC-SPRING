package com.example.starter.form;

import com.example.starter.model.Cliente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ClienteFORM {
    @NotEmpty @NotNull(message = "nome não pode ser vazio") @Length(max = 40)
    private String nome;
    @NotEmpty @NotNull(message = "cpf não pode ser vazio")
    private String cpf;
    @NotEmpty @NotNull(message = "cidade não pode ser vazio")
    private String cidade;
    @NotEmpty @NotNull(message = "bairro não pode ser vazio")
    private String bairro;
    private String complemento;
    @NotEmpty @NotNull(message = "data de nascimento não pode ser vazio")
    private String dataNascimento;
    @NotEmpty @NotNull(message = "telefone não pode ser vazio")
    private String telefone;
    @NotEmpty @NotNull(message = "número não pode ser vazio")
    private String numero;
    @NotNull(message = "fidelidade não pode ser vazio")
    private boolean fidelidade;

    public String getNome() {
        return nome.toUpperCase();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNumero() {
        return numero;
    }

    public void setFidelidade(boolean fidelidade) {
        this.fidelidade = fidelidade;
    }

    public boolean getFidelidade() {
        return fidelidade;
    }

    public Cliente convert() {
        return new Cliente(getNome().toUpperCase(), getCpf(), getCidade().toUpperCase(), getBairro().toUpperCase(), getComplemento().toUpperCase(), getDataNascimento(), getTelefone(), getNumero(), getFidelidade());
    }
}
