package com.example.starter.service;
import com.example.starter.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public abstract class SolicitanteService {
    //retornar todos de maneira paginada
    public abstract Page<Paciente> buscarTodos(Pageable pageable);
}