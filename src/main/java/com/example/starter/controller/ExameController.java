package com.example.starter.controller;

import com.example.starter.dto.ExameDTO;
import com.example.starter.form.ExameFORM;
import com.example.starter.model.Exame;
import com.example.starter.service.ExameService;
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
@RequestMapping(path = "/exames")
public class ExameController {

    @Autowired
    private ExameService exameService;

    @GetMapping
    public ResponseEntity<Page<ExameDTO>>listar(@RequestParam(required = false) String nomeExame,
                                                @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "nomeExame") Pageable pageable) {
        if(nomeExame != null) {
            try {
                Exame exame = new Exame();
                exame.setNomeExame(nomeExame);
                Page<ExameDTO> exameDTO = convertInDetalhamentoDTO(exameService.buscarPorNome(exame),pageable);
                return ResponseEntity.ok(exameDTO);
            }catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            Page<ExameDTO> exameDTO = convertInDetalhamentoDTO(exameService.listarTodos(),pageable);
            return ResponseEntity.ok(exameDTO);
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

    private Page<ExameDTO> convertInDetalhamentoDTO (List<Exame> lista, Pageable pageable) {
        List<ExameDTO> exameDTOList = new ArrayList<>();
        for (Exame e:
                lista) {
            exameDTOList.add(new ExameDTO(e.getId(),e.getNomeExame()));
        }
        return new PageImpl<>(exameDTOList,pageable,exameDTOList.size());
    }
}
