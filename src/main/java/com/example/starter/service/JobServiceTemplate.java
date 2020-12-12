package com.example.starter.service;

import com.example.starter.model.*;
import com.example.starter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import com.example.starter.service.EmailSender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  abstract class JobServiceTemplate {
    @Autowired
    public PacienteRepository pacienteRepository;

    @Autowired
    public EspecialidadeRepository especialidadeRepository;

    @Autowired
    public ExameRepository exameRepository;

    @Autowired
    public ListaEsperaRepository listaEsperaRepository;

    @Autowired
    public VagaRepository vagaRepository;

    @Autowired
    public AgendamentoRepository agendamentoRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;

    //neste metódo deve ser implementado uma validacao para a lista de espera.
    public abstract boolean validate(ListaEspera lista);

    // Todos os dias meia noite
    //@Scheduled(cron = "0 0 0 1/1 * ?")
    // De um em um minuto
    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void criarAgendamentos() {
        regraAgendamento();
    }

    public abstract void regraAgendamento();

    // Todos os dias 1h da manhã
    //@Scheduled(cron = "0 0 1 1/1 * ?")
    // De um em um minuto
    @Scheduled(cron = "0 0/2 * 1/1 * ?")
    public void enviarConfirmacao() {
        List<Agendamento> agendamentos = agendamentoRepository.findByDataAgendamento(LocalDate.now().plusDays(1));
        for (Agendamento agendamento: agendamentos) {
            regraDeConfirmacao(agendamento);
        }
    }

    public abstract void regraDeConfirmacao(Agendamento agendamento);
}
