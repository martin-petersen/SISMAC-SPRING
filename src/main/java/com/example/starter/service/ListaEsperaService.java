package com.example.starter.service;

import com.example.starter.form.DeleteFilaFORM;
import com.example.starter.form.ListaEsperaConsultaFORM;
import com.example.starter.form.ListaEsperaExameFORM;
import com.example.starter.form.UpdateListaEsperaFORM;
import com.example.starter.model.*;
import com.example.starter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ListaEsperaService {

    @Autowired
    private ListaEsperaRepository listaEsperaRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<ListaEspera> listaEsperaConsulta(Long especialidade_id, Pageable pageable) {
        if(especialidadeRepository.findById(especialidade_id).isPresent()) {
            Especialidade especialidade = especialidadeRepository.findById(especialidade_id).get();
            return listaEsperaRepository.findByEspecialidade(especialidade);
        } else {
            return null;
        }
    }

    public List<ListaEspera> listaEsperaExame(Long exame_id, Pageable pageable) {
        if(exameRepository.findById(exame_id).isPresent()) {
            Exame exame = exameRepository.findById(exame_id).get();
            return listaEsperaRepository.findByExame(exame);
        } else {
            return null;
        }
    }

    public List<ListaEspera> listaEspera() {
        return listaEsperaRepository.findAll();
    }

    @Transactional
    public ListaEspera filaExame(ListaEsperaExameFORM listaEsperaFORM) {
        ListaEspera entrada = new ListaEspera();
        entrada.setDataEntradaLista(LocalDateTime.now());
        entrada.setLastUpdate(entrada.getDataEntradaLista());

        if(pacienteRepository.findById(listaEsperaFORM.getPaciente_id()).isPresent()) {
            entrada.setPaciente(pacienteRepository.findById(listaEsperaFORM.getPaciente_id()).get());
        } else {
            return null;
        }

        if(usuarioRepository.findById(listaEsperaFORM.getUser_id()).isPresent()) {
            Usuario usuario = usuarioRepository.findById(listaEsperaFORM.getUser_id()).get();
            entrada.setUsuarioLastUpdate(usuario);
        } else {
            return null;
        }

        if(exameRepository.findById(listaEsperaFORM.getExame_id()).isPresent()) {
            entrada.setExame(exameRepository.findById(listaEsperaFORM.getExame_id()).get());
        } else {
            return null;
        }

        if(entrada.getExame().isAutorizacao()) {
            entrada.setRequerAutorizacao(true);
        } else {
            entrada.setRequerAutorizacao(false);
        }

        entrada.setAtivo(true);
        listaEsperaRepository.save(entrada);
        return entrada;
    }

    @Transactional
    public ListaEspera filaConsulta(ListaEsperaConsultaFORM listaEsperaFORM) {
        ListaEspera entrada = new ListaEspera();
        entrada.setDataEntradaLista(LocalDateTime.now());
        entrada.setLastUpdate(entrada.getDataEntradaLista());
        entrada.setRequerAutorizacao(false);
        if(pacienteRepository.findById(listaEsperaFORM.getPaciente_id()).isPresent()) {
            entrada.setPaciente(pacienteRepository.findById(listaEsperaFORM.getPaciente_id()).get());
        } else {
            return null;
        }

        if(usuarioRepository.findById(listaEsperaFORM.getUser_id()).isPresent()) {
            Usuario usuario = usuarioRepository.findById(listaEsperaFORM.getUser_id()).get();
            entrada.setUsuarioLastUpdate(usuario);
        } else {
            return null;
        }

        entrada.setConsulta(consultaRepository.findById(Long.parseLong("1")).get());
        if(especialidadeRepository.findById(listaEsperaFORM.getEspecialidade_id()).isPresent()) {
            entrada.setEspecialidade(especialidadeRepository.findById(listaEsperaFORM.getEspecialidade_id()).get());
        } else {
            return null;
        }

        entrada.setAtivo(true);
        listaEsperaRepository.save(entrada);
        return entrada;
    }

    @Transactional
    public ListaEspera removerDaFila(Long id, DeleteFilaFORM deleteFilaFORM) {
        if(listaEsperaRepository.findById(id).isPresent()) {
            ListaEspera listaEspera = listaEsperaRepository.findById(id).get();
            listaEspera.setAtivo(false);
            listaEspera.setLastUpdate(LocalDateTime.now());
            if(usuarioRepository.findById(deleteFilaFORM.getUser_id()).isPresent()) {
                listaEspera.setUsuarioLastUpdate(usuarioRepository.findById(deleteFilaFORM.getUser_id()).get());
            } else {
                return null;
            }
            listaEspera.setMotivoRemocao(deleteFilaFORM.getMotivoCancelamento());
            listaEsperaRepository.save(listaEspera);
            return listaEspera;
        } else {
            return null;
        }
    }

    public ListaEspera update(Long id, UpdateListaEsperaFORM listaEsperaFORM) {
        if(listaEsperaRepository.findById(id).isPresent()) {
            ListaEspera listaEspera = listaEsperaRepository.findById(id).get();
            if(especialidadeRepository.findById(listaEsperaFORM.getEspecilidade_id()).isPresent()) {
                listaEspera.setEspecialidade(especialidadeRepository.findById(listaEsperaFORM.getEspecilidade_id()).get());
            }
            if(exameRepository.findById(listaEsperaFORM.getExame_id()).isPresent()) {
                Exame exame = exameRepository.findById(listaEsperaFORM.getExame_id()).get();
                listaEspera.setExame(exame);
                listaEspera.setRequerAutorizacao(exame.isAutorizacao());
            }
            if(usuarioRepository.findById(listaEsperaFORM.getUser_id()).isPresent()) {
                listaEspera.setUsuarioLastUpdate(usuarioRepository.findById(listaEsperaFORM.getUser_id()).get());
            } else {
                return null;
            }
            listaEspera.setLastUpdate(LocalDateTime.now());
            return listaEsperaRepository.save(listaEspera);
        } else {
            return null;
        }
    }
}
