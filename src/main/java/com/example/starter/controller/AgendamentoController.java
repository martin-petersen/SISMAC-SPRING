package com.example.starter.controller;

import com.example.starter.dto.AgendamentoDTO;
import com.example.starter.dto.PacienteAgendamentoDTO;
import com.example.starter.exceptions.ServiceException;
import com.example.starter.model.Agendamento;
import com.example.starter.model.Paciente;
import com.example.starter.repository.AgendamentoRepository;
import com.example.starter.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping("/{id}")
    public ResponseEntity<Page<AgendamentoDTO>> historicoPacienteWeb(@PathVariable Long id,
                                                    @PageableDefault(size = 10, direction = Sort.Direction.ASC, sort = "dataAgendamento")
                                                            Pageable pageable) throws ServiceException {
        Page<AgendamentoDTO> agendamentos = agendamentoService.buscarPorPacienteWeb(id,pageable);
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/mobile/{id}")
    public ResponseEntity<Page<AgendamentoDTO>> historicoPacienteMobile(@PathVariable Long id,
                                                          @PageableDefault(size = 10, direction = Sort.Direction.ASC, sort = "dataAgendamento")
                                                                  Pageable pageable) throws ServiceException {
        Page<AgendamentoDTO> agendamentos = agendamentoService.buscarPorPacienteMobile(id,pageable);
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/vaga/{id}")
    public ResponseEntity<List<Number>> agendamentosPorVaga(@PathVariable Long id) throws ServiceException {
        return ResponseEntity.ok(agendamentoService.buscarSolicitantesPorListaDeVaga(id));
    }
}
