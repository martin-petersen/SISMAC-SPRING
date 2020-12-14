package com.example.starter.service;

import com.example.starter.model.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.starter.service.EmailSender.java;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceConcretoClinica extends JobServiceTemplate {
    
    @AutoWired
    EmailSender email;
    
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
                //verifica se a lista de cabelo tem pelomenos um elemento para evitar errro
                //inicializa um data com o dia do 1 da lista para saber quantos terao naquele dia
                if(listCabelo.lenght >0){
                    LocalDate  dataAtual = listCabelo[0].getDataEntradaLista();
                }
                 
                //arrays para serem como auxiliares para cada dia diferente ter os de fidelidade em 1 na lista final
                List<ListaEspera>  deUmDiaFiel = new ArrayList<>();
                 List<ListaEspera>  deUmDiaNaoFiel = new ArrayList<>();
                for (ListaEspera le:
                     ListCabelo) {
                    
                    if(le.getDataEntradaLista().isEqual(dataAtual)){
                        if(le.getCliente().isFidelidade()){
                            deUmDiaFiel.add(le);
                            continue;
                        } else{
                            deUmNaoFiel.add(le);{
                            continue;
                        }          
                    }else{
                        //aconteceu uma mudanca de data de dia hora de colocar todos aqueles do dia na lista final
                        for(ListaEspera l:deUmDiaFiel){
                            listaCabeloOrdenada.add(l);
                        }
                        for(ListaEspera l:deUmdiaNaoFiel){
                            listaCabeloOrdenada.add(l);
                        }
                        //inicializar as varias novamente e ver em qual se encaixa essa nova do outro dia 
                        deUmDiaFiel = new ArrayList<>();
                        deUmDiaNaoFiel = new ArrayList<>();
                        if(le.getCliente().isFidelidade()){
                            deUmDiaFiel.add(le);
                        } else{
                            deUmNaoFiel.add(le);{
                        }
                        dataAtual = le.getDataEntradaLista();
                    }
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
                            Usuario usuario = usuarioRepository.findByCliente(cliente);
                        email.getInstancia().confirmaCabelo(usuario,novoAgendamento, cliente.getNomeCliente());
                        } else {
                            break;
                        }
                    }
                }
            } else {
                List<ListaEspera> listaBarbaOrdenada = new ArrayList<>();
                
                //verifica se a lista de barba tem pelomenos um elemento para evitar errro
                //inicializa um data com o dia do 1 da lista para saber quantos terao naquele dia
                if(listCabelo.lenght >0){
                    LocalDate  dataAtual = listaBarba[0].getDataEntradaLista();
                }
                //arrays para serem como auxiliares para cada dia diferente ter os de fidelidade em 1 na lista final
                List<ListaEspera>  deUmDiaFiel = new ArrayList<>();
                 List<ListaEspera>  deUmDiaNaoFiel = new ArrayList<>();
                for (ListaEspera le:
                        ListBarba) {
               
                if(le.getDataEntradaLista().isEqual(dataAtual)){
                    if(le.getCliente().isFidelidade()){
                        deUmDiaFiel.add(le);
                        continue;
                    } else{
                        deUmNaoFiel.add(le);{
                        continue;
                    }          
                }else{
                    //aconteceu uma mudanca ne data de dia, hora de colocar todos aqueles do dia na lista final
                    for(ListaEspera l:deUmDiaFiel){
                        listaBarbaOrdenada.add(l);
                    }
                    for(ListaEspera l:deUmdiaNaoFiel){
                        listaBarbaOrdenada.add(l);
                    }
                    //inicializar as varias novamente e ver em qual se encaixa essa nova do outro dia 
                    deUmDiaFiel = new ArrayList<>();
                    deUmDiaNaoFiel = new ArrayList<>();
                    if(le.getCliente().isFidelidade()){
                        deUmDiaFiel.add(le);
                    } else{
                        deUmNaoFiel.add(le);
                    }
                    dataAtual = le.getDataEntradaLista();
                }
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
                            Usuario usuario = usuarioRepository.findByCliente(cliente);
                            email.getInstancia().confirmaBarba(usuario,novoAgendamento,cliente.getNomeCliente());                         
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
