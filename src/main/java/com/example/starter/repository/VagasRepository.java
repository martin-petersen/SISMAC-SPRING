package com.example.starter.repository;

import com.example.starter.model.Especialidade;
import com.example.starter.model.Procedimento;
import com.example.starter.model.Vagas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VagasRepository extends JpaRepository<Vagas, Long> {
    List<Vagas> findByProcedimentoAndEspecialidade(Procedimento procedimento, Especialidade especialidade);
}
