package com.example.starter.service;

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

    public Paciente buscarUm (Paciente paciente) {
        try{
            return pacienteRepository.findById(paciente.getId()).get();
        }catch (Exception e) {
            return null;
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

    public boolean remover (Paciente paciente) {
        try {
            pacienteRepository.deleteById(paciente.getId());
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public Paciente buscarPorCpf(String cpf) {
        if(pacienteRepository.findByCpf(cpf) != null) {
            return pacienteRepository.findByCpf(cpf);
        } else {
            throw new NullPointerException();
        }
    }

    public Paciente buscarPorSUS(String carteiraSUS) {
        if(pacienteRepository.findByCpf(carteiraSUS) != null) {
            return pacienteRepository.findByCarteiraSUS(carteiraSUS);
        } else {
            throw new NullPointerException();
        }
    }

    public Paciente alterar(Paciente paciente, AtualizacaoPacienteFORM atualizacaoPacienteFORM) {
        if(paciente == null) {
            return null;
        } else if (paciente.getCpf() != null) {
            Paciente pacienteAtualizado = pacienteRepository.findByCpf(paciente.getCpf());
            if(!paciente.getNomePaciente().equals(atualizacaoPacienteFORM.getNome()) && atualizacaoPacienteFORM.getNome() != null) {
                pacienteAtualizado.setNomePaciente(atualizacaoPacienteFORM.getNome());
            } else if (!paciente.getCpf().equals(atualizacaoPacienteFORM.getCpf()) && atualizacaoPacienteFORM.getCpf() != null) {
                pacienteAtualizado.setCpf(atualizacaoPacienteFORM.getCpf());
            }
        } else {

        }
    }
}
