package com.example.starter.service;

import com.example.starter.dto.VagaDTO;
import com.example.starter.exceptions.ServiceException;
import com.example.starter.form.NovaDataVagaFORM;
import com.example.starter.form.VagaFORM;
import com.example.starter.model.Cabelo;
import com.example.starter.model.Barba;
import com.example.starter.model.Vaga;
import com.example.starter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private BarbaRepository barbaRepository;

    @Autowired
    private CabeloRepository cabeloRepository;

    public Page<VagaDTO> listarBarba(Pageable pageable) throws ServiceException {
        LocalDate ontem = LocalDate.now().minusDays(1);
        Barba barba = barbaRepository.findById(1L).get();
        return convertInDetalhamentoDTO(vagaRepository.findByBarbaAndDataAfter(barba,ontem),pageable);
    }

    public Page<VagaDTO> listarCabelo(Pageable pageable) {
        LocalDate ontem = LocalDate.now().minusDays(1);
        Cabelo cabelo = cabeloRepository.findById(1L).get();
        return convertInDetalhamentoDTO(vagaRepository.findByCabeloAndDataAfter(cabelo,ontem),pageable);
    }

    public Page<VagaDTO> listar(Pageable pageable) {
        LocalDate ontem = LocalDate.now().minusDays(1);
        return convertInDetalhamentoDTO(vagaRepository.findByDataAfter(ontem),pageable);
    }

    private Page<VagaDTO> convertInDetalhamentoDTO (List<Vaga> lista, Pageable pageable) {
        List<VagaDTO> vagaDTOList = new ArrayList<>();
        for (Vaga v:
                lista) {
            if(v.getCabelo() != null) {
                vagaDTOList.add(new VagaDTO(v.getId(),v.getData(),v.getVagasOfertadas(),v.getVagasRestantes(),v.getCabelo()));
            } else {
                vagaDTOList.add(new VagaDTO(v.getId(),v.getData(),v.getVagasOfertadas(),v.getVagasRestantes(),v.getBarba()));
            }
        }
        return new PageImpl<>(vagaDTOList,pageable,vagaDTOList.size());
    }

    public Vaga salvar(VagaFORM vagaFORM) throws ServiceException {
        Vaga novaVaga = new Vaga();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(vagaFORM.getData(), formatter);
        novaVaga.setData(localDate);
        if (vagaFORM.isCabelo()) {
            Cabelo cabelo = cabeloRepository.findById(1L).get();
            novaVaga.setCabelo(cabelo);
        } else {
            Barba barba = barbaRepository.findById(1L).get();
            novaVaga.setBarba(barba);
        }
        novaVaga.setVagasOfertadas(vagaFORM.getVagasOfertadas());
        novaVaga.setVagasRestantes(vagaFORM.getVagasOfertadas());
        vagaRepository.save(novaVaga);
        return novaVaga;
    }


    public Vaga atualizar(Long id, NovaDataVagaFORM novaDataVagaFORM) {
        Vaga vaga = vagaRepository.findById(id).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(novaDataVagaFORM.getNovaData(), formatter);
        vaga.setData(localDate);
        vagaRepository.save(vaga);
        return vaga;
    }
}
