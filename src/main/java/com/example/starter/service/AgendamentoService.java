package com.example.starter.service;

import com.example.starter.service.SolicitacaoService;
import com.example.starter.dto.AgendamentoDTO;
import com.example.starter.dto.PacienteAgendamentoDTO;
import com.example.starter.exceptions.ServiceException;
import com.example.starter.model.Agendamento;
import com.example.starter.model.Paciente;
import com.example.starter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AgendamentoService extends SolicitacaoService {


    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<AgendamentoDTO> buscarPorPacienteWeb(Long id, Pageable pageable) throws ServiceException {
        if(pacienteRepository.findById(id).isPresent()) {
            List<Agendamento> agendamentos = agendamentoRepository.findByPaciente(id);
            return convertInDetalhamentoDTO(agendamentos,pageable);
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Paciente","Paciente não encontrado no sistema");
        }
    }

    public Page<AgendamentoDTO> buscarPorPacienteMobile(Long id, Pageable pageable) throws ServiceException {
        Long paciente_id = null;
        if(usuarioRepository.findById(id).isPresent()) {
            paciente_id = usuarioRepository.findById(id).get().getPaciente().getId();
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Paciente","Paciente não encontrado no sistema");
        }
        if(pacienteRepository.findById(paciente_id).isPresent()) {
            List<Agendamento> agendamentos = agendamentoRepository.findByPaciente(id);
            return convertInDetalhamentoDTO(agendamentos,pageable);
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Paciente","Paciente não encontrado no sistema");
        }
    }

    private Page<AgendamentoDTO> convertInDetalhamentoDTO (List<Agendamento> agendamentos, Pageable pageable) {
        List<AgendamentoDTO> agendamentosDTO = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Agendamento a:
                agendamentos) {
            AgendamentoDTO agendamentoDTO = new AgendamentoDTO();
            agendamentoDTO.setId(a.getId());
            agendamentoDTO.setDataAgendamento(a.getDataAgendamento().format(formatter));
            agendamentoDTO.setConsulta(a.isConsulta());
            agendamentoDTO.setNomePaciente(pacienteRepository.findById(a.getPaciente_id()).get().getNomePaciente());
            if(a.getEspecialidade_id() != null) {
                agendamentoDTO.setNomeEspecialidade(especialidadeRepository.findById(a.getEspecialidade_id()).get().getNomeEspecialidade());
            } else {
                agendamentoDTO.setNomeExame(exameRepository.findById(a.getExame_id()).get().getNomeExame());
            }
            agendamentosDTO.add(agendamentoDTO);
        }
        return new PageImpl<>(agendamentosDTO,pageable,agendamentosDTO.size());
    }
}
