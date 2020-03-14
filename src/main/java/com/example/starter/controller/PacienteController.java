package com.example.starter.controller;

import com.example.starter.dto.PacienteDTO;
import com.example.starter.form.AtualizacaoPacienteFORM;
import com.example.starter.form.PacienteFORM;
import com.example.starter.model.Paciente;
import com.example.starter.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/listar")
    public ResponseEntity<Page<PacienteDTO>> listarPacientes(@RequestParam(required = false) String nome,
                                                             @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "nomePaciente")
                                                                     Pageable pageable) {
        if(nome == null){
            Page<Paciente> pacientes = pacienteService.buscarTodos(pageable);
            return ResponseEntity.ok(PacienteDTO.convert(pacientes));
        } else {
            String pacienteNome = "%" + nome + "%";
            Page<Paciente> pacientes = pacienteService.buscarPorNome(pacienteNome, pageable);
            return ResponseEntity.ok(PacienteDTO.convert(pacientes));
        }


    }

    @GetMapping(path = "/buscarCpf/{cpf}")
    public ResponseEntity<PacienteDTO> buscarCpf(@PathVariable String cpf) {
        try{
            Paciente paciente = pacienteService.buscarPorCpf(cpf);
            return ResponseEntity.ok(PacienteDTO.convert(paciente));
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "/buscarCadastroSUS/{carteiraSUS}")
    public ResponseEntity<PacienteDTO> buscarCadastroSUS(@PathVariable String carteiraSUS) {
        try{
            Paciente paciente = pacienteService.buscarPorSUS(carteiraSUS);
            return ResponseEntity.ok(PacienteDTO.convert(paciente));
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PacienteDTO> cadastrarPaciente(@RequestBody @Valid PacienteFORM pacienteForm, UriComponentsBuilder uriComponentsBuilder) {
        try {
            pacienteService.salvar(pacienteForm.convert());
            Paciente paciente = pacienteForm.convert();
            URI uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
            return ResponseEntity.created(uri).body(new PacienteDTO(paciente));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/atualizarCadastro")
    @Transactional
    public ResponseEntity<PacienteDTO> atualizarPaciente(@RequestParam(required = false) String cpf,
                                                         @RequestParam(required = false) String carteiraSUS,
                                                         @RequestBody AtualizacaoPacienteFORM pacienteForm) {
        try {
            Paciente objPacienteBusca = new Paciente(cpf, carteiraSUS);
            PacienteDTO pacienteDTO = pacienteService.alterar(objPacienteBusca, pacienteForm);
            return ResponseEntity.ok(pacienteDTO);
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
