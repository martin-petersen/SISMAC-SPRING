package com.example.starter.dto;

import com.example.starter.model.Paciente;
import org.springframework.data.domain.Page;

public class PacienteDTO {
    private final String nome;
    private final String carteiraSUS;
    private final String cpf;
    private final String cidade;
    private final String bairro;
    private final String complemento;
    private final String dataNascimento;
    private final String telefone;
    private final String numero;


    public PacienteDTO(Paciente paciente) {
        this.nome = paciente.getNomePaciente();
        this.carteiraSUS = paciente.getCarteiraSUS();
        this.cpf = paciente.getCpf();
        this.cidade = paciente.getCidade();
        this.bairro = paciente.getBairro();
        this.complemento = paciente.getComplemento();
        this.dataNascimento = paciente.getDataNascimento();
        this.telefone = paciente.getTelefone();
        this.numero = paciente.getNumero();
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

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getNumero() {
        return numero;
    }

    public static Page<PacienteDTO> convert(Page<Paciente> pacientes) {
        return pacientes.map(PacienteDTO::new);
    }

    public static PacienteDTO convert (Paciente paciente) {
        return new PacienteDTO(paciente);
    }
}
