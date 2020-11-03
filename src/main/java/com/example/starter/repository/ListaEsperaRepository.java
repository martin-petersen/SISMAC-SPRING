package com.example.starter.repository;

import com.example.starter.model.Especialidade;
import com.example.starter.model.Exame;
import com.example.starter.model.ListaEspera;
import com.example.starter.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListaEsperaRepository extends JpaRepository<ListaEspera,Long> {
    List<ListaEspera> findByEspecialidadeAndAtivo(Especialidade especialidade, boolean ativo);
    List<ListaEspera> findByExameAndAtivo(Exame exame, boolean ativo);
    List<ListaEspera> findByAtivoOrderByDataEntradaLista(boolean ativo);
    List<ListaEspera> findByAtivo(boolean ativo);
    List<ListaEspera> findByPacienteAndAtivo(Paciente paciente, boolean ativo);
}
