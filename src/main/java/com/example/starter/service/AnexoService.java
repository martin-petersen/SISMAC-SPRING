package com.example.starter.service;

import com.example.starter.exceptions.ServiceException;
import com.example.starter.model.Anexo;
import com.example.starter.repository.AnexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AnexoService {

    @Autowired
    private AnexoRepository anexoRepository;

    @Transactional
    public Anexo salvar(MultipartFile multipartFile) throws ServiceException {
        try {
            Anexo anexo = new Anexo(multipartFile.getOriginalFilename(),multipartFile.getContentType(),multipartFile.getBytes());
            anexoRepository.save(anexo);
            return anexo;
        }catch (Exception e) {
            throw new ServiceException(HttpStatus.BAD_REQUEST,"Anexo","Erro ao criar anexo verifique o tamanho ou entre em contato com a administração");
        }
    }

    @Transactional
    public boolean removerAnexo(Long id) throws ServiceException {
        try {
            anexoRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new ServiceException(HttpStatus.BAD_REQUEST,"Anexo","Erro ao remover anexo entre em contato com a administração");
        }
    }
}
