package com.example.starter.service;

import com.example.starter.exceptions.ServiceException;
import com.example.starter.form.AutorizacaoFORM;
import com.example.starter.model.Anexo;
import com.example.starter.model.Encaminhamento;
import com.example.starter.model.ListaEspera;
import com.example.starter.repository.AnexoRepository;
import com.example.starter.repository.EncaminhamentoRepository;
import com.example.starter.repository.ListaEsperaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EncaminhamentoService {

    @Autowired
    private AnexoRepository anexoRepository;

    @Autowired
    private EncaminhamentoRepository encaminhamentoRepository;

    @Autowired
    private ListaEsperaRepository listaEsperaRepository;

    @Transactional
    public Encaminhamento salvar(Long filaEspera_id, Long id) throws ServiceException {
        ListaEspera listaEspera = new ListaEspera();
        Encaminhamento encaminhamento = new Encaminhamento();
        Anexo anexo = new Anexo();
        if(listaEsperaRepository.findById(filaEspera_id).isPresent()) {
            listaEspera = listaEsperaRepository.findById(filaEspera_id).get();
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"ListaEspera","não foi encontrada essa solicitação de espera");
        }

        if(anexoRepository.findById(id).isPresent()) {
            anexo = anexoRepository.findById(id).get();
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Anexo","não foi encontrado esse anexo");
        }
        encaminhamento.setAnexo(anexo);
        encaminhamentoRepository.save(encaminhamento);

        listaEspera.setEncaminhamento(encaminhamento);
        listaEspera.setRequerAutorizacao(false);
        listaEsperaRepository.save(listaEspera);

        return encaminhamento;
    }

    @Transactional
    public Encaminhamento download(Long id) throws ServiceException {
        if(encaminhamentoRepository.findById(id).isPresent()) {
            return encaminhamentoRepository.findById(id).get();
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Encaminhamento","não foi encontrado esse encaminhamento");
        }
    }

    @Transactional
    public Encaminhamento update(Long id, Long anexo_id) throws ServiceException {
        if(encaminhamentoRepository.findById(id).isPresent()) {
            Encaminhamento encaminhamento = encaminhamentoRepository.findById(id).get();
            if(anexoRepository.findById(anexo_id).isPresent()) {
                Anexo anexo = anexoRepository.findById(anexo_id).get();
                anexoRepository.delete(encaminhamento.getAnexo());
                encaminhamento.setAnexo(anexo);
                encaminhamentoRepository.save(encaminhamento);
            } else {
                throw new ServiceException(HttpStatus.NOT_FOUND,"Anexo","não foi encontrado esse anexo");
            }
            return encaminhamento;
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Encaminhamento","não foi encontrado esse encaminhamento");
        }
    }

    public Encaminhamento autorizar(Long id, AutorizacaoFORM autorizacaoFORM) throws ServiceException {
        if(encaminhamentoRepository.findById(id).isPresent()) {
            Encaminhamento encaminhamento = encaminhamentoRepository.findById(id).get();
            if(autorizacaoFORM.isStatus()) {
                encaminhamento.setAutorizado(autorizacaoFORM.isStatus());
                encaminhamentoRepository.save(encaminhamento);
            } else {
                encaminhamento.setAutorizado(autorizacaoFORM.isStatus());
                encaminhamento.setMotivo(autorizacaoFORM.getMotivo());
                encaminhamentoRepository.save(encaminhamento);
            }
            return encaminhamento;
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Encaminhamento","não foi encontrado esse encaminhamento");
        }
    }
}
