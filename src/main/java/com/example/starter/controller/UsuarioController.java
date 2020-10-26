package com.example.starter.controller;

import com.example.starter.dto.UsuarioDTO;
import com.example.starter.exceptions.ServiceException;
import com.example.starter.form.UpdateUsuarioFORM;
import com.example.starter.form.UsuarioFORM;
import com.example.starter.model.Usuario;
import com.example.starter.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> listaDeUsers(@RequestParam(required = false) String nome,
                                                        @RequestParam(required = false) String email,
                                                        @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "nome") Pageable pageable) throws ServiceException {
        if(nome != null) {
            String name = "%" + nome.toUpperCase() + "%";
            List<Usuario> usuarios = usuarioService.buscarPorNome(name);
            Page<UsuarioDTO> pageUsers = converterListToPageUsuarioDTO(usuarios,pageable);
            return ResponseEntity.ok(pageUsers);
        } else if(email != null) {
            String username = "%" + email.toLowerCase() + "%";
            List<Usuario> usuarios = usuarioService.buscarPorEmail(username);
            Page<UsuarioDTO> pageUsers = converterListToPageUsuarioDTO(usuarios,pageable);
            return ResponseEntity.ok(pageUsers);
        } else {
            List<Usuario> usuarios = usuarioService.buscarTodos();
            Page<UsuarioDTO> pageUsers = converterListToPageUsuarioDTO(usuarios,pageable);
            return ResponseEntity.ok(pageUsers);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDTO> cadastrarUser(@RequestBody @Valid UsuarioFORM usuarioFORM,
                                                    UriComponentsBuilder uriComponentsBuilder) {
        try {
            Usuario usuario = usuarioService.salvar(usuarioFORM);
            URI uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(usuario.getId()).toUri();
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getId(),usuario.getEmail(),usuario.getNome(),usuario.isValidate());
            return ResponseEntity.created(uri).body(usuarioDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/vinculo/{id}")
    @Transactional
    public ResponseEntity<UsuarioDTO> vincularPaciente(@PathVariable Long id,
                                                       @RequestParam(required = true) String cpf) {
        try {
            Usuario usuario = usuarioService.criarVinculo(id,cpf);
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
            return ResponseEntity.ok(usuarioDTO);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<UsuarioDTO> atualizarUser(@PathVariable Long id,
                                                     @RequestBody @Valid UpdateUsuarioFORM updateUsuarioFORM) {
        try {
            Usuario user = usuarioService.alterarUser(id,updateUsuarioFORM);
            UsuarioDTO usuarioDTO = new UsuarioDTO(user);
            return ResponseEntity.ok(usuarioDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<?> removerUser(@PathVariable Long id) throws ServiceException {
        boolean done = usuarioService.remover(id);
        return ResponseEntity.ok().build();
    }

    private Page<UsuarioDTO> converterListToPageUsuarioDTO(List<Usuario> usuarios, Pageable pageable) {
        List<UsuarioDTO> listaUsuarios = new ArrayList<>();
        for (Usuario m:
                usuarios) {
            listaUsuarios.add(new UsuarioDTO(m.getId(),m.getEmail(),m.getNome(),m.getPaciente().getId()));
        }
        return new PageImpl<>(listaUsuarios,pageable,listaUsuarios.size());
    }
}
