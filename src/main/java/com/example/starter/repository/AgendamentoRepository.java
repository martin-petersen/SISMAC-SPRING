package com.example.starter.repository;

import com.example.starter.model.Agendamento;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository  extends CrudRepository<Agendamento, Long> {
    List<Agendamento> buscarPorData(LocalDateTime dataAgendamento);
    List<Agendamento> buscarPorNome(String nomePaciente);
    List<Agendamento> buscarPorEspecialidade (String especialidade);
}
