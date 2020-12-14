package com.example.starter.service;

import com.example.starter.dto.AgendamentoDTO;
import com.example.starter.exceptions.ServiceException;
import com.example.starter.model.Agendamento;
import com.example.starter.repository.BarbaRepository;
import com.example.starter.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AgendamentoService extends SolicitacaoService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Number regraDeBuscaDeSolicitante(Agendamento a) {
        return a.getCliente();
    }

    public Page<AgendamentoDTO> buscarPorCliente(Long id, Pageable pageable) throws ServiceException {
        if(clienteRepository.findById(id).isPresent()) {
            List<Agendamento> agendamentos = agendamentoRepository.findByCliente(id);
            return convertInDetalhamentoDTO(agendamentos,pageable);
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Cliente","Cliente não encontrado no sistema");
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
            agendamentoDTO.setCabelo(a.isCabelo());
            agendamentoDTO.setNomeCliente(clienteRepository.findById(a.getCliente()).get().getNomeCliente());
            agendamentoDTO.setBarba(a.isBarba());
            agendamentosDTO.add(agendamentoDTO);
        }
        return new PageImpl<>(agendamentosDTO,pageable,agendamentosDTO.size());
    }
}
