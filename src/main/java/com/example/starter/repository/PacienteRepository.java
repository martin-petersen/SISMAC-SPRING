package com.example.starter.repository;

import com.example.starter.model.Paciente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends CrudRepository<Paciente, Long> {
    List<Paciente> buscarPorNome(String nome);
    Optional<Paciente> buscarCadastroSUS(String carteiraSUS);
    Optional<Paciente> buscaPorEmail(String email);
    Optional<Paciente> buscarCpf(String cpf);
}
