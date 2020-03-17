package com.example.starter.service;

import com.example.starter.model.Procedimento;
import com.example.starter.repository.ProcedimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProcedimentoService {

    @Autowired
    private ProcedimentoRepository procedimentoRepository;

    public Page<Procedimento> listarTodos(Pageable pageable) {
        return procedimentoRepository.findAll(pageable);
    }

    public Procedimento buscarPorNome(Procedimento procedimento) {
        return procedimentoRepository.findByNomeProcedimento(procedimento.getNomeProcedimento());
    }

//    public Page<Procedimento> buscarPorEspecialidade(Procedimento procedimento) {
//        return procedimentoRepository.
//    }
}
