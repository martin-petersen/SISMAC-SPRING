package com.example.starter.service;

import com.example.starter.dto.AgendamentoDTO;
import com.example.starter.dto.PacienteAgendamentoDTO;
import com.example.starter.model.Agendamento;
import com.example.starter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.starter.dto.PacienteAgendamentoDTO;
import java.util.ArrayList;
import java.util.List;


public abstract class SolicitacaoService{
    
    @Autowired
    public AgendamentoRepository agendamentoRepository;
    @Autowired
    public PacienteRepository pacienteRepository;
    
    public List<PacienteAgendamentoDTO> buscarPorVaga(Long id) {
        List<Agendamento> agendamentos = agendamentoRepository.findByVaga(id);
        List<PacienteAgendamentoDTO> pacientes = new ArrayList<>();
        for (Agendamento a:
             agendamentos) {
            pacientes.add(new PacienteAgendamentoDTO(pacienteRepository.findById(a.getPaciente_id()).get()));
        }
        return pacientes;
    }
}