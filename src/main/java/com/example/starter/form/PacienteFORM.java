package com.example.starter.form;

import com.example.starter.model.Paciente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PacienteFORM {
    @NotEmpty @NotNull @Length(max = 40)
    private String nome;
    @NotEmpty @NotNull
    private String cpf;
    @NotEmpty @NotNull
    private String carteiraSUS;
    @NotEmpty @NotNull
    private String cidade;
    @NotEmpty @NotNull
    private String bairro;
    private String complemento;
    @NotEmpty @NotNull
    private String dataNascimento;
    @NotEmpty @NotNull
    private String telefone;

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

    public String getCarteiraSUS() {
        return carteiraSUS;
    }

    public void setCarteiraSUS(String carteiraSUS) {
        this.carteiraSUS = carteiraSUS;
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

    public Paciente convert() {
        return new Paciente(getNome().toUpperCase(), getCarteiraSUS(), getCpf(), getCidade(), getBairro(), getComplemento(), getDataNascimento(), getTelefone());
    }
}
