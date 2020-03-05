package com.example.starter.model;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Paciente extends Pessoa{
    @Column(unique = true)
    private String carteiraSUS;

    private int idade;

    private String genero;

    @Column(unique = true)
    private String cpf;

    public String getCarteiraSUS() {
        return carteiraSUS;
    }

    public void setCarteiraSUS(String carteiraSUS) {
        this.carteiraSUS = carteiraSUS;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
