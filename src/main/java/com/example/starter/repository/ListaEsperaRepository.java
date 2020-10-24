package com.example.starter.repository;

import com.example.starter.model.Especialidade;
import com.example.starter.model.Exame;
import com.example.starter.model.ListaEspera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListaEsperaRepository extends JpaRepository<ListaEspera,Long> {
    List<ListaEspera> findByEspecialidade(Especialidade especialidade);
    List<ListaEspera> findByExame(Exame exame);
    List<ListaEspera> findByAtivoOrderByDataEntradaLista(boolean ativo);
}
