package com.example.starter.controller;

import com.example.starter.dto.ListaEsperaDTO;
import com.example.starter.exceptions.ServiceException;
import com.example.starter.form.ListaEsperaFORM;
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
    public ResponseEntity<List<ListaEsperaDTO>> buscarPorCliente(@PathVariable Long id) {
        List<ListaEsperaDTO> listaEspera = listaEsperaService.buscarPorCliente(id);
        return ResponseEntity.ok(listaEspera);
    }

    @GetMapping
    public ResponseEntity<Page<ListaEsperaDTO>> buscarLista(@RequestParam boolean cabelo,
                                                            @RequestParam boolean barba,
                                                            @PageableDefault(size = 10, direction = Sort.Direction.ASC)
                                                                  Pageable pageable) throws ServiceException {
        if(cabelo) {
            List<ListaEspera> listaEspera = listaEsperaService.listaEsperaCabelo();
            return ResponseEntity.ok(convertInDetalhamentoDTO(listaEspera,pageable));
        } else if (barba) {
            List<ListaEspera> listaEspera = listaEsperaService.listaEsperaBarba();
            return ResponseEntity.ok(convertInDetalhamentoDTO(listaEspera,pageable));
        } else {
            List<ListaEspera> listaEspera = listaEsperaService.listaEspera();
            return ResponseEntity.ok(convertInDetalhamentoDTO(listaEspera,pageable));
        }
    }

    @PostMapping("/filaCabelo")
    public ResponseEntity<ListaEsperaDTO> entrarNaFilaConsulta(@RequestBody @Valid ListaEsperaFORM listaEsperaFORM,
                                                               UriComponentsBuilder uriComponentsBuilder) throws ServiceException {
        ListaEspera listaEspera = listaEsperaService.filaCabelo(listaEsperaFORM);
        URI uri = uriComponentsBuilder.path("/filaEspera/{id}").buildAndExpand(listaEspera.getId()).toUri();
        ListaEsperaDTO listaEsperaDTO = new ListaEsperaDTO(
                listaEspera.getId(),
                listaEspera.getCliente(),
                listaEspera.getCabelo(),
                listaEspera.isAtivo());
        return ResponseEntity.created(uri).body(listaEsperaDTO);
    }

    @PostMapping("/filaBarba")
    public ResponseEntity<ListaEsperaDTO> entrarNaFilaExame(@RequestBody @Valid ListaEsperaFORM listaEsperaFORM,
                                                       UriComponentsBuilder uriComponentsBuilder) throws ServiceException {
        ListaEspera listaEspera = listaEsperaService.filaBarba(listaEsperaFORM);
        URI uri = uriComponentsBuilder.path("/filaEspera/{id}").buildAndExpand(listaEspera.getId()).toUri();
        ListaEsperaDTO listaEsperaDTO = new ListaEsperaDTO(
                listaEspera.getId(),
                listaEspera.getCliente(),
                listaEspera.getBarba(),
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
                                             @RequestParam Long user) throws ServiceException {
        ListaEspera listaEspera = listaEsperaService.removerDaFila(id,user);
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
