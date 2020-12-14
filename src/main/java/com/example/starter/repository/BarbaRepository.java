package com.example.starter.repository;

import com.example.starter.model.Barba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarbaRepository extends JpaRepository<Barba,Long> {
}
