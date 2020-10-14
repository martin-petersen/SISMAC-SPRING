package com.example.starter.service;

import com.example.starter.dto.VagaDTO;
import com.example.starter.form.NovaDataVagaFORM;
import com.example.starter.form.VagaFORM;
import com.example.starter.model.Consulta;
import com.example.starter.model.Especialidade;
import com.example.starter.model.Exame;
import com.example.starter.model.Especialidade;
import com.example.starter.model.Vaga;
import com.example.starter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    private ExameRepository exameRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public Page<VagaDTO> listarExames(String nomeExame, Pageable pageable) {
        LocalDate ontem = LocalDate.now().minusDays(1);
        List<Exame> exames = exameRepository.findByNomeExame(nomeExame);
        Exame exame = null;
        for (Exame e:
             exames) {
            if(e.getNomeExame().equals(nomeExame))
                exame = e;
        }
        return convertInDetalhamentoDTO(vagaRepository.findByExameAndDataAfter(exame,ontem),pageable);
    }

    public Page<VagaDTO> listarConsulta(Pageable pageable) {
        LocalDate ontem = LocalDate.now().minusDays(1);
        Consulta consulta = consultaRepository.findById(Long.parseLong("1")).get();
        return convertInDetalhamentoDTO(vagaRepository.findByConsultaAndDataAfter(consulta,ontem),pageable);
    }

    public Page<VagaDTO> listar(Pageable pageable) {
        LocalDate ontem = LocalDate.now().minusDays(1);
        return convertInDetalhamentoDTO(vagaRepository.findByDataAfter(ontem),pageable);
    }
    public List<VagaDTO> listarEspecialidade(Especialidade especialidade,Pageable pageable){
        return convertInDetalhamentoDTO(vagaRepository.findByEspecialidade(especialidade,pageable);
    }

    private Page<VagaDTO> convertInDetalhamentoDTO (List<Vaga> lista, Pageable pageable) {
        List<VagaDTO> vagaDTOList = new ArrayList<>();
        for (Vaga v:
                lista) {
            if(v.getConsulta() != null) {
                vagaDTOList.add(new VagaDTO(v.getData(),v.getVagasOfertadas(),v.getVagasRestantes(),v.getEspecialidade(),v.getConsulta()));
            } else {
                vagaDTOList.add(new VagaDTO(v.getData(),v.getVagasOfertadas(),v.getVagasRestantes(),v.getExame()));
            }
        }
        return new PageImpl<>(vagaDTOList,pageable,vagaDTOList.size());
    }

    public Vaga salvar(VagaFORM vagaFORM) {
        Vaga novaVaga = new Vaga();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(vagaFORM.getData(), formatter);
        novaVaga.setData(localDate);
        if (vagaFORM.isConsulta()) {
            Consulta consulta = consultaRepository.findById(Long.parseLong("1")).get();
            novaVaga.setConsulta(consulta);
            if(especialidadeRepository.findById(vagaFORM.getEspecialidade()).isPresent()) {
                Especialidade especialidade = especialidadeRepository.findById(vagaFORM.getEspecialidade()).get();
                novaVaga.setEspecialidade(especialidade);
            } else {
                return null;
            }
        } else {
            if(exameRepository.findById(vagaFORM.getExame()).isPresent()) {
                Exame exame = exameRepository.findById(vagaFORM.getExame()).get();
                novaVaga.setExame(exame);
            } else {
                return null;
            }
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
