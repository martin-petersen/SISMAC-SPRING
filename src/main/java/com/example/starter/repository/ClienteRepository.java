package com.example.starter.repository;

import com.example.starter.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    @Query("from Cliente i where upper(i.nomeCliente) like :nome")
    Page<Cliente> findByNomeCliente(String nome, Pageable pageable);
    Page<Cliente> findByCpf(String cpf, Pageable pageable);
    Cliente findByCpf(String cpf);
}
