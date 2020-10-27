package com.example.starter.controller;

import com.example.starter.exceptions.ServiceException;
import com.example.starter.form.AtualizacaoPacienteFORM;
import com.example.starter.form.PacienteFORM;
import com.example.starter.model.Paciente;
import com.example.starter.service.PacienteService;
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
@RequestMapping(path = "/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<Page<Paciente>> listarPacientes(@RequestParam(required = false) String nome,
                                                          @RequestParam (required = false) String cpf,
                                                          @RequestParam (required = false) String carteiraSUS,
                                                          @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "nomePaciente")
                                                                     Pageable pageable) throws ServiceException {
        if(cpf!=null) {
            Page<Paciente> paciente = pacienteService.buscarPorCpf(cpf, pageable);
            return ResponseEntity.ok(paciente);
        } else if(carteiraSUS!=null) {
            Page<Paciente> paciente = pacienteService.buscarPorSUS(carteiraSUS, pageable);
            return ResponseEntity.ok(paciente);
        } else if(nome!=null) {
            String pacienteNome = "%" + nome.toUpperCase() + "%";
            Page<Paciente> pacientes = pacienteService.buscarPorNome(pacienteNome, pageable);
            return ResponseEntity.ok(pacientes);
        } else {
            Page<Paciente> pacientes = pacienteService.buscarTodos(pageable);
            return ResponseEntity.ok(pacientes);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody @Valid PacienteFORM pacienteForm, UriComponentsBuilder uriComponentsBuilder) throws ServiceException {
        pacienteService.salvar(pacienteForm.convert());
        Paciente paciente = pacienteService.buscarPorCpf(pacienteForm.getCpf());
        URI uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(paciente);
    }

    @PutMapping("/atualizarCadastro")
    @Transactional
    public ResponseEntity<Paciente> atualizarPaciente(@RequestBody AtualizacaoPacienteFORM pacienteForm) {
        try {
            Paciente objPacienteBusca = new Paciente(pacienteForm);
            pacienteService.alterar(objPacienteBusca);
            return ResponseEntity.ok(objPacienteBusca);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletarCadastro")
    @Transactional
    public ResponseEntity<?> deletarCadastro(@RequestParam(required = false) String cpf,
                                             @RequestParam(required = false) String carteiraSUS) {
        try {
            Paciente objPacienteBusca = new Paciente(cpf, carteiraSUS);
            pacienteService.remover(objPacienteBusca);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
