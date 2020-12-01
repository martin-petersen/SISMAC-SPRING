package com.example.starter.service;

import com.example.starter.exceptions.ServiceException;
import com.example.starter.model.Paciente;
import com.example.starter.repository.PacienteRepository;
import com.example.starter.service.SolicitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteService extends SolicitanteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public boolean salvar (Paciente paciente) {
        pacienteRepository.save(paciente);
        return true;
    }

    @Override
    public Page<Paciente> buscarTodos(Pageable pageable) {
        return pacienteRepository.findAll(pageable);
    }

    public Page<Paciente> buscarPorNome(String nome, Pageable pageable) throws ServiceException {
        if(pacienteRepository.findByNomePaciente(nome,pageable) != null) {
            return pacienteRepository.findByNomePaciente(nome,pageable);
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Paciente","Não foram encontrados pacientes com esse nome");
        }
    }

    public void remover (Paciente paciente) {
        Paciente aSerRemovido = new Paciente();
        if(paciente == null) {
            return;
        } else if (paciente.getCpf() != null) {
            aSerRemovido.setPaciente(pacienteRepository.findByCpf(paciente.getCpf()));
        } else {
            aSerRemovido.setPaciente(pacienteRepository.findByCarteiraSUS(paciente.getCarteiraSUS()));
        }
        pacienteRepository.deleteById(aSerRemovido.getId());
    }

    public Paciente buscarPorCpf(String cpf) throws ServiceException {
        if(pacienteRepository.findByCpf(cpf) != null) {
            return pacienteRepository.findByCpf(cpf);
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Paciente","Não foi encontrado paciente com esse cpf");
        }
    }

    public Page<Paciente> buscarPorCpf(String cpf, Pageable pageable) throws ServiceException {
        if(pacienteRepository.findByCpf(cpf) != null) {
            return pacienteRepository.findByCpf(cpf, pageable);
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Paciente","Não foi encontrado paciente com esse cpf");
        }
    }

    public Page<Paciente> buscarPorSUS(String carteiraSUS, Pageable pageable) throws ServiceException {
        if(pacienteRepository.findByCarteiraSUS(carteiraSUS) != null) {
            return pacienteRepository.findByCarteiraSUS(carteiraSUS, pageable);
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Paciente","Não foi encontrado paciente com esse cartãoSUS");
        }
    }

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
}
