package com.example.starter.repository;

import com.example.starter.model.Procedimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcedimentoRepository extends JpaRepository<Procedimento,Long> {
}
