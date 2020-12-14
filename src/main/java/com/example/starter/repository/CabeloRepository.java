package com.example.starter.repository;

import com.example.starter.model.Cabelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabeloRepository extends JpaRepository<Cabelo, Long> {
}
