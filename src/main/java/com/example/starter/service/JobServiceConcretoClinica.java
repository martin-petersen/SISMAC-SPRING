package com.example.starter.service;

import com.example.starter.model.*;
import com.example.starter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
    public Notificacao setarNotificador(){
        return EmailSender.getInstancia();
    }

    public EmailSender email = EmailSender.getInstancia();
    
    @Override
    public List<HashMap<Long,List<ListaEspera>>> carregarListas(){
        List<ListaEspera> listaEspera = listaEsperaRepository.findByAtivoOrderByDataEntradaLista(true);
        List<HashMap<Long,List<ListaEspera>>> resposta = new ArrayList<HashMap<Long,List<ListaEspera>>>();
        for(ListaEspera le:listaEspera){
            if(le.getEspecialidade().getId() == 1){
                for(HashMap<Long,List<ListaEspera>> mapa : resposta) {
                    if (mapa.containsKey(1)) {
                        mapa.get(1).add(le);
                    } else {
                        HashMap<Long, List<ListaEspera>> nova = new HashMap<Long, List<ListaEspera>>();
                        List<ListaEspera> lista = new ArrayList<ListaEspera>();
                        nova.put((long) 1, lista);
                        resposta.add(nova);
                    }
                }
            }else{
                for(HashMap<Long,List<ListaEspera>> mapa : resposta) {
                    if (mapa.containsKey(2)) {
                        mapa.get(2).add(le);
                    } else {
                        HashMap<Long, List<ListaEspera>> nova = new HashMap<Long, List<ListaEspera>>();
                        List<ListaEspera> lista = new ArrayList<ListaEspera>();
                        nova.put((long) 2, lista);
                        resposta.add(nova);
                    }
                }
            }
        }
        return resposta;
    }

    @Override
    public boolean validate(ListaEspera lista){
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
    public void regraDeAgendamento(HashMap<Long,List<ListaEspera>> lista,Vaga vaga){
        //id 1 para consultas 
        if(lista.containsKey(1)){
                List<ListaEspera> listaConsultas = lista.get(vaga.getEspecialidade().getId());
                for (ListaEspera listaConsulta : listaConsultas) {
                    if(listaConsulta.getEspecialidade() == vaga.getEspecialidade()){
                        if (vaga.getVagasRestantes() > 0) {
                            Agendamento novoAgendamento = new Agendamento(vaga.getData(), listaConsulta, vaga.getMedico(),vaga.getLugar(), vaga.getId());
                            agendamentoRepository.save(novoAgendamento);
                            listaConsulta.setAtivo(false);
                            listaEsperaRepository.save(listaConsulta);
                            vaga.setVagasRestantes(vaga.getVagasRestantes() - 1);
                            vagaRepository.save(vaga);
    
                            if(usuarioRepository.findByPaciente(listaConsulta.getPaciente()) != null) {
                                assert novoAgendamento.getEspecialidade_id() != null;
                                Especialidade especialidade = especialidadeRepository.findById(novoAgendamento.getEspecialidade_id()).get();
                                Paciente paciente = pacienteRepository.findById(novoAgendamento.getPaciente_id()).get();
                                email.consultaConfimada(
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
            }else{
                    List<ListaEspera> listaExames = lista.get(vaga.getEspecialidade().getId());
                    for (ListaEspera listaExame : listaExames) {
                        if(listaExame.getExame() == vaga.getExame()){
                        
                        if (vaga.getVagasRestantes() > 0) {
                            Agendamento novoAgendamento = new Agendamento(vaga.getData(), listaExame, null,vaga.getLugar(), vaga.getId());
                            agendamentoRepository.save(novoAgendamento);
                            listaExame.setAtivo(false);
                            listaEsperaRepository.save(listaExame);
                            vaga.setVagasRestantes(vaga.getVagasRestantes() - 1);
                            vagaRepository.save(vaga);

                            if(usuarioRepository.findByPaciente(listaExame.getPaciente()) != null) {
                                assert novoAgendamento.getExame_id() != null;
                                Exame exame = exameRepository.findById(novoAgendamento.getExame_id()).get();
                                Paciente paciente = pacienteRepository.findById(novoAgendamento.getPaciente_id()).get();
                                email.exameConfimado(
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
