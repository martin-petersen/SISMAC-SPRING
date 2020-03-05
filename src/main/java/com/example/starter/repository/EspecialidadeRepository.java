package com.example.starter.repository;

import com.example.starter.model.Especialidade;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EspecialidadeRepository extends CrudRepository<Especialidade, Long> {
    Optional<Especialidade> buscarPelaEspecialidade(String especialidade);
}
