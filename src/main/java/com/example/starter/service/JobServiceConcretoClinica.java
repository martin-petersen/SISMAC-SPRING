package com.example.starter.service;

import com.example.starter.model.*;
import com.example.starter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
package com.example.starter.service.EmailSender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@service
public class JobServiceConcreto extends JobServiceTemplate {
    @override
    public Notificacao setarNoficador(){
        return EmailSender.getInstancia();
    }
    
    @override
    public List<HashMap<Long,List<ListaEspera>>> carregarListas(){
        List<ListaEspera> listaEspera = listaEsperaRepository.findByAtivoOrderByDataEntradaLista(true);
        List<HashMap<Long,List<ListaEspera>>> resposta = new List<HashMap<Long,List<ListaEspera>>>();
         for(ListaEspera le:listaEspera){
            if(le.getEspecialidade().getId() == 1){
                if (resposta.containsKey(1)){
                    resposta[1].add(le);
                }else{
                    HashMap<Long,List<ListaEspera>> nova = {1:new List<ListaEspera>();}
                    reposta[1] = nova;
                }
            }else{
                if(resposta.containsKey(2)){
                    resposta[2].add(le);
                }else{
                    HashMap<Long,List<ListaEspera>> nova = {2:new List<ListaEspera>();}
                    reposta[2] = nova;

                }
            }
        }
        return resposta;
            
    }
        


    
    @override
    public  abstract boolean validate(ListaEspera lista){
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
    @override
    public abstract void regraDeAgendamento(HashMap<Long,List<ListaEspera>> lista,Vaga vaga){
        //id 1 para consultas 
        if(lista.containsKey(1)){
                List<ListaEspera> listaConsultas = mapListConsultas.get(v.getEspecialidade().getId());
                for (ListaEspera listaConsulta : listaConsultas) {
                    if(listaconsultas[0] == v.getEspecialidadeMedica()){
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
                                Notificador.consultaConfimada(
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
                    List<ListaEspera> listaExames = mapa.get(v.getEspecialidade().getId());
                    for (ListaEspera listaExame : listaExames) {
                        if(listExame[0]== v.getExameMedico()){
                        
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
                                notificador.exameConfimado(
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
