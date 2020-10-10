package com.example.starter.dto;

import com.example.starter.model.Especialidade;
import org.springframework.data.domain.Page;

public class EspecialidadeDTO {

    private Long id;
    private String nomeEspecialidade;

    public EspecialidadeDTO(Especialidade especialidade) {
        this.id = especialidade.getId();
        this.nomeEspecialidade = especialidade.getNomeEspacialidade();
    }

    public String getNomeEspecialidade() {
        return nomeEspecialidade;
    }

    public static EspecialidadeDTO convert(Especialidade especialidade) {
        return new EspecialidadeDTO(especialidade);
    }

    public static Page<EspecialidadeDTO> convert(Page<Especialidade> especialidades) {
        return especialidades.map(EspecialidadeDTO::new);
    }
}
