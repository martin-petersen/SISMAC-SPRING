package com.example.starter.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Vagas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime data;
    private Integer vagas;
    @ManyToOne(cascade = CascadeType.ALL)
    private Especialidade especialidade;
    @ManyToOne(cascade = CascadeType.ALL)
    private Procedimento procedimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }
}
