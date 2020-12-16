package com.example.starter.service;

import com.example.starter.model.*;
import org.springframework.stereotype.Service;
import com.example.starter.service.EmailSender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceConcretoBarbearia extends JobServiceTemplate {

    @Override
    public boolean validate(ListaEspera lista) {
        return true;
    }
    @Override
    public void regraAgendamento() {
        List<ListaEspera> listaEspera = listaEsperaRepository.findByAtivoOrderByDataEntradaLista(true);
        List<ListaEspera> listCabelo = new ArrayList<>();
        List<ListaEspera> listBarba = new ArrayList<>();
        List<Vaga> vagas = vagaRepository.findByDataAfter(LocalDate.now());

        for (ListaEspera le: listaEspera) {
            if (validate(le)) {
                if (le.getCabelo() != null) {
                    listCabelo.add(le);
                } else {
                    listBarba.add(le);
                }
            }
        }

        for (Vaga v: vagas) {
            if (v.getCabelo() != null) {
                List<ListaEspera> listaCabeloOrdenada = new ArrayList<>();
                //arrays para serem como auxiliares para cada dia diferente ter os de fidelidade em 1 na lista final
                List<ListaEspera> deUmDiaFiel = new ArrayList<>();
                List<ListaEspera> deUmDiaNaoFiel = new ArrayList<>();

                for (ListaEspera le: listCabelo) {
                    if (le.getCliente().getFidelidade()){
                        deUmDiaFiel.add(le);
                        continue;
                    } else{
                        deUmDiaNaoFiel.add(le);
                        continue;
                    }
                }

                for(ListaEspera l: deUmDiaFiel){
                    listaCabeloOrdenada.add(l);
                }
                for(ListaEspera l: deUmDiaNaoFiel){
                    listaCabeloOrdenada.add(l);
                }

                for (ListaEspera le: listaCabeloOrdenada) {
                    if (v.getVagasRestantes() > 0 && le.isAtivo()) {
                        Agendamento novoAgendamento = new Agendamento(v.getData(), le, v.getId());
                        agendamentoRepository.save(novoAgendamento);
                        le.setAtivo(false);
                        listaEsperaRepository.save(le);
                        v.setVagasRestantes(v.getVagasRestantes() - 1);
                        vagaRepository.save(v);
                        if (usuarioRepository.findByCliente(le.getCliente()) != null) {
                            Cliente cliente = clienteRepository.findById(novoAgendamento.getCliente()).get();
                            Usuario usuario = usuarioRepository.findByCliente(cliente);
                            EmailSender.getInstancia().confirmaCabelo(usuario,novoAgendamento, cliente.getNomeCliente());
                        } else {
                            break;
                        }
                    }
                }
            } else {
                List<ListaEspera> listaBarbaOrdenada = new ArrayList<>();

                //arrays para serem como auxiliares para cada dia diferente ter os de fidelidade em 1 na lista final
                List<ListaEspera> deUmDiaFiel = new ArrayList<>();
                List<ListaEspera> deUmDiaNaoFiel = new ArrayList<>();

                for (ListaEspera le: listBarba) {
                    if (le.getCliente().getFidelidade()){
                        deUmDiaFiel.add(le);
                        continue;
                    } else {
                        deUmDiaNaoFiel.add(le);
                        continue;
                    }
                }

                for (ListaEspera l: deUmDiaFiel){
                    listaBarbaOrdenada.add(l);
                }
                for (ListaEspera l: deUmDiaNaoFiel){
                    listaBarbaOrdenada.add(l);
                }

                for (ListaEspera le: listaBarbaOrdenada) {
                    if(v.getVagasRestantes() > 0 && le.isAtivo()) {
                        Agendamento novoAgendamento = new Agendamento(v.getData(), le, v.getId());
                        agendamentoRepository.save(novoAgendamento);
                        le.setAtivo(false);
                        listaEsperaRepository.save(le);
                        v.setVagasRestantes(v.getVagasRestantes() - 1);
                        vagaRepository.save(v);
                        if(usuarioRepository.findByCliente(le.getCliente()) != null) {
                            Cliente cliente = clienteRepository.findById(novoAgendamento.getCliente()).get();
                            Usuario usuario = usuarioRepository.findByCliente(cliente);
                            EmailSender.getInstancia().confirmaBarba(usuario,novoAgendamento,cliente.getNomeCliente());
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
