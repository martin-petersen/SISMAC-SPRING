package com.example.starter.service;

import java.time.LocalDate;

public abstract class Notificacao {
    public abstract void lembrete(String NomePaciente,String email,String especialidade,LocalDate dataDeAgendamento,String lugar);
    

}