package com.example.starter.repository;

import com.example.starter.model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Optional<Usuario> buscaPorEmail(String email);
}
