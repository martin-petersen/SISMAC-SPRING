package com.example.starter.service;

import com.example.starter.model.*;
import org.springframework.stereotype.Service;
import com.example.starter.service.EmailSender;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobServiceConcretoClinica extends JobServiceTemplate {
    
    @Override
    public boolean validate(ListaEspera lista) {
        if(!lista.isRequerAutorizacao()) {
            return true;
        } else {
            if(lista.getEncaminhamento() != null) {
                Encaminhamento encaminhamento = lista.getEncaminhamento();
                return encaminhamento.isAutorizado();
            } else {
                return false;
            }
        }
    }
    @Override
    public void regraAgendamento() {
        List<ListaEspera> listaEspera = listaEsperaRepository.findByAtivoOrderByDataEntradaLista(true);
        Map<Long,List<ListaEspera>> mapListConsultas = new HashMap<Long,List<ListaEspera>>();
        Map<Long,List<ListaEspera>> mapListExames = new HashMap<Long,List<ListaEspera>>();
        List<Vaga> vagas = vagaRepository.findByDataAfter(LocalDate.now());

        for (ListaEspera le:
                listaEspera) {
            if(validate(le)) {
                if(le.getEspecialidade() != null) {
                    if(!mapListConsultas.containsKey(le.getEspecialidade().getId())) {
                        mapListConsultas.put(le.getEspecialidade().getId(), new ArrayList<ListaEspera>());
                    }
                    mapListConsultas.get(le.getEspecialidade().getId()).add(le);
                } else {
                    assert le.getExame() != null;
                    if(!mapListExames.containsKey(le.getExame().getId())) {
                        mapListExames.put(le.getExame().getId(), new ArrayList<ListaEspera>());
                    }
                    mapListExames.get(le.getExame().getId()).add(le);
                }
            }
        }

        for (Vaga v:
                vagas) {
            if(v.getEspecialidade() != null) {
                if(mapListConsultas.containsKey(v.getEspecialidade().getId())) {
                    List<ListaEspera> listaConsultas = mapListConsultas.get(v.getEspecialidade().getId());
                    for (ListaEspera listaConsulta : listaConsultas) {
                        if (v.getVagasRestantes() > 0 && listaConsulta.isAtivo()) {
                            Agendamento novoAgendamento = new Agendamento(v.getData(), listaConsulta,v.getMedico(),v.getLugar(), v.getId());
                            agendamentoRepository.save(novoAgendamento);
                            listaConsulta.setAtivo(false);
                            listaEsperaRepository.save(listaConsulta);
                            v.setVagasRestantes(v.getVagasRestantes() - 1);
                            vagaRepository.save(v);

                            if(usuarioRepository.findByPaciente(listaConsulta.getPaciente()) != null) {
                                assert novoAgendamento.getEspecialidade_id() != null;
                                Especialidade especialidade = especialidadeRepository.findById(novoAgendamento.getEspecialidade_id()).get();
                                Paciente paciente = pacienteRepository.findById(novoAgendamento.getPaciente_id()).get();
                                EmailSender.getInstancia().consultaConfimada(
                                        usuarioRepository.findByPaciente(listaConsulta.getPaciente()).getEmail(),
                                        novoAgendamento.getDataAgendamento(),
                                        paciente.getNomePaciente(),
                                        especialidade.getNomeEspecialidade(),
                                        novoAgendamento.getMedico(),
                                        novoAgendamento.getLugar()

                                );
                            }
                        } else {
                            break;
                        }
                    }
                }
            } else {
                assert v.getExame() != null;
                if(mapListExames.containsKey(v.getExame().getId())) {
                    List<ListaEspera> listaExames = mapListExames.get(v.getExame().getId());
                    for (ListaEspera listaExame : listaExames) {
                        if (v.getVagasRestantes() > 0 && listaExame.isAtivo()) {
                            Agendamento novoAgendamento = new Agendamento(v.getData(), listaExame, null,v.getLugar(), v.getId());
                            agendamentoRepository.save(novoAgendamento);
                            listaExame.setAtivo(false);
                            listaEsperaRepository.save(listaExame);
                            v.setVagasRestantes(v.getVagasRestantes() - 1);
                            vagaRepository.save(v);

                            if(usuarioRepository.findByPaciente(listaExame.getPaciente()) != null) {
                                assert novoAgendamento.getExame_id() != null;
                                Exame exame = exameRepository.findById(novoAgendamento.getExame_id()).get();
                                Paciente paciente = pacienteRepository.findById(novoAgendamento.getPaciente_id()).get();
                                EmailSender.getInstancia().exameConfimado(
                                        usuarioRepository.findByPaciente(listaExame.getPaciente()).getEmail(),
                                        novoAgendamento.getDataAgendamento(),
                                        paciente.getNomePaciente(),
                                        exame.getNomeExame(),
                                        novoAgendamento.getLugar()
                                );
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }
}
