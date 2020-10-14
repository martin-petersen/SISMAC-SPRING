package com.example.starter.repository;

import com.example.starter.model.Consulta;
import com.example.starter.model.Especialidade;
import com.example.starter.model.Exame;
import com.example.starter.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
    List<Vaga> findByExameAndDataAfter(Exame exame, LocalDate localDate);
    List<Vaga> findByConsultaAndDataAfter(Consulta consulta, LocalDate localDate);
    List<Vaga> findByDataAfter(LocalDate localDateTime);
    List<Vaga> findByEspecialidadeAndDataAfter(Especialidade especialidade, LocalDate localDate);
}
