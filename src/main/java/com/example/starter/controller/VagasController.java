package com.example.starter.controller;

import com.example.starter.dto.VagaDTO;
import com.example.starter.exceptions.ServiceException;
import com.example.starter.form.NovaDataVagaFORM;
import com.example.starter.form.VagaFORM;
import com.example.starter.model.Vaga;
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

@CrossOrigin
@RestController
@RequestMapping(path = "/vagas")
public class VagasController {

    @Autowired
    private VagaService vagaService;

    @GetMapping
    public ResponseEntity<Page<VagaDTO>> listar(@RequestParam boolean barba,
                                                @RequestParam boolean cabelo,
                                                @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "data") Pageable pageable) throws ServiceException {
        if(barba) {
            Page<VagaDTO> listagemVagasExames = vagaService.listarBarba(pageable);
            return ResponseEntity.ok(listagemVagasExames);
        } else if(cabelo) {
            Page<VagaDTO> listagemVagasTotais = vagaService.listar(pageable);
            return ResponseEntity.ok(listagemVagasTotais);
        } else {
            Page<VagaDTO> listagemVagasConsultas = vagaService.listarConsulta(pageable);
            return ResponseEntity.ok(listagemVagasConsultas);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<VagaDTO> cadastrarVaga(@RequestBody @Valid VagaFORM vagaFORM,
                                                   UriComponentsBuilder uriComponentsBuilder) throws ServiceException {
        Vaga vaga = vagaService.salvar(vagaFORM);
        URI uri = uriComponentsBuilder.path("/vagas/{id}").buildAndExpand(vaga.getId()).toUri();
        if(vaga.getCabelo() != null) {
            return ResponseEntity.created(uri).body(new VagaDTO(vaga.getId(),vaga.getData(),vaga.getVagasOfertadas(),vaga.getVagasRestantes(),vaga.getCabelo()));
        } else {
            return ResponseEntity.created(uri).body(new VagaDTO(vaga.getId(),vaga.getData(),vaga.getVagasOfertadas(),vaga.getVagasRestantes(),vaga.getBarba()));
        }
    }

    @PutMapping("/editarVaga/{id}")
    @Transactional
    public ResponseEntity<VagaDTO> editarVaga(@PathVariable Long id,
                                              @RequestBody NovaDataVagaFORM novaDataVagaFORM) {
        try {
            Vaga vaga = vagaService.atualizar(id,novaDataVagaFORM);
            if(vaga.getCabelo() != null) {
                return ResponseEntity.ok(new VagaDTO(vaga.getId(),vaga.getData(),vaga.getVagasOfertadas(),vaga.getVagasRestantes(),vaga.getCabelo()));
            } else {
                return ResponseEntity.ok(new VagaDTO(vaga.getId(),vaga.getData(),vaga.getVagasOfertadas(),vaga.getVagasRestantes(),vaga.getBarba()));
            }
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}