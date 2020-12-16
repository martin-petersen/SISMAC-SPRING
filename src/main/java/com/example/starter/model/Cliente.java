package com.example.starter.model;

import com.example.starter.form.AtualizacaoClienteFORM;

import javax.persistence.*;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String nomeCliente;
    private boolean fidelidade;
    @Column(unique = true)
    private String cpf;
    private String cidade;
    private String bairro;
    private String complemento;
    private String dataNascimento;
    private String telefone;
    private String numero;

    public Cliente(String nomeCliente, String cpf, String cidade, String bairro, String complemento, String dataNascimento, String telefone, String numero) {
        this.nomeCliente = nomeCliente;
        this.cpf = cpf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.complemento = complemento;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.numero = numero;
    }

    public Cliente(String cpf) {
        this.cpf = cpf;
    }

    public Cliente(AtualizacaoClienteFORM cliente) {
        this.nomeCliente = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.cidade = cliente.getCidade();
        this.bairro = cliente.getBairro();
        this.complemento = cliente.getComplemento();
        this.dataNascimento = cliente.getDataNascimento();
        this.telefone = cliente.getTelefone();
        this.numero = cliente.getNumero();
    }

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.id = cliente.getId();
        setClienteUpdate(cliente);
    }

    public void setClienteUpdate(Cliente paciente) {
        this.nomeCliente = paciente.getNomeCliente();
        this.cpf = paciente.getCpf();
        this.cidade = paciente.getCidade();
        this.bairro = paciente.getBairro();
        this.complemento = paciente.getComplemento();
        this.dataNascimento = paciente.getDataNascimento();
        this.telefone = paciente.getTelefone();
        this.numero = paciente.getNumero();
        
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
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

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public boolean getFidelidade() {
        return this.fidelidade;
    }

    public void setFidelidade(boolean fidelidade) {
        this.fidelidade = fidelidade;
    }
}
