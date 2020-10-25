package com.example.starter.repository;

import com.example.starter.model.Agendamento;
import com.example.starter.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento,Long> {
    List<Agendamento> findByDataAgendamentoAfter(LocalDate dataAgendamento);
}
