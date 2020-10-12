package com.example.starter.repository;

import com.example.starter.model.Exame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExameRepository extends JpaRepository<Exame,Long> {
    @Query("from Exame i where upper(i.nomeExame) like :nomeExame")
    List<Exame> findByNomeExame(String nomeExame);
    List<Exame> findByAutorizacao(boolean autorizacao);
}
