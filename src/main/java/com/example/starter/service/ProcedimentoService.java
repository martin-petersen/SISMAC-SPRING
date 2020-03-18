package com.example.starter.service;

import com.example.starter.form.AtualizacaoProcedimentoForm;
import com.example.starter.model.Especialidade;
import com.example.starter.model.Procedimento;
import com.example.starter.repository.EspecialidadeRepository;
import com.example.starter.repository.ProcedimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcedimentoService {

    @Autowired
    private ProcedimentoRepository procedimentoRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Autowired
    private EspecialidadeService especialidadeService;

    public List<Procedimento> listarTodos() {
        return procedimentoRepository.findAll();
    }

    public List<Procedimento> buscarPorNome(Procedimento procedimento) {
        String nomeProcedimento = "%" + procedimento.getNomeProcedimento().toUpperCase() + "%";
        return procedimentoRepository.findByNomeProcedimento(nomeProcedimento);
    }

    public List<Procedimento> listarPorEspecialidade(String nomeEspecialidade){
        String especialidadeString = "%" + nomeEspecialidade.toUpperCase() + "%";
        Especialidade especialidade = especialidadeRepository.findByNomeEspacialidade(especialidadeString);
        List<Long> listId = new ArrayList<>();
        for (Procedimento p:
             especialidade.getProcedimentos()) {
            listId.add(p.getId());
        }
        return procedimentoRepository.findByIdIn(listId);
    }

    public Procedimento salvar(Procedimento procedimento) {
        try {
            procedimentoRepository.save(procedimento);
            return procedimento;
        }catch (Exception e) {
            return null;
        }
    }

    public Procedimento atualizar (Procedimento procedimento, AtualizacaoProcedimentoForm atualizacaoProcedimentoForm) {
        Procedimento updateProcedimento = procedimentoRepository.findById(procedimento.getId()).get();
        List<Especialidade> especialidades = new ArrayList<>();
        for (Long eID:
                atualizacaoProcedimentoForm.getEspecialidades()) {
            especialidades.add(especialidadeRepository.findById(eID).get());
        }
        updateProcedimento.setEspecialidade(especialidades);
        updateProcedimento.setNomeProcedimento(atualizacaoProcedimentoForm.getNomeProcedimento().toUpperCase());
        procedimentoRepository.save(updateProcedimento);
        return updateProcedimento;
    }

    public boolean remover(Procedimento procedimento) {
        try {
            procedimentoRepository.deleteById(procedimento.getId());
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
