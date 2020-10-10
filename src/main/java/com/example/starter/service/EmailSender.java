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

    public void recoverPassword(Usuario usuario, String newPassword) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(usuario.getEmail());
        simpleMailMessage.setSubject("Recuperação de conta SISMAC");
        simpleMailMessage.setText("Sua senha atual é: " + newPassword);
        simpleMailMessage.setText("IMPORTANTE!!!");
        simpleMailMessage.setText("A senha atual é pouco segura, recomendamos que troque assim que efetuar o próxímo login");
        javaMailSender.send(simpleMailMessage);
    }
}
