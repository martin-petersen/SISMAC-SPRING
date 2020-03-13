package com.example.starter.repository;

import com.example.starter.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    Page<Paciente> findByNomePaciente(String nome, Pageable pageable);
    Paciente findByCpf(String cpf);
    Paciente findByCarteiraSUS(String carteiraSUS);
}
