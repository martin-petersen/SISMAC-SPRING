package com.example.starter.repository;

import com.example.starter.model.Procedimento;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProcedimentoRepository extends JpaRepository<Procedimento,Long> {
    @Query("from Procedimento i where upper(i.nomeProcedimento) like :nomeProcedimento")
    Procedimento findByNomeProcedimento(String nomeProcedimento);
}
