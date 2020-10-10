package com.example.starter.service;

import com.example.starter.model.Exame;
import com.example.starter.repository.ExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExameService {

    @Autowired
    private ExameRepository exameRepository;

    public List<Exame> buscarPorNome(Exame exame) {
        String nomeExame = "%" + exame.getNomeExame().toUpperCase() + "%";
        return exameRepository.findByNomeExame(nomeExame);
    }

    public List<Exame> listarTodos() {
        return exameRepository.findAll();
    }

    public Exame salvar(Exame exame) {
        exameRepository.save(exame);
        return exame;
    }

    public Exame atualizar(Exame exame) {
        Exame oldExame = exameRepository.findById(exame.getId()).get();
        oldExame.setNomeExame(exame.getNomeExame());
        exameRepository.save(oldExame);
        return oldExame;
    }

    public boolean remover(Exame exame) {
        try {
            exameRepository.deleteById(exame.getId());
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
