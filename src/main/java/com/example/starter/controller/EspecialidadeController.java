package com.example.starter.controller;

import com.example.starter.dto.EspecialidadeDTO;
import com.example.starter.form.EspecialidadeFORM;
import com.example.starter.model.Especialidade;
import com.example.starter.service.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(path = "/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @GetMapping("/listar")
    public ResponseEntity<Page<EspecialidadeDTO>> listarEspecialidades(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "nomeEspecialidade")
                                                                 Pageable pageable) {
        Page<Especialidade> listaEspecialidades = especialidadeService.buscarTodos(pageable);
        return ResponseEntity.ok(EspecialidadeDTO.convert(listaEspecialidades));
    }

    @GetMapping("/buscarNome")
    public ResponseEntity<EspecialidadeDTO> listarEspecialidades(@RequestParam String nomeEspecialidade) {
        Especialidade especialidade = especialidadeService.buscarPorNome(nomeEspecialidade);
        return ResponseEntity.ok(EspecialidadeDTO.convert(especialidade));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EspecialidadeDTO> cadastrarEspecialidade(@RequestBody @Valid EspecialidadeFORM especialidadeForm,
                                                                   UriComponentsBuilder uriComponentsBuilder) {
        try{
            especialidadeService.salvar(especialidadeForm.convert());
            Especialidade especialidade = especialidadeService.buscarPorNome(especialidadeForm.getNomeEspecialidade());
            URI uri = uriComponentsBuilder.path("/especialidades/{id}").buildAndExpand(especialidade.getId()).toUri();
            return ResponseEntity.created(uri).body(EspecialidadeDTO.convert(especialidade));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/atualizarEspecialidade")
    @Transactional
    public ResponseEntity<EspecialidadeDTO> atualizarEspecialidade(@RequestParam String nomeEspecialidade,
                                                                   @RequestBody EspecialidadeFORM especialidadeForm) {
        try {
            Especialidade especialidade = especialidadeService.atualizar(nomeEspecialidade.toUpperCase(), especialidadeForm);
            return ResponseEntity.ok(EspecialidadeDTO.convert(especialidade));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/deletarEspecialidade")
    @Transactional
    public ResponseEntity<?> removerEspecialidade(@RequestParam String nomeEspecialidade) {
        try {
            especialidadeService.remover(nomeEspecialidade.toUpperCase());
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
