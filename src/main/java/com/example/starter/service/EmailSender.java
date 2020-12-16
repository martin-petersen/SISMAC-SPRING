package com.example.starter.service;

import com.example.starter.model.Agendamento;
import com.example.starter.model.Cliente;
import com.example.starter.model.Usuario;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class EmailSender extends Notificacao {

    public String msg;
    
    @Override
    public String configurarMensagem(){
         return this.msg;
    }

    public void lembreteDeMarcacao(Usuario usuario, Cliente cliente, Agendamento agendamento, String titulo){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.msg = "Saudações " + cliente.getNomeCliente() + "\n" +
                    "Dia: " + agendamento.getDataAgendamento().format(formatter) + "\n" +
                    "é importante entender que o procedimento é por ordem de chegada";
        enviarEmail(usuario.getEmail(), titulo);
    }

    public void confirmaEmail(Usuario usuario) {
        this.msg = "Seu token de validação: " + usuario.getValidateCode();
        enviarEmail(usuario.getEmail(), "Confirma seu usuário BARBEX");
    }

    public void confirmaCabelo(Usuario usuario, Agendamento agendamento, String paciente) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.msg = "Saudações " + paciente + "\n" +
                    "você está agendado para um corte de cabelo!" + "\n" +
                    "Dia: " + agendamento.getDataAgendamento().format(formatter) + "\n" +
                    "caso tenha fila no horário de atendimento do dia prioridade para a  sequência da lista de agendamento do dia ca fila";
        enviarEmail(usuario.getEmail(), "Confirmação de agendamento");
    }

    public void confirmaBarba(Usuario usuario, Agendamento agendamento, String paciente) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.msg = "Saudações " + paciente + "\n" +
                    "você está agendado para barba" + "\n" +
                    "Dia: " + agendamento.getDataAgendamento().format(formatter) + "\n" +
                    "caso tenha fila no  horário de atendimento do dia prioridade para a  sequência da lista de agendamento do dia ca fila";
        enviarEmail(usuario.getEmail(), "Confirmação de agendamento");
    }

    public void recuperaSenha(Usuario usuario, String newPassword) {
        this.msg = "Sua senha atual é: " + newPassword + "\n" +
                "IMPORTANTE!!!" + "\n" +
                "A senha atual é pouco segura, recomendamos que troque assim que efetuar o próxímo login";
        enviarEmail(usuario.getEmail(), "Recuperação de conta BARBEX");
    }
}
