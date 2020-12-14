package com.example.starter.service;
import com.example.starter.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class Notificacao {
    @Autowired
    public JavaMailSender javaMailSender;
    
    public abstract String configurarMensagem();
    
    //metódo template
    public void enviarEmail(String emailDestino, String titulo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(emailDestino);
        simpleMailMessage.setSubject(titulo);
        simpleMailMessage.setText(configurarMensagem());
        javaMailSender.send(simpleMailMessage);
    }

    public void enviarEmail(String emailDestino, String titulo, String corpo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(emailDestino);
        simpleMailMessage.setSubject(titulo);
        simpleMailMessage.setText(corpo);
        javaMailSender.send(simpleMailMessage);
    }
}