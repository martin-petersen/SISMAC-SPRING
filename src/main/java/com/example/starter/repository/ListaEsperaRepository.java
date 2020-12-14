package com.example.starter.repository;

import com.example.starter.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListaEsperaRepository extends JpaRepository<ListaEspera,Long> {
    List<ListaEspera> findByCabeloAndAtivo(Cabelo cabelo, boolean ativo);
    List<ListaEspera> findByBarbaAndAtivo(Barba barba, boolean ativo);
    List<ListaEspera> findByAtivoOrderByDataEntradaLista(boolean ativo);
    List<ListaEspera> findByAtivo(boolean ativo);
    List<ListaEspera> findByClienteAndAtivo(Cliente cliente, boolean ativo);
}
