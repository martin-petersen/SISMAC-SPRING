package com.example.starter.controller;

import com.example.starter.dto.ListaEsperaDTO;
import com.example.starter.exceptions.ServiceException;
import com.example.starter.form.DeleteFilaFORM;
import com.example.starter.form.ListaEsperaConsultaFORM;
import com.example.starter.form.ListaEsperaExameFORM;
import com.example.starter.form.UpdateListaEsperaFORM;
import com.example.starter.model.ListaEspera;
import com.example.starter.service.ListaEsperaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/filaEspera")
public class ListaEsperaController {

    @Autowired
    private ListaEsperaService listaEsperaService;

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<ListaEsperaDTO>> buscarPorPaciente(@PathVariable Long id) {
        List<ListaEsperaDTO> listaEspera = listaEsperaService.buscarPorPaciente(id);
        return ResponseEntity.ok(listaEspera);
    }

    @GetMapping
    public ResponseEntity<Page<ListaEsperaDTO>> buscarLista(@RequestParam(required = false) Long especialidade_id,
                                                            @RequestParam(required = false) Long exame_id,
                                                            @PageableDefault(size = 10, direction = Sort.Direction.ASC, sort = {"especialidade","exame"})
                                                                  Pageable pageable) throws ServiceException {
        if(especialidade_id != null) {
            List<ListaEspera> listaEspera = listaEsperaService.listaEsperaConsulta(especialidade_id);
            return ResponseEntity.ok(convertInDetalhamentoDTO(listaEspera,pageable));
        } else if (exame_id != null) {
            List<ListaEspera> listaEspera = listaEsperaService.listaEsperaExame(exame_id);
            return ResponseEntity.ok(convertInDetalhamentoDTO(listaEspera,pageable));
        } else {
            List<ListaEspera> listaEspera = listaEsperaService.listaEspera();
            return ResponseEntity.ok(convertInDetalhamentoDTO(listaEspera,pageable));
        }
    }

    @PostMapping("/filaConsulta")
    public ResponseEntity<ListaEsperaDTO> entrarNaFilaConsulta(@RequestBody @Valid ListaEsperaConsultaFORM listaEsperaFORM,
                                                               UriComponentsBuilder uriComponentsBuilder) throws ServiceException {
        ListaEspera listaEspera = listaEsperaService.filaConsulta(listaEsperaFORM);
        URI uri = uriComponentsBuilder.path("/filaEspera/{id}").buildAndExpand(listaEspera.getId()).toUri();
        ListaEsperaDTO listaEsperaDTO = new ListaEsperaDTO(listaEspera.getId(),
                listaEspera.getPaciente(),
                listaEspera.getEspecialidade(),
                listaEspera.getConsulta(),
                listaEspera.isAtivo());
        return ResponseEntity.created(uri).body(listaEsperaDTO);
    }

    @PostMapping("/filaExame")
    public ResponseEntity<ListaEsperaDTO> entrarNaFilaExame(@RequestBody @Valid ListaEsperaExameFORM listaEsperaFORM,
                                                       UriComponentsBuilder uriComponentsBuilder) throws ServiceException {
        ListaEspera listaEspera = listaEsperaService.filaExame(listaEsperaFORM);
        URI uri = uriComponentsBuilder.path("/filaEspera/{id}").buildAndExpand(listaEspera.getId()).toUri();
        ListaEsperaDTO listaEsperaDTO = new ListaEsperaDTO(listaEspera.getId(),
                listaEspera.getPaciente(),
                listaEspera.getExame(),
                listaEspera.getEncaminhamento(),
                listaEspera.isRequerAutorizacao(),
                listaEspera.isAtivo());
        return ResponseEntity.created(uri).body(listaEsperaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListaEsperaDTO> atualizarFila(@PathVariable Long id,
                                                        @RequestBody @Valid UpdateListaEsperaFORM listaEsperaFORM) throws ServiceException {
        ListaEspera listaEspera = listaEsperaService.update(id,listaEsperaFORM);
        return ResponseEntity.ok(new ListaEsperaDTO(listaEspera));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ListaEsperaDTO> deletarCadastro(@PathVariable Long id,
                                             @RequestBody @Valid DeleteFilaFORM deleteFilaFORM) throws ServiceException {
        ListaEspera listaEspera = listaEsperaService.removerDaFila(id,deleteFilaFORM);
        ListaEsperaDTO listaEsperaDTO = new ListaEsperaDTO(listaEspera);
        return ResponseEntity.ok(listaEsperaDTO);
    }

    private Page<ListaEsperaDTO> convertInDetalhamentoDTO (List<ListaEspera> lista, Pageable pageable) {
        List<ListaEsperaDTO> listaEsperaDTO = new ArrayList<>();
        for (ListaEspera e:
                lista) {
            listaEsperaDTO.add(new ListaEsperaDTO(e));
        }
        return new PageImpl<>(listaEsperaDTO,pageable,listaEsperaDTO.size());
    }
}
