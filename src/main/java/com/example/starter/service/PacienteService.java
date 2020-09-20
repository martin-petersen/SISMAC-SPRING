package com.example.starter.service;

import com.example.starter.dto.PacienteDTO;
import com.example.starter.form.AtualizacaoPacienteFORM;
import com.example.starter.model.Paciente;
import com.example.starter.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public boolean salvar (Paciente paciente) {
        try {
            pacienteRepository.save(paciente);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public Page<Paciente> buscarTodos(Pageable pageable) {
        return pacienteRepository.findAll(pageable);
    }

    public Page<Paciente> buscarPorNome(String nome, Pageable pageable) {
        if(pacienteRepository.findByNomePaciente(nome,pageable) != null) {
            return pacienteRepository.findByNomePaciente(nome,pageable);
        } else {
            throw new NullPointerException();
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

    public Paciente buscarPorCpf(String cpf) {
        if(pacienteRepository.findByCpf(cpf) != null) {
            return pacienteRepository.findByCpf(cpf);
        } else {
            throw new NullPointerException();
        }
    }

    public Page<Paciente> buscarPorCpf(String cpf, Pageable pageable) {
        if(pacienteRepository.findByCpf(cpf) != null) {
            return pacienteRepository.findByCpf(cpf, pageable);
        } else {
            throw new NullPointerException();
        }
    }

    public Page<Paciente> buscarPorSUS(String carteiraSUS, Pageable pageable) {
        if(pacienteRepository.findByCarteiraSUS(carteiraSUS) != null) {
            return pacienteRepository.findByCarteiraSUS(carteiraSUS, pageable);
        } else {
            throw new NullPointerException();
        }
    }

    public PacienteDTO alterar(Paciente paciente) {
        if(paciente == null) {
            return null;
        } else if (pacienteRepository.findByCpf(paciente.getCpf()) != null) {
            Paciente pacienteAtualizado = pacienteRepository.findByCpf(paciente.getCpf());
            pacienteAtualizado.setPacienteUpdate(paciente);
            return new PacienteDTO(pacienteAtualizado);
        } else {
            Paciente pacienteAtualizado = pacienteRepository.findByCarteiraSUS(paciente.getCarteiraSUS());
            pacienteAtualizado.setPacienteUpdate(paciente);
            return new PacienteDTO(pacienteAtualizado);
        }
    }
}
