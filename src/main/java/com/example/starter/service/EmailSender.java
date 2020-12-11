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
public class EmailSender extends Notificacao {
    private static final EmailSender instancia = new EmailSender();
    
    public String msg;
    
    public static EmailSender getInstancia(){
        return instancia;
    }
    
    @Override
    public  String configurarMensagem(){
         return msg;
    }

    public void lembreteDeMarcacao(Usuario usuario, Paciente paciente, Especialidade especialidade, Agendamento agendamento, String titulo){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.msg = "Saudações " + paciente.getNomePaciente() + "\n" +
                    "Especialidade: " + especialidade.getNomeEspecialidade() + "\n" +
                    "Dia: " + agendamento.getDataAgendamento().format(formatter) + "\n" +
                    "Lugar: " + agendamento.getLugar() + "\n" +
                    "é importante entender que o procedimento é por ordem de chegada";
        enviarEmail(usuario.getEmail(), titulo);
    }

    public void confirmaEmail(Usuario usuario) {
        this.msg = "Seu token de validação: " + usuario.getValidateCode();
        enviarEmail(usuario.getEmail(), "Confirma seu usuário SISMAC");
    }

    public void confirmaConsulta(Usuario usuario, Agendamento agendamento, String paciente, String especialidade) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.msg = "Saudações " + paciente + "\n" +
                    "você está agendado para uma Consulta:" + "\n\n" +
                    "Especialidade: " + especialidade + "\n" +
                    "Medico: " + agendamento.getMedico() + "\n" +
                    "Local: " + agendamento.getLugar() + "\n" +
                    "Dia: " + agendamento.getDataAgendamento().format(formatter) + "\n" +
                    "é importante entender que a consulta é por ordem de chegada";
        enviarEmail(usuario.getEmail(), "Confirmação de agendamento");
    }

    public void confirmaExame(Usuario usuario, Agendamento agendamento, String paciente, String exame) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.msg = "Saudações " + paciente + "\n" +
                    "você está agendado para um(a)" + exame + "\n" +
                    "Dia: " + agendamento.getDataAgendamento().format(formatter) + "\n" +
                    "Lugar: " + agendamento.getLugar() + "\n" +
                    "é importante entender que a execução do exame acontece por ordem de chegada";
        enviarEmail(usuario.getEmail(), "Confirmação de agendamento");
    }

    public void recuperaSenha(Usuario usuario, String newPassword) {
        this.msg = "Sua senha atual é: " + newPassword + "\n" +
                "IMPORTANTE!!!" + "\n" +
                "A senha atual é pouco segura, recomendamos que troque assim que efetuar o próxímo login";
        enviarEmail(usuario.getEmail(), "Recuperação de conta SISMAC");
    }
}
