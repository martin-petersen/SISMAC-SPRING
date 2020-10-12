package com.example.starter.model;

import com.example.starter.form.ExameFORM;

import javax.persistence.*;

@Entity
public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nomeExame;
    private boolean autorizacao = true;

    public Exame() {
    }

    public Exame(Long id) {
        this.id = id;
    }

    public Exame(Long id, ExameFORM exameFORM) {
        this.nomeExame = exameFORM.getNomeExame().toUpperCase();
        this.autorizacao = exameFORM.isAutorizacao();
    }

    public Exame(ExameFORM exameFORM) {
        this.nomeExame = exameFORM.getNomeExame().toUpperCase();
        this.autorizacao = exameFORM.isAutorizacao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeExame() {
        return nomeExame;
    }

    public void setNomeExame(String nomeExame) {
        this.nomeExame = nomeExame;
    }

    public boolean isAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(boolean autotizacao) {
        this.autorizacao = autotizacao;
    }
}
