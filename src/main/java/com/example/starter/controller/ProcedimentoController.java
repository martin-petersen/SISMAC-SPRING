package com.example.starter.controller;

import com.example.starter.dto.AtualizacaoProcedimentoDTO;
import com.example.starter.dto.DetalhamentoProcedimentoDTO;
import com.example.starter.dto.ProcedimentoDTO;
import com.example.starter.form.AtualizacaoProcedimentoForm;
import com.example.starter.form.ProcedimentoForm;
import com.example.starter.model.Especialidade;
import com.example.starter.model.Procedimento;
import com.example.starter.repository.EspecialidadeRepository;
import com.example.starter.service.ProcedimentoService;
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
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/procedimentos")
public class ProcedimentoController {

    @Autowired
    private ProcedimentoService procedimentoService;
    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @GetMapping(path = "/listar")
    public ResponseEntity<Page<ProcedimentoDTO>> listarTodosProcedimentos(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "nomeProcedimento") Pageable pageable) {
        Page<ProcedimentoDTO> procedimentoPage = convertInDTO(procedimentoService.listarTodos(),pageable);
        return ResponseEntity.ok(procedimentoPage);
    }

    @GetMapping(path = "/buscarPorEspecialidade")
    public ResponseEntity<Page<ProcedimentoDTO>> procedimentosPorEspcialidade(@RequestParam String nomeEspecialidade,
                                                                              @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "nomeProcedimento") Pageable pageable) {
        try {
            Page<ProcedimentoDTO> procedimentoPage = convertInDTO(procedimentoService.listarPorEspecialidade(nomeEspecialidade),pageable);
            return ResponseEntity.ok(procedimentoPage);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "/buscaPorNome")
    public ResponseEntity<Page<DetalhamentoProcedimentoDTO>> buscarPorNome(@RequestParam String nomeProcedimento,
                                                                     @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "nomeProcedimento") Pageable pageable) {
        try {
            List<Procedimento> procedimentos = procedimentoService.buscarPorNome(new Procedimento(nomeProcedimento));
            Page<DetalhamentoProcedimentoDTO> detalhamentoProcedimentoDTOPage = convertInDetalamentoDTO(procedimentos, pageable);
            return ResponseEntity.ok(detalhamentoProcedimentoDTOPage);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProcedimentoDTO> cadastrarProcedimento(@RequestBody @Valid ProcedimentoForm procedimentoForm,
                                                                 UriComponentsBuilder uriComponentsBuilder) {
        try{
            Procedimento procedimento = new Procedimento();
            procedimento.setNomeProcedimento(procedimentoForm.getNomeProcedimento().toUpperCase());
            for (Long i:
                    procedimentoForm.getEspecialidadesId()) {
                Optional<Especialidade> especialidade = especialidadeRepository.findById(i);
                procedimento.addEspecialidade(especialidade.get());
            }
            procedimentoService.salvar(procedimento);
            URI uri = uriComponentsBuilder.path("/procedimentos/{id}").buildAndExpand(procedimento.getId()).toUri();
            ProcedimentoDTO procedimentoDTO = new ProcedimentoDTO(procedimento.getId(), procedimento.getNomeProcedimento());
            return ResponseEntity.created(uri).body(procedimentoDTO);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editarProcedimento/{id}")
    @Transactional
    public ResponseEntity<AtualizacaoProcedimentoDTO> editarProcedimento(@PathVariable Long id,
                                                                         @RequestBody AtualizacaoProcedimentoForm atualizacaoProcedimentoForm) {
        try {
            Procedimento procedimento = procedimentoService.atualizar(new Procedimento(id), atualizacaoProcedimentoForm);
            List<String> listaEspecialidades = new ArrayList<>();
            for (Especialidade e:
                 procedimento.getEspecialidade()) {
                listaEspecialidades.add(e.getNomeEspacialidade());
            }
            return ResponseEntity.ok(new AtualizacaoProcedimentoDTO(procedimento.getNomeProcedimento(), listaEspecialidades));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/deletarProcedimento")
    @Transactional
    public ResponseEntity<?> removerProcedimento(@RequestParam Long id) {
        try {
            procedimentoService.remover(new Procedimento(id));
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private Page<ProcedimentoDTO> convertInDTO (List<Procedimento> lista, Pageable pageable) {
        List<ProcedimentoDTO> procedimentoDTOList = new ArrayList<>();
        for (Procedimento p:
                lista) {
            procedimentoDTOList.add(new ProcedimentoDTO(p.getId(), p.getNomeProcedimento()));
        }
        return new PageImpl<>(procedimentoDTOList,pageable,procedimentoDTOList.size());
    }

    private Page<DetalhamentoProcedimentoDTO> convertInDetalamentoDTO (List<Procedimento> lista, Pageable pageable) {
        List<DetalhamentoProcedimentoDTO> procedimentoDTOList = new ArrayList<>();
        for (Procedimento p:
                lista) {
            procedimentoDTOList.add(new DetalhamentoProcedimentoDTO(p.getId(), p.getNomeProcedimento(), p.getEspecialidade()));
        }
        return new PageImpl<>(procedimentoDTOList,pageable,procedimentoDTOList.size());
    }
}
