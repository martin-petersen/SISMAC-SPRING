package com.example.starter.controller;

import com.example.starter.dto.PacienteDTO;
import com.example.starter.model.Paciente;
import com.example.starter.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/listar")
    public ResponseEntity<?> listarPacientes(@RequestParam(required = false) String nome,
                                          @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = "nomePaciente")
                                                  Pageable pageable) {
        if (nome == null) {
            Page<Paciente> pacientes = pacienteService.buscarTodos(pageable);
            return ResponseEntity.ok(PacienteDTO.convert(pacientes));
        } else {
            Page<Paciente> pacientes = pacienteService.buscarPorNome(nome, pageable);
            return ResponseEntity.ok(PacienteDTO.convert(pacientes));
        }
    }
}
