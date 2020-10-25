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
        simpleMailMessage.setText(
                "Sua senha atual é: " + newPassword + "\n" +
                "IMPORTANTE!!!" + "\n" +
                "A senha atual é pouco segura, recomendamos que troque assim que efetuar o próxímo login"
        );
        javaMailSender.send(simpleMailMessage);
    }

    public void consultaConfimada(String email, LocalDate dataAgendamento, String paciente, String especialidade) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Confirmação de agendamento");
        simpleMailMessage.setText(
                        "Saudações " + paciente + "\n" +
                        "você está agendado para uma Consulta:" + "\n\n" +
                        "Especialidade: " + especialidade + "\n" +
                        "Dia: " + dataAgendamento.format(formatter) + "\n" +
                        "é importante entender que a consulta é por ordem de chegada"


        );
        javaMailSender.send(simpleMailMessage);
    }

    public void exameConfimado(String email, LocalDate dataAgendamento, String paciente, String exame) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Confirmação de agendamento");
        simpleMailMessage.setText(
                "Saudações " + paciente + "\n" +
                "você está agendado para um(a)" + exame + "\n" +
                "Dia: " + dataAgendamento.format(formatter) + "\n" +
                "é importante entender que a execução do exame acontece por ordem de chegada"
        );
        javaMailSender.send(simpleMailMessage);
    }

    public void lembreteExame(String email, String paciente, String exame, LocalDate dataAgendamento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Lembrete de agendamento");
        simpleMailMessage.setText(
                "Saudações " + paciente + "\n" +
                        "você está agendado para um(a)" + exame + "\n" +
                        "Dia: " + dataAgendamento.format(formatter) + "\n" +
                        "é importante entender que a exacução do exame acontece por ordem de chegada"
        );
        javaMailSender.send(simpleMailMessage);
    }

    public void lembreteConsulta(String email, String paciente, LocalDate dataAgendamento, String especialidade) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Lembrete de agendamento");
        simpleMailMessage.setText(
                "Saudações " + paciente + "\n" +
                        "você está agendado para uma Consulta:" + "\n\n" +
                        "Especialidade: " + especialidade + "\n" +
                        "Dia: " + dataAgendamento.format(formatter) + "\n" +
                        "é importante entender que a consulta é por ordem de chegada"


        );
        javaMailSender.send(simpleMailMessage);
    }
}
