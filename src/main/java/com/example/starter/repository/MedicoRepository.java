package com.example.starter.repository;

import com.example.starter.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico,Long> {
    @Query("from Medico i where upper(i.nomeMedico) like :nomeMedico")
    List<Medico> findByNomeMedico(String nomeMedico);
    Medico findByNumeroConselho(String numeroConselho);
}
