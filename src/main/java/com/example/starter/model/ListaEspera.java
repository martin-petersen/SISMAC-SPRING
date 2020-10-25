package com.example.starter.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ListaEspera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataEntradaLista = LocalDateTime.now();
    @ManyToOne(cascade = CascadeType.ALL)
    private Paciente paciente;
    @Nullable
    @ManyToOne(cascade = CascadeType.ALL)
    private Especialidade especialidade = null;
    @Nullable
    @ManyToOne(cascade = CascadeType.ALL)
    private Exame exame = null;
    @Nullable
    @ManyToOne(cascade = CascadeType.ALL)
    private Consulta consulta = null;
    @Nullable
    @ManyToOne(cascade = CascadeType.ALL)
    private Encaminhamento encaminhamento = null;
    private boolean requerAutorizacao;
    private boolean ativo = true;
    private String motivoRemocao;
    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario usuarioLastUpdate;
    private LocalDateTime lastUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataEntradaLista() {
        return dataEntradaLista;
    }

    public void setDataEntradaLista(LocalDateTime dataEntradaLista) {
        this.dataEntradaLista = dataEntradaLista;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Nullable
    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(@Nullable Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    @Nullable
    public Exame getExame() {
        return exame;
    }

    public void setExame(@Nullable Exame exame) {
        this.exame = exame;
    }

    @Nullable
    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(@Nullable Consulta consulta) {
        this.consulta = consulta;
    }

    @Nullable
    public Encaminhamento getEncaminhamento() {
        return encaminhamento;
    }

    public void setEncaminhamento(@Nullable Encaminhamento encaminhamento) {
        this.encaminhamento = encaminhamento;
    }

    public boolean isRequerAutorizacao() {
        return requerAutorizacao;
    }

    public void setRequerAutorizacao(boolean requerAutorizacao) {
        this.requerAutorizacao = requerAutorizacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getMotivoRemocao() {
        return motivoRemocao;
    }

    public void setMotivoRemocao(String motivoRemocao) {
        this.motivoRemocao = motivoRemocao;
    }

    public Usuario getUsuarioLastUpdate() {
        return usuarioLastUpdate;
    }

    public void setUsuarioLastUpdate(Usuario usuarioLastUpdate) {
        this.usuarioLastUpdate = usuarioLastUpdate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
