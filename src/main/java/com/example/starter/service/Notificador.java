import java.time.LocalDate;

public abstract class Notificador {
    public abstract void lembrete(String NomePaciente,String email,int especialidade_id,LocalDate dataDeAgendamento,String lugar);
    

}