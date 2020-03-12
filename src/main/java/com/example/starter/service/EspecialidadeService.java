package com.example.starter.service;

import com.example.starter.model.Especialidade;
import com.example.starter.model.Paciente;
import com.example.starter.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public boolean salvar (Especialidade especialidade) {
        try {
            especialidadeRepository.save(especialidade);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public Especialidade buscar (Especialidade especialidade) {
        try{
            return especialidadeRepository.findById(especialidade.getId()).get();
        }catch (Exception e) {
            return null;
        }
    }

    public boolean remover (Especialidade especialidade) {
        try {
            especialidadeRepository.deleteById(especialidade.getId());
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
