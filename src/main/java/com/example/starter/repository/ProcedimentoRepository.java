package com.example.starter.repository;

import com.example.starter.model.Procedimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProcedimentoRepository extends JpaRepository<Procedimento,Long> {
    @Query("from Procedimento i where upper(i.nomeProcedimento) like :nomeProcedimento")
    List<Procedimento> findByNomeProcedimento(String nomeProcedimento);
    List<Procedimento> findByIdIn(List<Long> ids);
}
