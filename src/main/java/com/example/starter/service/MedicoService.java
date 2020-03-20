package com.example.starter.service;

import com.example.starter.form.MedicoForm;
import com.example.starter.model.Especialidade;
import com.example.starter.model.Medico;
import com.example.starter.repository.EspecialidadeRepository;
import com.example.starter.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public Medico salvar(MedicoForm medicoForm) {
        try {
            List<Especialidade> listEspecialidade= new ArrayList<>();
            for (String s:
                 medicoForm.getEspecialidades()) {
                String especialidade = "%" + s.toUpperCase() + "%";
                listEspecialidade.add(especialidadeRepository.findByNomeEspacialidade(especialidade));
            }
            Medico medico = new Medico(medicoForm);
            medico.setEspecialidade(listEspecialidade);
            medicoRepository.save(medico);
            return medico;
        }catch (Exception e) {
            return null;
        }
    }

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public List<Medico> listarPorEspecialidade(Especialidade especialidade) {
        try {
            String especialidadeBusca = "%" + especialidade.getNomeEspacialidade().toUpperCase() + "%";
            Especialidade retornoBusca = especialidadeRepository.findByNomeEspacialidade(especialidadeBusca);
            return retornoBusca.getMedicos();
        }catch (Exception e) {
            return null;
        }
    }

    public Medico buscarPorNumeroConselho(Medico medico) {
        try {
            return medicoRepository.findByNumeroConselho(medico.getNumeroConselho());
        }catch (Exception e) {
            return null;
        }
    }

    public List<Medico> buscarPorNome(Medico medico) {
        try {
            String nomeMedico = "%" + medico.getNomeMedico().toUpperCase() +"%";
            List<Medico> medicos = medicoRepository.findByNomeMedico(nomeMedico);
            return medicos;
        }catch (Exception e) {
            return null;
        }
    }

    public Medico atualizar(String numeroConselho, MedicoForm medicoForm) {
        try {
            List<Especialidade> listEspecialidade= new ArrayList<>();
            for (String s:
                    medicoForm.getEspecialidades()) {
                String especialidade = "%" + s.toUpperCase() + "%";
                listEspecialidade.add(especialidadeRepository.findByNomeEspacialidade(especialidade));
            }
            Medico medico = medicoRepository.findByNumeroConselho(numeroConselho);
            medico.setNomeMedico(medicoForm.getNomeMedico());
            medico.setConselho(medicoForm.getConselho());
            medicoForm.setNomeMedico(medicoForm.getNumeroConselho());
            medico.setEspecialidade(listEspecialidade);
            medicoRepository.save(medico);
            return medico;
        }catch (Exception e) {
            return null;
        }
    }

    public boolean deletar(Medico medico) {
        try {
            Medico medicoRemocao = medicoRepository.findByNumeroConselho(medico.getNumeroConselho());
            medicoRepository.deleteById(medicoRemocao.getId());
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
