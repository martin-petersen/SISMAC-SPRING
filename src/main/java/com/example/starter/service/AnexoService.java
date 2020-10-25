package com.example.starter.service;

import com.example.starter.model.Anexo;
import com.example.starter.repository.AnexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AnexoService {

    @Autowired
    private AnexoRepository anexoRepository;

    @Transactional
    public Anexo salvar(MultipartFile multipartFile) {
        try {
            Anexo anexo = new Anexo(multipartFile.getOriginalFilename(),multipartFile.getContentType(),multipartFile.getBytes());
            anexoRepository.save(anexo);
            return anexo;
        }catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public boolean removerAnexo(Long id) {
        try {
            anexoRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
