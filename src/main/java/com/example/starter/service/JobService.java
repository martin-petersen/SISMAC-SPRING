package com.example.starter.service;

import com.example.starter.model.*;
import com.example.starter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobService {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private ListaEsperaRepository listaEsperaRepository;

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailSender emailSender;
    // Todos os dias meia noite
    //@Scheduled(cron = "0 0 0 1/1 * ?")
    // De um em um minuto
    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void criarAgendamentos() {
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
                        if (v.getVagasRestantes() > 0) {
                            Agendamento novoAgendamento = new Agendamento(v.getData(), listaConsulta,v.getMedico(),v.getLugar());
                            agendamentoRepository.save(novoAgendamento);
                            listaConsulta.setAtivo(false);
                            listaEsperaRepository.save(listaConsulta);
                            v.setVagasRestantes(v.getVagasRestantes() - 1);
                            vagaRepository.save(v);

                            if(usuarioRepository.findByPaciente(listaConsulta.getPaciente()) != null) {
                                assert novoAgendamento.getEspecialidade_id() != null;
                                Especialidade especialidade = especialidadeRepository.findById(novoAgendamento.getEspecialidade_id()).get();
                                Paciente paciente = pacienteRepository.findById(novoAgendamento.getPaciente_id()).get();
                                emailSender.consultaConfimada(
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
                        if (v.getVagasRestantes() > 0) {
                            Agendamento novoAgendamento = new Agendamento(v.getData(), listaExame, null,v.getLugar());
                            agendamentoRepository.save(novoAgendamento);
                            listaExame.setAtivo(false);
                            listaEsperaRepository.save(listaExame);
                            v.setVagasRestantes(v.getVagasRestantes() - 1);
                            vagaRepository.save(v);

                            if(usuarioRepository.findByPaciente(listaExame.getPaciente()) != null) {
                                assert novoAgendamento.getExame_id() != null;
                                Exame exame = exameRepository.findById(novoAgendamento.getExame_id()).get();
                                Paciente paciente = pacienteRepository.findById(novoAgendamento.getPaciente_id()).get();
                                emailSender.exameConfimado(
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
    // Todos os dias 1h da manhã
    //@Scheduled(cron = "0 0 1 1/1 * ?")
    // De um em um minuto
    @Scheduled(cron = "0 0/2 * 1/1 * ?")
    public void enviarEmailConfirmacao() {
        List<Agendamento> agendamentos = agendamentoRepository.findByDataAgendamento(LocalDate.now().plusDays(1));
        for (Agendamento agendamento:
                agendamentos) {
            Paciente paciente = pacienteRepository.findById(agendamento.getPaciente_id()).get();
            Usuario usuario = usuarioRepository.findByPaciente(paciente);
            if(agendamento.isConsulta() && agendamento.getEspecialidade_id() != null && especialidadeRepository.findById(agendamento.getEspecialidade_id()).isPresent()) {
                Especialidade especialidade = especialidadeRepository.findById(agendamento.getEspecialidade_id()).get();
                emailSender.lembreteConsulta(
                        usuario.getEmail(),
                        paciente.getNomePaciente(),
                        agendamento.getDataAgendamento(),
                        especialidade.getNomeEspecialidade(),
                        agendamento.getMedico(),
                        agendamento.getLugar()
                );
            } else if(agendamento.getExame_id() != null && exameRepository.findById(agendamento.getExame_id()).isPresent()){
                Exame exame = exameRepository.findById(agendamento.getExame_id()).get();
                emailSender.lembreteExame(
                        usuario.getEmail(),
                        paciente.getNomePaciente(),
                        exame.getNomeExame(),
                        agendamento.getDataAgendamento(),
                        agendamento.getLugar()
                        );
            }
        }
    }

    private boolean validate(ListaEspera listaEspera) {
        if(!listaEspera.isRequerAutorizacao()) {
            return true;
        } else {
            if(listaEspera.getEncaminhamento() != null) {
                Encaminhamento encaminhamento = listaEspera.getEncaminhamento();
                return encaminhamento.isAutorizado();
            } else {
                return false;
            }
        }
    }
}
