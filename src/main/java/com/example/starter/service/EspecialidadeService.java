package com.example.starter.service;

import com.example.starter.exceptions.ServiceException;
import com.example.starter.form.EspecialidadeFORM;
import com.example.starter.model.Consulta;
import com.example.starter.model.Especialidade;
import com.example.starter.repository.ConsultaRepository;
import com.example.starter.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadeService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public boolean salvar (Especialidade especialidade) throws ServiceException {
        try {
            Consulta consulta = consultaRepository.findById(Long.parseLong("1")).get();
            especialidade.setCosulta(consulta);
            especialidadeRepository.save(especialidade);
            return true;
        }catch (Exception e) {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Especialidade","Houve um erro ao cadastrar essa especiliade, entre em contato com a administração");
        }
    }

    public Page<Especialidade> buscarPorNome(String nomeEspecialidade, Pageable pageable) throws ServiceException {
        try {
            String especialidade = "%" + nomeEspecialidade.toUpperCase() + "%";
            return especialidadeRepository.findByNomeEspacialidade(especialidade, pageable);
        }catch (Exception e) {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Especialidade","Não foi encontrada especialidade com esse nome no sistema");
        }
    }

    public Especialidade buscarPorNome(String nomeEspecialidade) throws ServiceException {
        try {
            String especialidade = "%" + nomeEspecialidade.toUpperCase() + "%";
            return especialidadeRepository.findByNomeEspacialidade(especialidade);
        }catch (Exception e) {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Especialidade","Não foi encontrada especialidade com esse nome no sistema");
        }
    }

    public Especialidade buscarPorID(Long id) throws ServiceException {
        if(especialidadeRepository.findById(id).isPresent())
            return especialidadeRepository.findById(id).get();
        else
            throw new ServiceException(HttpStatus.NOT_FOUND,"Especialidade","Não foi encontrada especialidade no sistema");
    }

    public boolean remover (String nomeEspecialidade) throws ServiceException {
        try {
            Especialidade especialidade = buscarPorNome(nomeEspecialidade);
            especialidadeRepository.deleteById(especialidade.getId());
            return true;
        }catch (Exception e) {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Especialidade","Houve um erro ao remover essa especiliade, entre em contato com a administração");
        }
    }

    public Page<Especialidade> buscarTodos(Pageable pageable) {
        return especialidadeRepository.findAll(pageable);
    }

    public List<Especialidade> buscarTodosSemPaginacao() {
        return especialidadeRepository.findAll();
    }

    public Especialidade atualizar(Long id, EspecialidadeFORM especialidadeForm) {
        Especialidade especialidade = especialidadeRepository.findById(id).get();
        especialidade.setNomeEspecialidade(especialidadeForm.getNomeEspecialidade());
        especialidadeRepository.save(especialidade);
        return especialidade;
    }
}
