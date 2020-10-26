package com.example.starter.service;

import com.example.starter.exceptions.ServiceException;
import com.example.starter.form.ExameFORM;
import com.example.starter.model.Exame;
import com.example.starter.repository.ExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public List<Exame> listarAutorizacao(boolean autorizacao) {
        return exameRepository.findByAutorizacao(autorizacao);
    }

    public Exame salvar(ExameFORM exameFORM) {
        Exame exame = new Exame(exameFORM);
        exameRepository.save(exame);
        return exame;
    }
    public Exame buscarUm(Long id) throws ServiceException {
        if(exameRepository.findById(id).isPresent())
            return exameRepository.findById(id).get();
        else
            throw new ServiceException(HttpStatus.NOT_FOUND,"Exame","Não foi encontrado esse exame no sistema");
    }

    public Exame atualizar(Long id, ExameFORM exameFORM) {
        Exame oldExame = exameRepository.findById(id).get();
        oldExame.setNomeExame(exameFORM.getNomeExame().toUpperCase());
        oldExame.setAutorizacao(exameFORM.isAutorizacao());
        exameRepository.save(oldExame);
        return oldExame;
    }

    public boolean remover(Exame exame) throws ServiceException {
        try {
            exameRepository.deleteById(exame.getId());
            return true;
        }catch (Exception e) {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Exame","Houve um erro ao remover esse exame, entre em contato com a administração");
        }
    }
}
