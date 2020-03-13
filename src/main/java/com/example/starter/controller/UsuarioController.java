package com.example.starter.controller;

import com.example.starter.model.Usuario;
import com.example.starter.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
    private final UsuarioRepository usuarioDAO;

    public UsuarioController(UsuarioRepository usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return new ResponseEntity<> (usuarioDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/email/{Email}")
    public ResponseEntity<?> buscarPorEmail(@PathVariable String Email) {
        Optional<Usuario> usuario = usuarioDAO.buscaPorEmail(Email);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
