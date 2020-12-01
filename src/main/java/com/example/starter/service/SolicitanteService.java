package com.example.starter.service;

import com.example.starter.model.Paciente;
import com.example.starter.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import com.example.starter.exceptions.ServiceException;


public abstract class SolicitanteService {
    
    @Autowired
    public PacienteRepository pacienteRepository;
    
    @Transactional
    public Paciente alterar(Long id, Paciente attPaciente) throws ServiceException {
        if (pacienteRepository.findById(id).isPresent()) {
            Paciente paciente = pacienteRepository.findById(id).get();
            paciente.setPacienteUpdate(attPaciente);
            pacienteRepository.save(paciente);
            return paciente;
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND, "Paciente", "Não foi encontrado esse paciente no sistema");
        }
    }
    
    public boolean salvar(Paciente paciente) {
        pacienteRepository.save(paciente);
        return true;
    }

    public Page<Paciente> buscarTodos(Pageable pageable) {
        return pacienteRepository.findAll(pageable);
    }
}