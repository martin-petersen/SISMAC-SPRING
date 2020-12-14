package com.example.starter.service;

import com.example.starter.dto.ListaEsperaDTO;
import com.example.starter.exceptions.ServiceException;
import com.example.starter.form.ListaEsperaFORM;
import com.example.starter.form.UpdateListaEsperaFORM;
import com.example.starter.model.*;
import com.example.starter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListaEsperaService {

    @Autowired
    private ListaEsperaRepository listaEsperaRepository;

    @Autowired
    private BarbaRepository barbaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CabeloRepository cabeloRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<ListaEspera> listaEsperaCabelo() throws ServiceException {
        if(cabeloRepository.findById(1L).isPresent()) {
            Cabelo cabelo2 = cabeloRepository.findById(1L).get();
            return listaEsperaRepository.findByCabeloAndAtivo(cabelo2,true);
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Cabelo","Um erro acontenceu");
        }
    }

    public List<ListaEspera> listaEsperaBarba() throws ServiceException {
        if(barbaRepository.findById(1L).isPresent()) {
            Barba barba2 = barbaRepository.findById(1L).get();
            return listaEsperaRepository.findByBarbaAndAtivo(barba2,true);
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Barba","Um erro aconteceu");
        }
    }

    public List<ListaEspera> listaEspera() {
        return listaEsperaRepository.findByAtivo(true);
    }

    @Transactional
    public ListaEspera filaBarba(ListaEsperaFORM listaEsperaFORM) throws ServiceException {
        ListaEspera entrada = new ListaEspera();
        entrada.setLastUpdate(LocalDateTime.now());
        entrada.setDataEntradaLista(LocalDate.now());

        if(clienteRepository.findById(listaEsperaFORM.getCliente_id()).isPresent()) {
            entrada.setCliente(clienteRepository.findById(listaEsperaFORM.getCliente_id()).get());
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Cliente","Não foi encontrado esse cliente no sistema");
        }

        if(usuarioRepository.findById(listaEsperaFORM.getUser_id()).isPresent()) {
            Usuario usuario = usuarioRepository.findById(listaEsperaFORM.getUser_id()).get();
            entrada.setUsuarioLastUpdate(usuario);
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Usuario","Não foi encontrado esse usuario no sistema");
        }

        if(listaEsperaFORM.isBarba()) {
            entrada.setBarba(barbaRepository.findById(1L).get());
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Exame","Não foi encontrado esse exame no sistema");
        }

        entrada.setAtivo(true);
        listaEsperaRepository.save(entrada);
        return entrada;
    }

    @Transactional
    public ListaEspera filaCabelo(ListaEsperaFORM listaEsperaFORM) throws ServiceException {
        ListaEspera entrada = new ListaEspera();
        entrada.setLastUpdate(LocalDateTime.now());
        entrada.setDataEntradaLista(LocalDate.now());
        if(clienteRepository.findById(listaEsperaFORM.getCliente_id()).isPresent()) {
            entrada.setCliente(clienteRepository.findById(listaEsperaFORM.getCliente_id()).get());
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Paciente","Não foi encontrado esse paciente no sistema");
        }

        if(usuarioRepository.findById(listaEsperaFORM.getUser_id()).isPresent()) {
            Usuario usuario = usuarioRepository.findById(listaEsperaFORM.getUser_id()).get();
            entrada.setUsuarioLastUpdate(usuario);
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Usuario","Não foi encontrado esse usuario no sistema");
        }

        if(listaEsperaFORM.isCabelo()) {
            entrada.setCabelo(cabeloRepository.findById(1L).get());
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Especialidade","Não foi encontrada essa especialidade no sistema");
        }

        entrada.setAtivo(true);
        listaEsperaRepository.save(entrada);
        return entrada;
    }

    @Transactional
    public ListaEspera removerDaFila(Long id, Long user) throws ServiceException {
        if(listaEsperaRepository.findById(id).isPresent()) {
            ListaEspera listaEspera = listaEsperaRepository.findById(id).get();
            listaEspera.setAtivo(false);
            listaEspera.setLastUpdate(LocalDateTime.now());
            if(usuarioRepository.findById(user).isPresent()) {
                listaEspera.setUsuarioLastUpdate(usuarioRepository.findById(user).get());
            } else {
                throw new ServiceException(HttpStatus.NOT_FOUND,"Usuario","Não foi encontrado esse usuario no sistema");
            }
            listaEsperaRepository.save(listaEspera);
            return listaEspera;
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"ListaEspera","Não foi encontrada essa solicitação de espera no sistema");
        }
    }

    public ListaEspera update(Long id, UpdateListaEsperaFORM listaEsperaFORM) throws ServiceException {
        if(listaEsperaRepository.findById(id).isPresent()) {
            ListaEspera listaEspera = listaEsperaRepository.findById(id).get();
            if(listaEsperaFORM.isCabelo()) {
                listaEspera.setCabelo(cabeloRepository.findById(1L).get());
            }
            if(listaEsperaFORM.isBarba()) {
                listaEspera.setBarba(barbaRepository.findById(1L).get());
            }
            if(usuarioRepository.findById(listaEsperaFORM.getUser_id()).isPresent()) {
                listaEspera.setUsuarioLastUpdate(usuarioRepository.findById(listaEsperaFORM.getUser_id()).get());
            } else {
                throw new ServiceException(HttpStatus.NOT_FOUND,"Usuario","Não foi encontrado esse usuario no sistema");
            }
            listaEspera.setLastUpdate(LocalDateTime.now());
            return listaEsperaRepository.save(listaEspera);
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"ListaEspera","Não foi encontrada essa solicitação de espera no sistema");
        }
    }


    public List<ListaEsperaDTO> buscarPorCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id).get();
        List<ListaEspera> listaEspera = listaEsperaRepository.findByClienteAndAtivo(cliente,true);
        List<ListaEsperaDTO> listaEsperaDTO = new ArrayList<>();

        for (ListaEspera li:
             listaEspera) {
            listaEsperaDTO.add(new ListaEsperaDTO(li));
        }

        return listaEsperaDTO;
    }
}
