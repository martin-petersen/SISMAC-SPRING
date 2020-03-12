package com.example.starter.service;

import com.example.starter.model.Paciente;
import com.example.starter.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Paciente buscar (Paciente paciente) {
        try{
            return pacienteRepository.findById(paciente.getId()).get();
        }catch (Exception e) {
            return null;
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
}
