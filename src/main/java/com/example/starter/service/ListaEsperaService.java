package com.example.starter.service;

import com.example.starter.exceptions.ServiceException;
import com.example.starter.form.DeleteFilaFORM;
import com.example.starter.form.ListaEsperaConsultaFORM;
import com.example.starter.form.ListaEsperaExameFORM;
import com.example.starter.form.UpdateListaEsperaFORM;
import com.example.starter.model.*;
import com.example.starter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<ListaEspera> listaEsperaConsulta(Long especialidade_id) throws ServiceException {
        if(especialidadeRepository.findById(especialidade_id).isPresent()) {
            Especialidade especialidade = especialidadeRepository.findById(especialidade_id).get();
            return listaEsperaRepository.findByEspecialidadeAndAtivo(especialidade,true);
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Especialidade","Não foi encontrada essa especialidade no sistema");
        }
    }

    public List<ListaEspera> listaEsperaExame(Long exame_id) throws ServiceException {
        if(exameRepository.findById(exame_id).isPresent()) {
            Exame exame = exameRepository.findById(exame_id).get();
            return listaEsperaRepository.findByExameAndAtivo(exame,true);
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Exame","Não foi encontrado esse exame no sistema");
        }
    }

    public List<ListaEspera> listaEspera() {
        return listaEsperaRepository.findByAtivo(true);
    }

    @Transactional
    public ListaEspera filaExame(ListaEsperaExameFORM listaEsperaFORM) throws ServiceException {
        ListaEspera entrada = new ListaEspera();
        entrada.setDataEntradaLista(LocalDateTime.now());
        entrada.setLastUpdate(entrada.getDataEntradaLista());

        if(pacienteRepository.findById(listaEsperaFORM.getPaciente_id()).isPresent()) {
            entrada.setPaciente(pacienteRepository.findById(listaEsperaFORM.getPaciente_id()).get());
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Paciente","Não foi encontrado esse paciente no sistema");
        }

        if(usuarioRepository.findById(listaEsperaFORM.getUser_id()).isPresent()) {
            Usuario usuario = usuarioRepository.findById(listaEsperaFORM.getUser_id()).get();
            entrada.setUsuarioLastUpdate(usuario);
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Usuario","Não foi encontrado esse usuario no sistema");
        }

        if(exameRepository.findById(listaEsperaFORM.getExame_id()).isPresent()) {
            entrada.setExame(exameRepository.findById(listaEsperaFORM.getExame_id()).get());
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Exame","Não foi encontrado esse exame no sistema");
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
    public ListaEspera filaConsulta(ListaEsperaConsultaFORM listaEsperaFORM) throws ServiceException {
        ListaEspera entrada = new ListaEspera();
        entrada.setDataEntradaLista(LocalDateTime.now());
        entrada.setLastUpdate(entrada.getDataEntradaLista());
        entrada.setRequerAutorizacao(false);
        if(pacienteRepository.findById(listaEsperaFORM.getPaciente_id()).isPresent()) {
            entrada.setPaciente(pacienteRepository.findById(listaEsperaFORM.getPaciente_id()).get());
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Paciente","Não foi encontrado esse paciente no sistema");
        }

        if(usuarioRepository.findById(listaEsperaFORM.getUser_id()).isPresent()) {
            Usuario usuario = usuarioRepository.findById(listaEsperaFORM.getUser_id()).get();
            entrada.setUsuarioLastUpdate(usuario);
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Usuario","Não foi encontrado esse usuario no sistema");
        }

        entrada.setConsulta(consultaRepository.findById(Long.parseLong("1")).get());
        if(especialidadeRepository.findById(listaEsperaFORM.getEspecialidade_id()).isPresent()) {
            entrada.setEspecialidade(especialidadeRepository.findById(listaEsperaFORM.getEspecialidade_id()).get());
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"Especialidade","Não foi encontrada essa especialidade no sistema");
        }

        entrada.setAtivo(true);
        listaEsperaRepository.save(entrada);
        return entrada;
    }

    @Transactional
    public ListaEspera removerDaFila(Long id, DeleteFilaFORM deleteFilaFORM) throws ServiceException {
        if(listaEsperaRepository.findById(id).isPresent()) {
            ListaEspera listaEspera = listaEsperaRepository.findById(id).get();
            listaEspera.setAtivo(false);
            listaEspera.setLastUpdate(LocalDateTime.now());
            if(usuarioRepository.findById(deleteFilaFORM.getUser_id()).isPresent()) {
                listaEspera.setUsuarioLastUpdate(usuarioRepository.findById(deleteFilaFORM.getUser_id()).get());
            } else {
                throw new ServiceException(HttpStatus.NOT_FOUND,"Usuario","Não foi encontrado esse usuario no sistema");
            }
            listaEspera.setMotivoRemocao(deleteFilaFORM.getMotivoCancelamento());
            listaEsperaRepository.save(listaEspera);
            return listaEspera;
        } else {
            throw new ServiceException(HttpStatus.NOT_FOUND,"ListaEspera","Não foi encontrada essa solicitação de espera no sistema");
        }
    }

    public ListaEspera update(Long id, UpdateListaEsperaFORM listaEsperaFORM) throws ServiceException {
        if(listaEsperaRepository.findById(id).isPresent()) {
            ListaEspera listaEspera = listaEsperaRepository.findById(id).get();
            if(listaEsperaFORM.getEspecilidade_id() != null && especialidadeRepository.findById(listaEsperaFORM.getEspecilidade_id()).isPresent()) {
                listaEspera.setEspecialidade(especialidadeRepository.findById(listaEsperaFORM.getEspecilidade_id()).get());
            }
            if(listaEsperaFORM.getExame_id() != null &&exameRepository.findById(listaEsperaFORM.getExame_id()).isPresent()) {
                Exame exame = exameRepository.findById(listaEsperaFORM.getExame_id()).get();
                listaEspera.setExame(exame);
                listaEspera.setConsulta(null);
                listaEspera.setRequerAutorizacao(exame.isAutorizacao());
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
}
