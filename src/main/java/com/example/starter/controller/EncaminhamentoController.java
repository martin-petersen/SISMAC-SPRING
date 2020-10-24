package com.example.starter.controller;

import com.example.starter.dto.EncaminhamentoDTO;
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

import javax.annotation.Resource;
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
                                                                  UriComponentsBuilder uriComponentsBuilder) {
        try {
            Anexo anexo = anexoService.salvar(file);
            Encaminhamento encaminhamento = encaminhamentoService.salvar(id,anexo.getId());
            URI uri = uriComponentsBuilder.path("/filaEspera/{id}").buildAndExpand(encaminhamento.getId()).toUri();
            EncaminhamentoDTO encaminhamentoDTO = new EncaminhamentoDTO(encaminhamento.getId(),encaminhamento.isAutorizado(),encaminhamento.getMotivo());
            return ResponseEntity.created(uri).body(encaminhamentoDTO);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> baixarEncaminhamento(@PathVariable Long id) {
        try {
            Encaminhamento encaminhamento = encaminhamentoService.download(id);
            Anexo anexo = encaminhamento.getAnexo();
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(anexo.getTipoAnexo()))
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+anexo.getNomeAnexo()+"\"")
                    .body(new ByteArrayResource(anexo.getData()));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
