package com.example.starter.service;

import com.example.starter.dto.ExameDTO;
import com.example.starter.dto.VagaDTO;
import com.example.starter.model.Exame;
import com.example.starter.model.Vaga;
import com.example.starter.repository.ConsultaRepository;
import com.example.starter.repository.ExameRepository;
import com.example.starter.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Page<VagaDTO> listarExames(String nomeExame, Pageable pageable) {
        LocalDateTime agora = LocalDateTime.now();
        List<Exame> exames = exameRepository.findByNomeExame(nomeExame);
        Exame exame = null;
        for (Exame e:
             exames) {
            if(e.getNomeExame().equals(nomeExame))
                exame = e;
        }
        return convertInDetalhamentoDTO(vagaRepository.findByExameAndDataAfter(exame,agora),pageable);
    }

    public Page<VagaDTO> listarConsulta(String consulta) {
        LocalDateTime agora = LocalDateTime.now();
    }

    public Page<VagaDTO> listar() {
        LocalDateTime agora = LocalDateTime.now();
    }

    private Page<VagaDTO> convertInDetalhamentoDTO (List<Vaga> lista, Pageable pageable) {
        List<VagaDTO> vagaDTOList = new ArrayList<>();
        for (Vaga v:
                lista) {
            vagaDTOList.add(new VagaDTO());
        }
        return new PageImpl<>(vagaDTOList,pageable,vagaDTOList.size());
    }
}
