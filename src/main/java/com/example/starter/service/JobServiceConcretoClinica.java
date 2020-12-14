package com.example.starter.service;

import com.example.starter.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceConcretoClinica extends JobServiceTemplate {
    
    @Override
    public boolean validate(ListaEspera lista) {
        return true;
    }
    @Override
    public void regraAgendamento() {
        List<ListaEspera> listaEspera = listaEsperaRepository.findByAtivoOrderByDataEntradaLista(true);
        List<ListaEspera> ListCabelo = new ArrayList<>();
        List<ListaEspera> ListBarba = new ArrayList<>();
        List<Vaga> vagas = vagaRepository.findByDataAfter(LocalDate.now());

        for (ListaEspera le:
                listaEspera) {
            if(validate(le)) {
                if(le.getCabelo() != null) {
                    ListCabelo.add(le);
                } else {
                    ListBarba.add(le);
                }
            }
        }

        for (Vaga v:
                vagas) {
            if(v.getCabelo() != null) {
                List<ListaEspera> listaCabeloOrdenada = new ArrayList<>();
                for (ListaEspera le:
                     ListCabelo) {
                    //BLOCO DE CÓDIGO QUE VAI ORGANIZAR CLIENTES QUE ENTRARAM NA LISTA DE ESPERA NO MESMO DIA
                    //INSERINDO OS QUE TEM FIDELIDADE NA FRENTE DOS QUE NÃO TEM
                }
                for (ListaEspera le:
                     listaCabeloOrdenada) {
                    if(v.getVagasRestantes() > 0 && le.isAtivo()) {
                        Agendamento novoAgendamento = new Agendamento(v.getData(), le, v.getId());
                        agendamentoRepository.save(novoAgendamento);
                        le.setAtivo(false);
                        listaEsperaRepository.save(le);
                        v.setVagasRestantes(v.getVagasRestantes() - 1);
                        vagaRepository.save(v);
                        if(usuarioRepository.findByCliente(le.getCliente()) != null) {
                            Cliente cliente = clienteRepository.findById(novoAgendamento.getCliente()).get();
                            //EMAIL SENDER PARA CONFIRMAÇÃO DE CABELO
                        } else {
                            break;
                        }
                    }
                }
            } else {
                List<ListaEspera> listaBarbaOrdenada = new ArrayList<>();
                for (ListaEspera le:
                        ListBarba) {
                    //BLOCO DE CÓDIGO QUE VAI ORGANIZAR CLIENTES QUE ENTRARAM NA LISTA DE ESPERA NO MESMO DIA
                    //INSERINDO OS QUE TEM FIDELIDADE NA FRENTE DOS QUE NÃO TEM
                }
                for (ListaEspera le:
                     listaBarbaOrdenada) {
                    if(v.getVagasRestantes() > 0 && le.isAtivo()) {
                        Agendamento novoAgendamento = new Agendamento(v.getData(), le, v.getId());
                        agendamentoRepository.save(novoAgendamento);
                        le.setAtivo(false);
                        listaEsperaRepository.save(le);
                        v.setVagasRestantes(v.getVagasRestantes() - 1);
                        vagaRepository.save(v);
                        if(usuarioRepository.findByCliente(le.getCliente()) != null) {
                            Cliente cliente = clienteRepository.findById(novoAgendamento.getCliente()).get();
                            //EMAIL SENDER PARA CONFIRMAÇÃO DE BARBA
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void regraDeConfirmacao(Agendamento agendamento) {
        Cliente cliente = clienteRepository.findById(agendamento.getCliente()).get();
        Usuario usuario = usuarioRepository.findByCliente(cliente);
        EmailSender.getInstancia().lembreteDeMarcacao(usuario, cliente, agendamento, "LEMBRETE");
    }
}
