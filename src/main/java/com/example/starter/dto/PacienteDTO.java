package com.example.starter.dto;

import com.example.starter.model.Paciente;
import org.springframework.data.domain.Page;

public class PacienteDTO {
    private String nome;
    private String carteiraSUS;
    private String cpf;

    public PacienteDTO(Paciente paciente) {
        this.nome = paciente.getNomePaciente();
        this.carteiraSUS = paciente.getCarteiraSUS();
        this.cpf = paciente.getCpf();
    }

    public String getNome() {
        return nome;
    }

    public String getCarteiraSUS() {
        return carteiraSUS;
    }

    public String getCpf() {
        return cpf;
    }

    public static Page<PacienteDTO> convert(Page<Paciente> pacientes) {
        return pacientes.map(PacienteDTO::new);
    }

    public static PacienteDTO convert (Paciente paciente) {
        return new PacienteDTO(paciente);
    }
}
