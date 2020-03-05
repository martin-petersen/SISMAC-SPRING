package com.example.starter.repository;

import com.example.starter.model.ListaDeEspera;
import com.example.starter.model.Paciente;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ListaDeEsperaRepository extends CrudRepository<ListaDeEspera, Long> {
    List<ListaDeEspera> buscaPorNome(String nome);
    List<ListaDeEspera> buscaPorDataEntrada(LocalDateTime dataEntrada);
    List<Paciente> buscaPorEspecialidade(String especialidade);
}
