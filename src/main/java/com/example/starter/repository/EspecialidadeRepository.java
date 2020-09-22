package com.example.starter.repository;

import com.example.starter.model.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade,Long> {

    @Query("from Especialidade i where upper(i.nomeEspecialidade) like :nome")
    Especialidade findByNomeEspacialidade(String nome);
    @Query("from Especialidade i where upper(i.nomeEspecialidade) like :nome")
    Page<Especialidade> findByNomeEspacialidade(String nome, Pageable pageable);
}
