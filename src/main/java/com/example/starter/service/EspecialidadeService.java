package com.example.starter.service;

import com.example.starter.form.EspecialidadeFORM;
import com.example.starter.model.Especialidade;
import com.example.starter.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Especialidade buscarPorNome(String nomeEspecialidade) {
        try {
            String especialidade = "%" + nomeEspecialidade.toUpperCase() + "%";
            return especialidadeRepository.findByNomeEspacialidade(especialidade);
        }catch (Exception e) {
            return null;
        }
    }

    public boolean remover (String nomeEspecialidade) {
        try {
            Especialidade especialidade = buscarPorNome(nomeEspecialidade);
            especialidadeRepository.deleteById(especialidade.getId());
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public Page<Especialidade> buscarTodos(Pageable pageable) {
        return especialidadeRepository.findAll(pageable);
    }


    public Especialidade atualizar(String nomeEspecialidade, EspecialidadeFORM especialidadeForm) {
        Especialidade especialidade = buscarPorNome(nomeEspecialidade);
        especialidade.setNomeEspacialidade(especialidadeForm.getNomeEspecialidade());
        especialidadeRepository.save(especialidade);
        return especialidade;
    }
}
