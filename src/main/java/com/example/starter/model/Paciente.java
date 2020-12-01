package com.example.starter.model;

import com.example.starter.form.AtualizacaoPacienteFORM;

import javax.persistence.*;

@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String nomePaciente;
    @Column(unique = true)
    private String carteiraSUS;
    @Column(unique = true)
    private String cpf;
    private String cidade;
    private String bairro;
    private String complemento;
    private String dataNascimento;
    private String telefone;
    private String numero;

    public Paciente(String nomePaciente, String carteiraSUS, String cpf, String cidade, String bairro, String complemento, String dataNascimento, String telefone, String numero) {
        this.nomePaciente = nomePaciente;
        this.carteiraSUS = carteiraSUS;
        this.cpf = cpf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.complemento = complemento;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.numero = numero;
    }

    public Paciente(String cpf, String carteiraSUS) {
        this.cpf = cpf;
        this.carteiraSUS = carteiraSUS;
    }

    public Paciente(AtualizacaoPacienteFORM paciente) {
        this.nomePaciente = paciente.getNome();
        this.cpf = paciente.getCpf();
        this.carteiraSUS = paciente.getCarteiraSUS();
        this.cidade = paciente.getCidade();
        this.bairro = paciente.getBairro();
        this.complemento = paciente.getComplemento();
        this.dataNascimento = paciente.getDataNascimento();
        this.telefone = paciente.getTelefone();
        this.numero = paciente.getNumero();
    }

    public Paciente() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPaciente(Paciente paciente) {
        this.id = paciente.getId();
        setPacienteUpdate(paciente);
    }

    public void setPacienteUpdate(Paciente paciente) {
        this.nomePaciente = paciente.getNomePaciente();
        this.cpf = paciente.getCpf();
        this.carteiraSUS = paciente.getCarteiraSUS();
        this.cidade = paciente.getCidade();
        this.bairro = paciente.getBairro();
        this.complemento = paciente.getComplemento();
        this.dataNascimento = paciente.getDataNascimento();
        this.telefone = paciente.getTelefone();
        this.numero = paciente.getNumero();

    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getCarteiraSUS() {
        return carteiraSUS;
    }

    public void setCarteiraSUS(String carteiraSUS) {
        this.carteiraSUS = carteiraSUS;
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
}
