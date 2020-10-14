package com.example.starter.controller;

import com.example.starter.dto.EspecialidadeDTO;
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
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @Cacheable(value = "listaEspecialidades")
    @GetMapping
    public ResponseEntity<Page<EspecialidadeDTO>> listarEspecialidades(@RequestParam(required = false) String nomeEspecialidade,
                                                                    @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "nomeEspecialidade") Pageable pageable) {
        if(nomeEspecialidade != null) {
            Page<Especialidade> especialidade = especialidadeService.buscarPorNome(nomeEspecialidade, pageable);
            Page<EspecialidadeDTO> especialidadeDTOS = especialidade.map(EspecialidadeDTO::new);
            return ResponseEntity.ok(especialidadeDTOS);
        } else {
            Page<Especialidade> listaEspecialidades = especialidadeService.buscarTodos(pageable);
            Page<EspecialidadeDTO> especialidadeDTOS = listaEspecialidades.map(EspecialidadeDTO::new);
            return ResponseEntity.ok(especialidadeDTOS);
        }
    }

    @GetMapping("/todasEspecialidades")
    public ResponseEntity<List<EspecialidadeDTO>> listarEspecialidadesSemPaginacao() {
        List<Especialidade> listaEspecialidades = especialidadeService.buscarTodosSemPaginacao();
        List<EspecialidadeDTO> especialidadeDTOS = new ArrayList<>();
        for (Especialidade e:
             listaEspecialidades) {
            especialidadeDTOS.add(new EspecialidadeDTO(e));
        }
        return ResponseEntity.ok(especialidadeDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadeDTO> buscarporID(@PathVariable Long id) {
        try {
            Especialidade especialidade = especialidadeService.buscarPorID(id);
            return ResponseEntity.ok(new EspecialidadeDTO(especialidade));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaEspecialidades", allEntries = true)
    public ResponseEntity<EspecialidadeDTO> cadastrarEspecialidade(@RequestBody @Valid EspecialidadeFORM especialidadeForm,
                                                                   UriComponentsBuilder uriComponentsBuilder) {
        try{
            especialidadeService.salvar(especialidadeForm.convert());
            Especialidade especialidade = especialidadeService.buscarPorNome(especialidadeForm.getNomeEspecialidade());
            URI uri = uriComponentsBuilder.path("/especialidades/{id}").buildAndExpand(especialidade.getId()).toUri();
            EspecialidadeDTO especialidadeDTO = new EspecialidadeDTO(especialidade);
            return ResponseEntity.created(uri).body(especialidadeDTO);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/atualizarEspecialidade/{id}")
    @Transactional
    @CacheEvict(value = "listaEspecialidades", allEntries = true)
    public ResponseEntity<EspecialidadeDTO> atualizarEspecialidade(@PathVariable Long id,
                                                                @RequestBody EspecialidadeFORM especialidadeForm) {
        try {
            Especialidade especialidade = especialidadeService.atualizar(id, especialidadeForm);
            return ResponseEntity.ok(new EspecialidadeDTO(especialidade));
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
