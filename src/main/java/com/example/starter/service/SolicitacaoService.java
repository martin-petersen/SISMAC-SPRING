package com.example.starter.service;

import com.example.starter.dto.PacienteAgendamentoDTO;
import java.util.ArrayList;
import java.util.List;


public abstract class SolicitacaoService{

    public abstract List<PacienteAgendamentoDTO> buscarPorVaga(Long id);

        

}