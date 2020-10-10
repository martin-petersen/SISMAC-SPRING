package com.example.starter.controller;

import com.example.starter.dto.ExameDTO;
import com.example.starter.dto.VagaDTO;
import com.example.starter.form.ExameFORM;
import com.example.starter.model.Exame;
import com.example.starter.service.VagaService;
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
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/vagas")
public class VagasController {

    @Autowired
    private VagaService vagaService;

    @GetMapping
    public ResponseEntity<Page<VagaDTO>> listar(@RequestParam(required = false) String nomeExame,
                                                @RequestParam(required = false) String consulta,
                                                @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "especialidade") Pageable pageable) {
        try {
            if(nomeExame != null) {
                Page<VagaDTO> listagemVagas = vagaService.listarExames(nomeExame.toUpperCase(),pageable);
                return ResponseEntity.ok(listagemVagas);
            } else if(consulta != null) {
                Page<VagaDTO> listagemVagas = vagaService.listarConsulta(consulta.toUpperCase());
                return ResponseEntity.ok(listagemVagas);
            } else {
                Page<VagaDTO> listagemVagas = vagaService.listar();
                return ResponseEntity.ok(listagemVagas);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ExameDTO> cadastrarExame(@RequestBody @Valid ExameFORM exameFORM,
                                                   UriComponentsBuilder uriComponentsBuilder) {
        try{
            Exame exame = new Exame();
            exame.setNomeExame(exameFORM.getNomeExame().toUpperCase());
            exameService.salvar(exame);
            URI uri = uriComponentsBuilder.path("/exames/{id}").buildAndExpand(exame.getId()).toUri();
            ExameDTO exameDTO = new ExameDTO(exame.getId(),exame.getNomeExame());
            return ResponseEntity.created(uri).body(exameDTO);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editarExame/{id}")
    @Transactional
    public ResponseEntity<ExameDTO> editarProcedimento(@PathVariable Long id,
                                                       @RequestBody ExameFORM exameFORM) {
        try {
            Exame exame = exameService.atualizar(new Exame(id,exameFORM.getNomeExame().toUpperCase()));
            return ResponseEntity.ok(new ExameDTO(exame.getId(),exame.getNomeExame()));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/deletarProcedimento/{id}")
    @Transactional
    public ResponseEntity<?> removerProcedimento(@RequestParam Long id) {
        if(exameService.remover(new Exame(id))) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
