package com.example.starter.service;

import com.example.starter.model.*;
import com.example.starter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  abstract class JobServiceTemplate {
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

    private Notificacao notificador;

    public void setNotificador(Notificacao notificador){
        this.notificador = notificador;
    }

    //neste metódo ele indica qual notificador sera usado
    public abstract Notificacao setarNotificador();

    //neste metódo devesse carregar todas as listas de todos os tipos de solicitações.
    public abstract  List<HashMap<Long,List<ListaEspera>>> carregarListas();

    //neste metódo deve ser implementado uma validacao para a lista de espera.
    public abstract boolean validate(ListaEspera lista);

    //neste metódo contém a regra de agendamento passando a vaga e um mapa que contem as litas de esperas para
    //a especialidade daquela vaga. 
    public abstract void  regraDeAgendamento(HashMap<Long,List<ListaEspera>> lista,Vaga vaga);
    
    // Todos os dias meia noite
    //@Scheduled(cron = "0 0 0 1/1 * ?")
    // De um em um minuto
    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void criarAgendamentos() {
        setNotificador(setarNotificador());
        List<ListaEspera> listaEspera = listaEsperaRepository.findByAtivoOrderByDataEntradaLista(true);
        List<HashMap<Long,List<ListaEspera>>> listas = carregarListas();
        List<Vaga> vagas = vagaRepository.findByDataAfter(LocalDate.now());

        for (ListaEspera le:
                listaEspera) {
            if(validate(le)) {
                for(HashMap<Long,List<ListaEspera>> mapa:  listas){
                    if(mapa.containsKey(le.getEspecialidade().getId())){
                        mapa.get(le.getEspecialidade().getId()).add(le);
                    }
                }
            }
        }

        for(Vaga v: vagas){
            for(HashMap<Long,List<ListaEspera>> mapa:  listas){
                if(mapa.containsKey(v.getEspecialidade().getId())){
                    regraDeAgendamento(mapa,v);
                }
            }
        }
        
        
        
        
        
        
    }
    // Todos os dias 1h da manhã
    //@Scheduled(cron = "0 0 1 1/1 * ?")
    // De um em um minuto
    @Scheduled(cron = "0 0/2 * 1/1 * ?")
    public void enviarConfirmacao() {
        List<Agendamento> agendamentos = agendamentoRepository.findByDataAgendamento(LocalDate.now().plusDays(1));
        for (Agendamento agendamento:
                agendamentos) {
            Paciente paciente = pacienteRepository.findById(agendamento.getPaciente_id()).get();
            Usuario usuario = usuarioRepository.findByPaciente(paciente);
            Especialidade especialidade = especialidadeRepository.findById(agendamento.getEspecialidade_id()).get();
            notificador.lembrete(paciente.getNomePaciente(),usuario.getUsername(),especialidade.getNomeEspecialidade(), agendamento.getDataAgendamento(),agendamento.getLugar());
            
            
            
        }
    }

    
}
