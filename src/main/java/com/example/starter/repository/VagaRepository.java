package com.example.starter.repository;

import com.example.starter.model.Consulta;
import com.example.starter.model.Exame;
import com.example.starter.model.Vaga;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
    List<Vaga> findByExameAndDataAfter(Exame exame, LocalDateTime localDateTime);
    List<Vaga> findByConsultaAndDataAfter(Consulta consulta, LocalDateTime localDateTime);
}
