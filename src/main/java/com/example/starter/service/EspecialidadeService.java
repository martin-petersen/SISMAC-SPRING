package com.example.starter.service;

import com.example.starter.form.EspecialidadeFORM;
import com.example.starter.model.Consulta;
import com.example.starter.model.Especialidade;
import com.example.starter.repository.ConsultaRepository;
import com.example.starter.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadeService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public boolean salvar (Especialidade especialidade) {
        try {
            Consulta consulta = consultaRepository.findById(Long.parseLong("1")).get();
            especialidade.setCosulta(consulta);
            especialidadeRepository.save(especialidade);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public Page<Especialidade> buscarPorNome(String nomeEspecialidade, Pageable pageable) {
        try {
            String especialidade = "%" + nomeEspecialidade.toUpperCase() + "%";
            return especialidadeRepository.findByNomeEspacialidade(especialidade, pageable);
        }catch (Exception e) {
            return null;
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
        especialidade.setNomeEspecialidade(especialidadeForm.getNomeEspecialidade());
        especialidadeRepository.save(especialidade);
        return especialidade;
    }
}
