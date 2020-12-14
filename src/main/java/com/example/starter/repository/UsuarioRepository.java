package com.example.starter.repository;

import com.example.starter.model.Cliente;
import com.example.starter.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    @Query("from Usuario i where lower(i.email) like :email")
    List<Usuario> findByEmail(String email);
    @Query("from Usuario i where upper(i.nome) like :nome")
    List<Usuario> findByNome(String nome);
    Usuario findByCliente(Cliente cliente);
}
