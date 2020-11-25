package com.example.starter.controller;

import com.example.starter.dto.EncaminhamentoDTO;
import com.example.starter.exceptions.ServiceException;
import com.example.starter.form.AutorizacaoFORM;
import com.example.starter.model.Anexo;
import com.example.starter.model.Encaminhamento;
import com.example.starter.service.AnexoService;
import com.example.starter.service.EncaminhamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping(path = "/encaminhamento")
public class EncaminhamentoController {

    @Autowired
    private AnexoService anexoService;

    @Autowired
    private EncaminhamentoService encaminhamentoService;

    @PostMapping("/{id}")
    public ResponseEntity<EncaminhamentoDTO> anexarEncaminhamento(@PathVariable Long id,
                                                                  @RequestParam("file") MultipartFile file,
                                                                  UriComponentsBuilder uriComponentsBuilder) throws ServiceException {
        Anexo anexo = anexoService.salvar(file);
        Encaminhamento encaminhamento = encaminhamentoService.salvar(id,anexo.getId());
        URI uri = uriComponentsBuilder.path("/encaminhamento/{id}").buildAndExpand(encaminhamento.getId()).toUri();
        EncaminhamentoDTO encaminhamentoDTO = new EncaminhamentoDTO(encaminhamento.getId(),encaminhamento.isAutorizado(),encaminhamento.getMotivo());
        return ResponseEntity.created(uri).body(encaminhamentoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> baixarEncaminhamento(@PathVariable Long id) throws ServiceException {
        Encaminhamento encaminhamento = encaminhamentoService.download(id);
        Anexo anexo = encaminhamento.getAnexo();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(anexo.getTipoAnexo()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+anexo.getNomeAnexo()+"\"")
                .body(new ByteArrayResource(anexo.getData()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EncaminhamentoDTO> updateEncaminhamento(@PathVariable Long id,
                                                                  @RequestParam("file") MultipartFile file) throws ServiceException {
        Anexo anexo = anexoService.salvar(file);
        Encaminhamento encaminhamento = encaminhamentoService.update(id,anexo.getId());
        EncaminhamentoDTO encaminhamentoDTO = new EncaminhamentoDTO(encaminhamento.getId(),encaminhamento.isAutorizado(),encaminhamento.getMotivo());
        return ResponseEntity.ok(encaminhamentoDTO);
    }

    @PutMapping("/autorizar/{id}")
    public ResponseEntity<EncaminhamentoDTO> autorizarEncaminhamento(@PathVariable Long id,
                                                                     @RequestBody AutorizacaoFORM autorizacaoFORM) throws ServiceException {
        Encaminhamento encaminhamento = encaminhamentoService.autorizar(id,autorizacaoFORM);
        EncaminhamentoDTO encaminhamentoDTO = new EncaminhamentoDTO(encaminhamento.getId(),encaminhamento.isAutorizado(),encaminhamento.getMotivo());
        return ResponseEntity.ok(encaminhamentoDTO);
    }
}











