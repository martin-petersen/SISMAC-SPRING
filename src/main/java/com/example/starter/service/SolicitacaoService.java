package com.example.starter.service;

import com.example.starter.model.Agendamento;
import com.example.starter.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public abstract class SolicitacaoService{
    @Autowired
    public AgendamentoRepository agendamentoRepository;

    public abstract Number regraDeBuscaDeSolicitante(Agendamento agendamento);

    public List<Number> buscarSolicitantesPorListaDeVaga(Long id) {
        List<Agendamento> solicitacoes = agendamentoRepository.findByVaga(id);
        List<Number> solicitatesIds = new ArrayList<>();
        for (Agendamento s: solicitacoes) {
            solicitatesIds.add(regraDeBuscaDeSolicitante(s));
        }
        return solicitatesIds;
    }
}