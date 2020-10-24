package com.example.starter.service;

import com.example.starter.model.Anexo;
import com.example.starter.model.Encaminhamento;
import com.example.starter.model.ListaEspera;
import com.example.starter.repository.AnexoRepository;
import com.example.starter.repository.EncaminhamentoRepository;
import com.example.starter.repository.ListaEsperaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EncaminhamentoService {

    @Autowired
    private AnexoRepository anexoRepository;

    @Autowired
    private EncaminhamentoRepository encaminhamentoRepository;

    @Autowired
    private ListaEsperaRepository listaEsperaRepository;

    @Transactional
    public Encaminhamento salvar(Long filaEspera_id, Long id) {
        ListaEspera listaEspera = new ListaEspera();
        Encaminhamento encaminhamento = new Encaminhamento();
        Anexo anexo = new Anexo();
        if(listaEsperaRepository.findById(filaEspera_id).isPresent()) {
            listaEspera = listaEsperaRepository.findById(filaEspera_id).get();
        } else {
            return null;
        }

        if(anexoRepository.findById(id).isPresent()) {
            anexo = anexoRepository.findById(id).get();
        } else {
            return null;
        }
        encaminhamento.setAnexo(anexo);
        encaminhamentoRepository.save(encaminhamento);

        listaEspera.setEncaminhamento(encaminhamento);
        listaEspera.setRequerAutorizacao(false);
        listaEsperaRepository.save(listaEspera);

        return encaminhamento;
    }

    public Encaminhamento download(Long id) {
        if(encaminhamentoRepository.findById(id).isPresent()) {
            return encaminhamentoRepository.findById(id).get();
        } else {
            return null;
        }
    }
}
