package com.example.starter.repository;

import com.example.starter.model.Encaminhamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncaminhamentoRepository extends JpaRepository<Encaminhamento,Long> {
}
