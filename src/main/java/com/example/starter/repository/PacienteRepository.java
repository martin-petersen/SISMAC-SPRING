package com.example.starter.repository;

import com.example.starter.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    @Query("from Paciente i where upper(i.nomePaciente) like :nome")
    Page<Paciente> findByNomePaciente(String nome, Pageable pageable);
    Paciente findByCpf(String cpf);
    Paciente findByCarteiraSUS(String carteiraSUS);
}
