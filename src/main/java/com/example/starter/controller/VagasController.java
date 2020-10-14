package com.example.starter.controller;

import com.example.starter.dto.VagaDTO;
import com.example.starter.form.NovaDataVagaFORM;
import com.example.starter.form.VagaFORM;
import com.example.starter.model.Vaga;
import com.example.starter.service.VagaService;
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

@CrossOrigin
@RestController
@RequestMapping(path = "/vagas")
public class VagasController {

    @Autowired
    private VagaService vagaService;

    @GetMapping
    public ResponseEntity<Page<VagaDTO>> listar(@RequestParam(required = false) String nomeExame,
                                                @RequestParam(required = true) boolean consulta,
                                                @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "especialidade") Pageable pageable) {
        try {
            if(nomeExame != null && !consulta) {
                Page<VagaDTO> listagemVagasExames = vagaService.listarExames(nomeExame.toUpperCase(),pageable);
                return ResponseEntity.ok(listagemVagasExames);
            } else if(nomeExame == null && !consulta) {
                Page<VagaDTO> listagemVagasTotais = vagaService.listar(pageable);
                return ResponseEntity.ok(listagemVagasTotais);
            } else {
                Page<VagaDTO> listagemVagasConsultas = vagaService.listarConsulta(pageable);
                return ResponseEntity.ok(listagemVagasConsultas);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<VagaDTO> cadastrarVaga(@RequestBody @Valid VagaFORM vagaFORM,
                                                   UriComponentsBuilder uriComponentsBuilder) {
        try{
            Vaga vaga = vagaService.salvar(vagaFORM);
            URI uri = uriComponentsBuilder.path("/vagas/{id}").buildAndExpand(vaga.getId()).toUri();
            if(vaga.getConsulta() != null) {
                return ResponseEntity.created(uri).body(new VagaDTO(vaga.getData(),vaga.getVagasOfertadas(),vaga.getVagasRestantes(),vaga.getEspecialidade(),vaga.getConsulta()));
            } else {
                return ResponseEntity.created(uri).body(new VagaDTO(vaga.getData(),vaga.getVagasOfertadas(),vaga.getVagasRestantes(),vaga.getExame()));
            }
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editarVaga/{id}")
    @Transactional
    public ResponseEntity<VagaDTO> editarVaga(@PathVariable Long id,
                                                       @RequestBody NovaDataVagaFORM novaDataVagaFORM) {
        try {
            Vaga vaga = vagaService.atualizar(id,novaDataVagaFORM);
            if(vaga.getConsulta() != null) {
                return ResponseEntity.ok(new VagaDTO(vaga.getData(),vaga.getVagasOfertadas(),vaga.getVagasRestantes(),vaga.getEspecialidade(),vaga.getConsulta()));
            } else {
                return ResponseEntity.ok(new VagaDTO(vaga.getData(),vaga.getVagasOfertadas(),vaga.getVagasRestantes(),vaga.getExame()));
            }
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/porespecialidade/{id}")
    public ResponseEntity<Page<VagaDTO>> listarPorEspecialidade(@PathVariable Long id, @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "especialidade") Pageable pageable) {
        try {
                
            EspecialidadeService servico;
            Page<VagaDTO> listagemVagasEspecialidade = vagaService.listarEspecialidade(servico.buscarPorID(id),pageable);
                return ResponseEntity.ok(listagemVagasEspecialidade);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}