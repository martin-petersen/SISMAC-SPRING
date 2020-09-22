package com.example.starter.service;

import com.example.starter.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(Usuario usuario) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(usuario.getEmail());
        simpleMailMessage.setSubject("Confirma seu usuário SISMAC");
        simpleMailMessage.setText("Seu token de validação: " + usuario.getValidateCode());
        javaMailSender.send(simpleMailMessage);
    }
}
