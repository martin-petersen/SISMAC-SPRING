package com.example.starter.form;

import com.example.starter.model.Paciente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PacienteFORM {
    @NotEmpty(message = "nome não pdoe ser vazio") @NotNull(message = "nome não pode ser nulo") @Length(message = "nome não pode ter mais que 40 caracteres",max = 40)
    private String nome;
    @NotEmpty(message = "cpf não pdoe ser vazio") @NotNull(message = "cpf não pode ser nulo")
    private String cpf;
    @NotEmpty(message = "carteira sus não pdoe ser vazio") @NotNull(message = "carteira sus  não pode ser nulo")
    private String carteiraSUS;
    @NotEmpty(message = "cidade  não pdoe ser vazio") @NotNull(message = "cidade não pode ser nulo")
    private String cidade;
    @NotEmpty(message = "bairro não pdoe ser vazio") @NotNull(message = "bairro não pode ser nulo")
    private String bairro;
    private String complemento;
    @NotEmpty(message = "data de nascimento não pdoe ser vazio") @NotNull(message = "data de nascimento não pode ser nulo")
    private String dataNascimento;
    @NotEmpty(message = "telefone  não pdoe ser vazio") @NotNull(message = "telefone  não pode ser nulo")
    private String telefone;
    @NotEmpty(message = "número não pdoe ser vazio") @NotNull(message = "número não pode ser nulo")
    private String numero;

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

    public String getNumero() {
        return numero;
    }

    public Paciente convert() {
        return new Paciente(getNome().toUpperCase(), getCarteiraSUS(), getCpf(), getCidade().toUpperCase(), getBairro().toUpperCase(), getComplemento().toUpperCase(), getDataNascimento(), getTelefone(), getNumero());
    }
}
