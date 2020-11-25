package com.example.starter.repository;

import com.example.starter.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento,Long> {
    List<Agendamento> findByDataAgendamentoAfter(LocalDate dataAgendamento);
    List<Agendamento> findByPaciente(Long id);
    List<Agendamento> findByDataAgendamento(LocalDate dataAgendamento);
    List<Agendamento> findByVaga(Long vaga);
}
