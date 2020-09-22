package com.example.starter.controller;

import com.example.starter.form.EspecialidadeFORM;
import com.example.starter.model.Especialidade;
import com.example.starter.service.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping(path = "/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @Cacheable(value = "listaEspecialidades")
    @GetMapping
    public ResponseEntity<Page<Especialidade>> listarEspecialidades(@RequestParam(required = false) String nomeEspecialidade,
                                                                    @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "nomeEspecialidade") Pageable pageable) {
        if(nomeEspecialidade != null) {
            Page<Especialidade> especialidade = especialidadeService.buscarPorNome(nomeEspecialidade, pageable);
            return ResponseEntity.ok(especialidade);
        } else {
            Page<Especialidade> listaEspecialidades = especialidadeService.buscarTodos(pageable);
            return ResponseEntity.ok(listaEspecialidades);
        }
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaEspecialidades", allEntries = true)
    public ResponseEntity<Especialidade> cadastrarEspecialidade(@RequestBody @Valid EspecialidadeFORM especialidadeForm,
                                                                   UriComponentsBuilder uriComponentsBuilder) {
        try{
            especialidadeService.salvar(especialidadeForm.convert());
            Especialidade especialidade = especialidadeService.buscarPorNome(especialidadeForm.getNomeEspecialidade());
            URI uri = uriComponentsBuilder.path("/especialidades/{id}").buildAndExpand(especialidade.getId()).toUri();
            return ResponseEntity.created(uri).body(especialidade);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/atualizarEspecialidade")
    @Transactional
    @CacheEvict(value = "listaEspecialidades", allEntries = true)
    public ResponseEntity<Especialidade> atualizarEspecialidade(@RequestParam String nomeEspecialidade,
                                                                   @RequestBody EspecialidadeFORM especialidadeForm) {
        try {
            Especialidade especialidade = especialidadeService.atualizar(nomeEspecialidade.toUpperCase(), especialidadeForm);
            return ResponseEntity.ok(especialidade);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/deletarEspecialidade")
    @Transactional
    @CacheEvict(value = "listaEspecialidades", allEntries = true)
    public ResponseEntity<?> removerEspecialidade(@RequestParam String nomeEspecialidade) {
        try {
            especialidadeService.remover(nomeEspecialidade.toUpperCase());
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
