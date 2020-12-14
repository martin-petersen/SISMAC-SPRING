package com.example.starter.repository;

import com.example.starter.model.Cabelo;
import com.example.starter.model.Barba;
import com.example.starter.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
    List<Vaga> findByCabeloAndDataAfter(Cabelo consulta, LocalDate localDate);
    List<Vaga> findByBarbaAndDataAfter(Barba barba, LocalDate localDate);
    List<Vaga> findByDataAfter(LocalDate localDateTime);
}
